package practice.leetcode;

public class L36ValidSudoku {
    public static boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] columns = new boolean[9][9];
        boolean[][] zones = new boolean[9][9];

        for (int i = 0; i < board.length; i++) {
            char[] row = board[i];
            for (int j = 0; j < row.length; j++) {
                char c = row[j];
                if (c == '.') continue;
                int digit = c - '1';

                if (rows[i][digit]) return false;
                rows[i][digit] = true;
                if (columns[j][digit]) return false;
                columns[j][digit] = true;
                int zone = 3 * (j / 3) + (i / 3);
                if (zones[zone][digit]) return false;
                zones[zone][digit] = true;
            }
        }
        return true;
    }

    public static boolean isValidSudokuSaveMemory(char[][] board) {
        // check per row
        for (int i = 0; i < 9; i++) {
            boolean[] memo = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (!checkAndSet(memo, board[i][j])) return false;
            }
        }

        // check per col
        for (int j = 0; j < 9; j++) {
            boolean[] memo = new boolean[9];
            for (int i = 0; i < 9; i++) {
                if (!checkAndSet(memo, board[i][j])) return false;
            }
        }

        // check per zone
        for (int z = 0; z < 9; z++) {
            boolean[] memo = new boolean[9];
            for (int n = 0; n < 9; n++) {
                int i = 3 * (z / 3) + n / 3;
                int j = 3 * (z % 3) + n % 3;
                if (!checkAndSet(memo, board[i][j])) return false;
            }
        }

        return true;
    }

    private static boolean checkAndSet(boolean[] memo, char c) {
        if (c != '.') {
            if (memo[c - '1']) return false;
            memo[c - '1'] = true;
        }
        return true;
    }

}
