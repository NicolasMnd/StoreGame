package util.positions;

import util.Dimension;

import java.util.Arrays;

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

    public Pos getRotatedPosition(Integer[][] matrix) {

        for(int i = 0; i < matrix.length; i++)
            for(int j = 0; j < matrix[i].length; j++)
                if(matrix[i][j] == 1)
                    return new Pos((j * tileSize) - diffX, (i * tileSize) - diffY);

        return realPosition;
    }

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
