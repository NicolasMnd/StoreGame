package util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class ObjectLinker<T> {

    private final T[][] matrix;
    private final Predicate<T> isPeerObject;
    private final BiPredicate<T, T> canBePeers;
    private final BiFunction<T, T, Boolean> action;
    private final BiPredicate<T, T> arePeers;

    /**
     * Object linker will try to connect objects on a very high level.
     * @param matrix the matrix of objects where we will iterate through
     * @param isPeerObject a predicate that determines if the given object should be interpreted as a peer object
     * @param action this function will trigger an action on object T
     * @param arePeers a predicate which determines if the parameter T already is a peer of a certain
     */
    public ObjectLinker(T[][] matrix, Predicate<T> isPeerObject, BiFunction<T, T, Boolean> action, BiPredicate<T, T> arePeers, BiPredicate<T, T> canBePeers) {
        this.matrix = matrix;
        this.isPeerObject = isPeerObject;
        this.canBePeers = canBePeers;
        this.action = action;
        this.arePeers = arePeers;
    }

    /**
     * Determines the amount of peers that can be linked
     * @return the amount of objects of type T can be linked to each other
     */
    public int getLinkRowAmount() {
        return 1;
    }

    /**
     * Iterates through the given matrix and links all objects together.
     */
    public void findRelations() {

        List<T> unfinishedLinks = new ArrayList<>();

        // Check all rows
        for(int i = 0; i < matrix.length; i++) {

            unfinishedLinks = checkRow(matrix[i], unfinishedLinks);

        }

    }

    /**
     * This function will loop over the row and determine which objects T can be linked
     * @param row the row which is examined
     * @param leftOvers the leftover objects T from past iterations
     * @return a list of leftover
     */
    private List<T> checkRow(T[] row, List<T> leftOvers) {

        if(containsPeers(row, 0) == -1)
            return leftOvers;

        int waitUntil = 0;

        // We loop over the row and check for peer objects
        for(int i = 0; i < row.length; i++) {

            waitUntil = containsPeers(row, i);

            // We check if from this index there are still peer objects
            if(waitUntil == -1)
                return leftOvers;

            // If we have not detected any peer objects, we skip until there is one
            if(i != waitUntil)
                continue;

            Pair<Integer, Integer> peerInfo = getLengthPeers(row, i);

            // If the amount of peers in a row is acceptable, we link them.
            if(peerInfo.getSecond() >= getLinkRowAmount()) {
                leftOvers = attemptLink(row, leftOvers, peerInfo);
            }
            // Else we skip the amount and add them to the leftovers
            else {
                int sizeBefore = leftOvers.size();
                leftOvers = getPeerObjectsInRow(row, leftOvers, peerInfo.getFirst());
                waitUntil = leftOvers.size() - sizeBefore;
            }

        }

        //TODO
        return null;
    }

    /**
     * Will loop through a row until we find a non peer object. We count the amount of peers.
     * @param row row that will be looped through
     * @return a pair of which index the peers start & stop
     */
    Pair<Integer, Integer> getLengthPeers(T[] row, int start) {
        assert start < row.length;
        int amount = 0;
        int startPeer = start;
        for(int i = start; i < row.length; i++) {
            if(!isPeerObject.test(row[i]))
                return new Pair<>(start, amount);
            else
                amount++;
        }
        return new Pair<>(start, amount);
    }

    /**
     * Determines if the row has any peers in it given a certain start index
     * @param row the row which is looped through
     * @param start the starting index
     * @return
     */
    int containsPeers(T[] row, int start) {
        assert start < row.length;
        for(int i = start; i < row.length; i++) {
            if(isPeerObject.test(row[i]))
                return i;
        }
        return -1;
    }

    /**
     * We retrieve all peer objects starting from a specified index. Called when the amount
     * of peers were insufficient in row to link them. They are added to the leftover list.
     * This will go until it finds a non peer object or until the end of the row has been reached.
     * @param row the row we currently operate
     * @param list the peers from previous
     * @param startIndex the start index
     * @return a list of peer objects that cannot be grouped
     */
    List<T> getPeerObjectsInRow(T[] row, List<T> list, int startIndex) {
        for(int i = startIndex; i < row.length; i++)
            if(!isPeerObject.test(row[i]))
                return list;
            else
                list.add(row[i]);
        return list;
    }

    /**
     * Given that we have a row with at least {@link ObjectLinker#getLinkRowAmount()} linkable peers,
     * we will now try to link them.
     * @param row
     * @param unlinkedPeers the list of unlinked peers
     * @param peerInfo the info for the peers: start index ; amount of peers
     */
    List<T> attemptLink(T[] row, List<T> unlinkedPeers, Pair<Integer, Integer> peerInfo) {

        for(int i = peerInfo.getFirst(); i < peerInfo.getFirst() + peerInfo.getSecond() - 1; i++)
            if(canBePeers.test(row[i], row[i+1]))
                action.apply(row[i], row[i+1]);
            else
                unlinkedPeers.add(row[i]);

        return unlinkedPeers;

    }

}
