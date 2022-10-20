package practice.leetcode;

import java.util.ArrayList;
import java.util.List;

public class L54SpiralMatrix {
    // x goes downwards
    // y goes right
    static class Coord {
        int x;
        int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Coord topLeft = new Coord(0, 0);
        Coord botRight = new Coord(m - 1, n - 1);
        List<Integer> result = new ArrayList<>();
        while (topLeft.x <= botRight.x && topLeft.y <= botRight.y) {
            addLoop(topLeft, botRight, matrix, result);
            topLeft.x++;
            topLeft.y++;
            botRight.x--;
            botRight.y--;
        }

        return result;
    }

    private static void addLoop(Coord topLeft, Coord botRight, int[][] matrix, List<Integer> results) {
        if (topLeft.x == botRight.x) {
            for (int i = topLeft.y; i <= botRight.y; i++) {
                results.add(matrix[topLeft.x][i]);
            }
        } else if (topLeft.y == botRight.y) {
            for (int i = topLeft.x; i <= botRight.x; i++) {
                results.add(matrix[i][topLeft.y]);
            }
        } else {
            for (int i = topLeft.y; i < botRight.y; i++) {
                results.add(matrix[topLeft.x][i]);
            }
            for (int i = topLeft.x; i < botRight.x; i++) {
                results.add(matrix[i][botRight.y]);
            }
            for (int i = botRight.y; i > topLeft.y; i--) {
                results.add(matrix[botRight.x][i]);
            }
            for (int i = botRight.x; i > topLeft.x; i--) {
                results.add(matrix[i][topLeft.y]);
            }
        }
    }
}
