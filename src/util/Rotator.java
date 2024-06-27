package util;

import java.lang.reflect.Array;

public class Rotator<T> {

    private final Class<? extends T> cls;

    public Rotator(Class<? extends T> cls) {
        this.cls = cls;
    }

    public T[][] rotateLeft(T[][] in, Dimension dimension) {

        @SuppressWarnings("unchecked")
        T[][] map = (T[][])Array.newInstance(cls, dimension.getWidth(), dimension.getHeight());
        int startColumn = dimension.getHeight()-1;

        for(int row = 0; row < in.length; row++) {

            T[] tileRow = in[row];

            for(int col = 0; col < tileRow.length; col++) {

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
