package util.positions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPositionRotator {

    MatrixHelper rotator;

    @Test
    public void testGetAntiSymmetrisch() {
        assertTrue(
                areEqual(
                    rotator.getAntiSymmetric(5),
                    new int[][] {
                            {0, 0, 0, 0, 1},
                            {0, 0, 0, 1, 0},
                            {0, 0, 1, 0, 0},
                            {0, 1, 0, 0, 0},
                            {1, 0, 0, 0, 0}
                    }
                )
        );
    }

    @Test
    public void testGetTranspose() {
        assertTrue(
                areEqual(
                        rotator.transpose(new int[][] {
                                {1,2,3},
                                {4,5,6},
                                {7,8,9}
                        }),
                        new int[][] {
                                {1,4,7},
                                {2,5,8},
                                {3,6,9}
                        }
                )
        );
    }

    boolean areEqual(int[][] arr1, int[][] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[i].length; j++) {
                if (arr1[i][j] != arr2[i][j])
                    return false;
            }
        }
        return true;
    }

    boolean areEqual(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i])
                return false;
        }
        return true;
    }

    void print(int[] row) {
        for(int i = 0; i < row.length; i++) {
            System.out.print(row[i] + " ");
        }
        System.out.println();
    }

}
