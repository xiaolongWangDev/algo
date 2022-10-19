package practice.leetcode;

public class L62UniquePaths {


    public static int uniquePaths1(int m, int n) {
        Integer[][] memo = new Integer[m][n];
        return rec(0, 0, memo, m, n);
    }

    public static int rec(int x, int y, Integer[][] memo, int m, int n) {
        if (x == m - 1 && y == n - 1) return 1;
        if (memo[x][y] != null) return memo[x][y];

        int temp = 0;
        if (x + 1 < m) {
            temp += rec(x + 1, y, memo, m, n);
        }
        if (y + 1 < n) {
            temp += rec(x, y + 1, memo, m, n);
        }
        memo[x][y] = temp;
        return temp;
    }

    public static int uniquePaths(int m, int n) {
        return combNumber(m + n - 2, m - 1);
    }

    public static int combNumber(int all, int take) {
        take = Math.max(all - take, take);
        double dividend = 1d;
        double divisor = 1d;
        int count = 1;
        for (int i = all; i > take; i--) {
            dividend *= i;
            divisor *= count;
            count++;
        }
        return (int) (dividend / divisor);
    }

    public static void main(String[] args) {
//        for (int i = 1; i < 10; i++) {
//            for (int j = 1; j < 10; j++) {
//                //  C(i+j-2, i-1);
////                System.out.printf("%10d", combNumber(i + j - 2, i - 1));
//                System.out.printf("%10d", uniquePaths(i, j));
//            }
//            System.out.println();
//        }

        combNumber(30, 15);
    }
}
