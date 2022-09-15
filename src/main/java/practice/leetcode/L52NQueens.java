package practice.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L52NQueens {
    public static int totalNQueens(int n) {
        int[] used = new int[n];
        Arrays.fill(used, -1);
        return solveRow(0, used);
    }

    public static int solveRow(int row, int[] used) {
        if (row == used.length) {
            return 1;
        }
        int total = 0;
        for (int i = 0; i < used.length; i++) {
            if (isValid(row, i, used)) {
                used[i] = row;
                total += solveRow(row + 1, used);
                used[i] = -1;
            }
        }

        return total;
    }
    private static boolean isValid(int row, int i, int[] used) {
        if (used[i] != -1) return false;
        for (int col = 0; col < used.length; col++) {
            if (used[col] > -1) {
                if (row - used[col] == Math.abs(i - col)) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(totalNQueens(8));
    }
}
