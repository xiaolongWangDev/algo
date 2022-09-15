package practice.leetcode;

import java.util.ArrayList;
import java.util.List;

public class L51NQueens {
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();

        solveRow(0, n, new ArrayList<>(), result);

        return result;
    }

    public static void solveRow(int row, int n, List<Integer> usedCols, List<List<String>> result) {
        if (row == n) {
            result.add(toStrings(usedCols));
        }
        for (int i = 0; i < n; i++) {
            if (isValid(row, i, usedCols)) {
                usedCols.add(i);
                solveRow(row + 1, n, usedCols, result);
                usedCols.remove(usedCols.size() - 1);
            }
        }
    }


    private static boolean isValid(int curRow, int i, List<Integer> usedCols) {
        for (int row = 0; row < usedCols.size(); row++) {
            if (usedCols.get(row) == i) return false;
            if (curRow - row == Math.abs(i - usedCols.get(row))) return false;
        }
        return true;
    }

    private static List<String> toStrings(List<Integer> colOnRow) {
        List<String> r = new ArrayList<>();
        for (int col : colOnRow) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < colOnRow.size(); i++) {
                sb.append(i == col ? 'Q' : '.');
            }
            r.add(sb.toString());
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
    }
}
