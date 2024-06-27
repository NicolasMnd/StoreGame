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

        // First we get the objects which can be peer objects
        List<T> possiblePeers = getPeersInRow(row);

        //TODO
        return null;
    }

    private List<T> getPeersInRow(T[] row) {

        List<T> possiblePeers = new ArrayList<>();

        for(T element : row)
            if(isPeerObject.test(element))
                possiblePeers.add(element);

        return possiblePeers;

    }

}
