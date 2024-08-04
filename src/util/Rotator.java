package util;

import com.misterl.location.positions.Pos2di;
import util.positions.Pos;

import java.lang.reflect.Array;

public class Rotator<T> {

    private final Class<? extends T> cls;

    public Rotator(Class<? extends T> cls) {
        this.cls = cls;
    }

    /**
     * Rotates a position along with a grid
     * @param original the original position of the object
     * @param dim the dimensions of the full grid
     * @param dir the direction in which the grid was turned
     * @return a rotated {@link Pos2di}
     */
    public Pos rotatePos(Pos original, Dimension dim, Direction dir) {
        if(dir == Direction.RIGHT)
            return new Pos(original.y(), dim.getWidth() - original.x());
        if(dir == Direction.LEFT)
            return new Pos(dim.getHeight() - original.x(), original.x());
        return original;
    }

    public T[][] rotateLeft(T[][] in, Dimension dimension) {

        @SuppressWarnings("unchecked")
        T[][] map = (T[][])Array.newInstance(cls, dimension.getWidth(), dimension.getHeight());
        int startColumn = dimension.getHeight()-1;


        for(int row = 0; row < in.length; row++) {

            for(int col = 0; col < in[row].length; col++) {

                map[col][startColumn] = in[row][col];

            }

            startColumn--;

        }

        return map;

    }

    public T[][] rotateRight(T[][] in, Dimension dimension) {

        @SuppressWarnings("unchecked")
        T[][] map = (T[][])Array.newInstance(cls, dimension.getWidth(), dimension.getHeight());

        for(int row = 0; row < in.length; row++) {

            T[] tileRow = in[row];
            int startRow = dimension.getWidth()-1;

            for(int col = 0; col < tileRow.length; col++) {

                map[startRow][row] = in[row][col];

                startRow--;

            }

        }

        return map;

    }

}
