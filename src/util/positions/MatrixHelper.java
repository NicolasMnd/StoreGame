package util.positions;

import util.Dimension;

import java.util.Arrays;
import java.util.function.BiFunction;

/**
 * This class tries to help rotating the game.
 * 1) Create a matrix with dimensions the same as the full game in total pixels used (32 pixels per tile)
 * 2) Fill the matrix with zero elements except the position of the player
 * ~ this matrix can be retrieved and rotated somewhere else
 * 3) Extract a position from a rotated matrix
 * 4) Add the necessary offsets to the player position, the matrix to transform to coordinates will contain the 'grid' = elements per 32 pixels
 */
public class MatrixHelper {

    private final Pos realPosition, original;
    private final Dimension dimension;
    private final int tileSize;
    private int diffX, diffY;

    public MatrixHelper(Pos realPosition, Dimension dimension, int tileSize) {
        this.realPosition = realPosition;
        this.dimension = dimension;
        this.tileSize = tileSize;
        this.original = scalePosition();
    }

    /**
     * Given a matrix, return the x,y coordinate of the element that is 1
     * @param matrix the matrix
     * @return a {@link Pos} object representing the non-zero object in the matrix
     */
    Pos getGridPosition(Integer[][] matrix) {
        for(int i = 0; i < matrix.length; i++)
            for(int j = 0; j < matrix[i].length; j++)
                if(matrix[i][j] == 1)
                    return new Pos((j * tileSize), (i * tileSize));

        return realPosition;
    }

    /**
     * This function will return rotated position for the player
     * @param matrix the integer matrix representing the player grid location
     * @param yAdd the function that adds to the X coordinate of the grid position
     * @param xAdd the function that adds to the X coordinate of the grid position
     * @return the position with small offsets < 32 which represents the player position
     */
    public Pos getRotatedPosition(Integer[][] matrix, BiFunction<Integer, Integer, Integer> yAdd, BiFunction<Integer, Integer, Integer> xAdd) {
        Pos gridPosition = getGridPosition(matrix);

        gridPosition.addY(yAdd.apply(diffX, diffY));
        gridPosition.addX(xAdd.apply(diffX, diffY));

        return gridPosition;
    }

    /**
     * @return a matrix with dimensions {@link MatrixHelper#dimension}, where each element is zero except the exact position of the player
     */
    public Integer[][] getPlayerPositionMatrix() {
        Integer[][] matrix = new Integer[dimension.getHeight()][dimension.getWidth()];

        int rows = 0;

        while(rows < dimension.getHeight()) {

            if(rows == original.y())
                matrix[rows] = getRow(dimension.getWidth(), original.x());
            else
                matrix[rows] = getZeroRow(dimension.getWidth());

            rows++;
        }

        return matrix;
    }

    /**
     * @param length the length of the row
     * @return a row of zero elements given a certain length
     */
    public Integer[] getZeroRow(int length) {
        Integer[] row = new Integer[length];
        Arrays.fill(row, 0);
        return row;
    }

    /**
     * @param length the length of the row
     * @param element the index in the row that should have value 1
     * @return a row containing only zeros except index 'element'
     */
    public Integer[] getRow(int length, int element) {
        Integer[] row = new Integer[length];
        for(int i = 0; i < length; i++)
            if(i == element)
                row[i] = 1;
            else
                row[i] = 0;
        return row;
    }

    /**
     * @param matrix the matrix to be transposed
     * @return the transposed matrix
     */
    int[][] transpose(int[][] matrix) {
        int[][] transpose = new int[matrix[0].length][matrix.length];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                transpose[j][i] = matrix[i][j];
            }
        }
        return transpose;
    }

    /**
     * @return a matrix of integers where the second diagonal is only ones, rest is zero
     */
    int[][] getAntiSymmetric(int length) {
        int[][] matrix = new int[length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if(i == j)
                    matrix[i][j] = 1;
                else
                    matrix[i][j] = 0;
            }
        }

        int replaceUntil = length % 2 == 0 ? length / 2 : (length-1) / 2;
        int i = 0;

        while(i < replaceUntil) {
            int[] saveI = matrix[i];
            int[] saveMirror = matrix[length - i - 1];
            matrix[i] = saveMirror;
            matrix[length - i - 1] = saveI;
            i += 1;
        }

        return matrix;
    }

    /**
     * The given dimensions of the class are counted per tile, not per pixel (32 per tile).
     * We scale the player position to a tile position.
     * @return
     */
    private Pos scalePosition() {
        this.diffX = realPosition.x() % tileSize;
        this.diffY = realPosition.y() % tileSize;
        return new Pos(
                Math.floorDiv(realPosition.x(), tileSize),
                Math.floorDiv(realPosition.y(), tileSize)
        );
    }

}
