package practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L59SpiralMatrix2 {

    static class Coord {
        int x;
        int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        L54SpiralMatrix.Coord topLeft = new L54SpiralMatrix.Coord(0, 0);
        L54SpiralMatrix.Coord botRight = new L54SpiralMatrix.Coord(n - 1, n - 1);
        int startNumber = 1;
        while (topLeft.x <= botRight.x && topLeft.y <= botRight.y) {
            startNumber = populateLoop(topLeft, botRight, matrix, startNumber);
            topLeft.x++;
            topLeft.y++;
            botRight.x--;
            botRight.y--;
        }

        return matrix;
    }

    private static int populateLoop(L54SpiralMatrix.Coord topLeft, L54SpiralMatrix.Coord botRight, int[][] matrix, int startNumber) {
        if (topLeft.x == botRight.x) {
            matrix[topLeft.x][topLeft.y] = startNumber++;
        } else {
            for (int i = topLeft.y; i < botRight.y; i++) {
                matrix[topLeft.x][i] = startNumber++;
            }
            for (int i = topLeft.x; i < botRight.x; i++) {
                matrix[i][botRight.y] = startNumber++;
            }
            for (int i = botRight.y; i > topLeft.y; i--) {
                matrix[botRight.x][i] = startNumber++;
            }
            for (int i = botRight.x; i > topLeft.x; i--) {
                matrix[i][topLeft.y] = startNumber++;
            }
        }
        return startNumber;
    }

    public static void main(String[] args) {
        var res = generateMatrix(4);
        for(var row: res) {
            System.out.println(Arrays.toString(row));
        }
    }

}
