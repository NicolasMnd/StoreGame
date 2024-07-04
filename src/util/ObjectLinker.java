package util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class ObjectLinker<T> {

    private T[][] matrix;
    private final Predicate<T> isPeerObject;
    private final BiPredicate<T, T> canBePeers;
    private final BiFunction<T, T, Boolean> action;
    private final BiPredicate<T, T> arePeers;
    private List<T> unlinkedPeers = new ArrayList<>();

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

        // Check all rows & link early per row
        for(int i = 0; i < matrix.length; i++) {

            checkRow(matrix[i]);

        }

        // Try to loop over the unfinished items & try to link them
        for(int i = 0; i < unlinkedPeers.size(); i++) {
            for(int j = 0; j < unlinkedPeers.size(); j++)
                if(canBePeers.test(unlinkedPeers.get(i), unlinkedPeers.get(j)))
                    action.apply(unlinkedPeers.get(i), unlinkedPeers.get(j));
        }

    }

    /**
     * Sometimes the matrix has to be changed in order to let the algorithm work.
     * We tread safely and make this method only accessible for child classes.
     * @param matrix the new matrix
     */
    protected void setMatrix(T[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * Retrieves the matrix to do modification
     * @return the clone of the current matrix
     */
    protected T[][] getMatrix() {
        return this.matrix.clone();
    }

    /**
     * This function will loop over the row and determine which objects T can be linked
     * @param row the row which is examined
     * @return a list of leftover
     */
    private void checkRow(T[] row) {

        // First we check if the row contains any peer objects
        if(containsPeers(row, 0) == -1)
            return;

        // This variable is used to skip an amount of objects when we found a group to link
        int waitUntil = 0;

        // We loop over the row and check for peer objects
        for(int i = 0; i < row.length; i++) {
            System.out.println("Iteration: " + i);

            // If we have not detected any peer objects, we skip until there is one
            if(i < waitUntil)
                continue;

            waitUntil = containsPeers(row, i);

            // We check if from this index there are still peer objects
            if(waitUntil == -1)
                return;

            // Get the length of peers ; this way we can differentiate whether we need to add
            // insufficient amount to leftovers, or try attempt a sufficiently sized row
            Pair<Integer, Integer> peerInfo = getLengthPeers(row, i);

            // If the amount of peers in a row is acceptable, we link them.
            if(peerInfo.getSecond() >= getLinkRowAmount()) {
                System.out.println("> linking from " + peerInfo.getFirst() + " until " + (peerInfo.getFirst() + peerInfo.getSecond()-2));
                waitUntil = attemptLink(row, peerInfo);
                System.out.println("Supposed to wait until: " + waitUntil);
            }
            // Else we skip the amount and add them to the leftovers
            else {
                waitUntil = getPeerObjectsInRow(row, peerInfo.getFirst());
            }

            System.out.println("End iteration " + i);

        }

        //TODO
        System.out.println("Leftovers: ");
        for(T t : unlinkedPeers)
            System.out.print(t + ", ");
    }

    /**
     * Will loop through a row until we find a non peer object. We count the amount of peers.
     * @param row row that will be looped through
     * @return a pair of which index the peers start & stop
     */
    Pair<Integer, Integer> getLengthPeers(T[] row, int start) {
        assert start < row.length;
        int amount = 0;
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
     * @param startIndex the start index
     * @return a list of peer objects that cannot be grouped
     */
    int getPeerObjectsInRow(T[] row, int startIndex) {
        int skip = startIndex;
        for(int i = startIndex; i < row.length; i++)
            if(!isPeerObject.test(row[i]))
                return i;
            else {
                skip++;
                unlinkedPeers.add(row[i]);
            }
        return skip;
    }

    /**
     * Given that we have a row with at least {@link ObjectLinker#getLinkRowAmount()} linkable peers,
     * we will now try to link them.
     * @param row the row we will loop over
     * @param peerInfo the info for the peers: start index ; amount of peers
     */
    int attemptLink(T[] row, Pair<Integer, Integer> peerInfo) {

        for(int i = peerInfo.getFirst(); i < peerInfo.getFirst() + getLinkRowAmount() - 1; i++)
            if(canBePeers.test(row[i], row[i+1])) {
                action.apply(row[i], row[i + 1]);
                System.out.println("Linked " + i + " and " + (i+1));
            }
            else {
                System.out.println("Added " + i + " to list");
                unlinkedPeers.add(row[i]);
            }

        return peerInfo.getFirst() + getLinkRowAmount();

    }

}
