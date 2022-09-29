package practice.leetcode;

public class L1680ConcatenationOfConsecutiveBinaryNumbers {
    public static int concatenatedBinary(int n) {
        long multiplier = 1L;
        long sum = 0;
        for (int i = n; i > 0; i--) {
            int quotient = i;
            while (quotient != 0) {
                int rem = quotient % 2;
                quotient = quotient >> 1;
                sum += rem * multiplier;
                sum = sum % 1_000_000_007;
                multiplier = multiplier << 1;
                multiplier = multiplier % 1_000_000_007;
            }
        }
        return (int) sum;
    }

    public static int concatenatedBinaryBitHint(int n) {
        long sum = 0;
        int digits = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) digits++;
            sum <<= digits;
            sum %= 1_000_000_007;
            sum = sum + i;
            sum %= 1_000_000_007;
        }
        return (int) sum;
    }

    /**
     * * first the induction formula is:
     * * f(k) = (2^m)*f(k-1) + k
     * * m(k) = floor(log(k)/log(2)) + 1
     * * e.g. f(3) = 2^2 * f(2) + 3
     * *
     * * we could write this into a matrix format:
     * * [f(k), k+1, 1] = [f(k-1), k, 1] * |2^m 0 0|
     * *                                   |1   1 0|
     * *                                   |0   1 1|
     * * we'll get the following:
     * * f(k) = (2^m)*f(k-1) + 1*k + 0*1
     * * k+1 = 0*f(k-1) + 1*k + 1*1
     * * 1 = 0*f(k-1) + 0*k + 1*1
     * * So, we can get the general form:
     * * [f(n), n+1, 1] = [f(1), 2, 1] * |2^m 0 0| *...n-1 times with m changing
     * *                                 |1   1 0|
     * *                                 |0   1 1|
     * *
     */
    public static int concatenatedBinaryMatrixOptimization(int n) {
        if (n == 1) return 1;
        int[] initVector = {1, 2, 1};
        int timeToSelfMultiply = 1;
        int remainingIterations = n - 1;

        int[][] transferMatrix = {
                {2, 0, 0},
                {1, 1, 0},
                {0, 1, 1}
        };

        int[][] culTransferMatrix = new int[3][3];
        for (int i = 0; i < 3; i++) {
            culTransferMatrix[i][i] = 1;
        }
        while (remainingIterations > 0) {
            /**
             * 2 3 4 5 6 7 8 9 10 11 12
             * 2 2 3 3 3 3 4 4 4  4  4
             */
            timeToSelfMultiply = Math.min(remainingIterations, timeToSelfMultiply * 2);

            // multiply m by 2
            transferMatrix[0][0] <<= 1;
            transferMatrix[0][0] = transferMatrix[0][0] % 1_000_000_007;
            culTransferMatrix = matrixMul(culTransferMatrix, selfMul(transferMatrix, timeToSelfMultiply));
            remainingIterations -= timeToSelfMultiply;
        }

        long sum = 0;
        for (int i = 0; i < 3; i++) {
            sum = (sum + initVector[i] * culTransferMatrix[i][0]) % 1_000_000_007;
        }

        return (int)sum;
    }

    private static int[][] selfMul(int[][] m, int n) {
        int rows = m.length;
        int cols = m[0].length;
        if (rows != cols) throw new RuntimeException("can only self multiply square matrix");
        int[][] prod = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            prod[i][i] = 1;
        }
        int quotient = n;
        int[][] factor = m;
        while (quotient != 0) {
            // same as quotient % 2 == 1, but faster
            if ((quotient & 1) == 1) {
                prod = matrixMul(prod, factor);
            }
            quotient >>>= 1;
            factor = matrixMul(factor, factor);
        }
        return prod;
    }

    private static int[][] matrixMul(int[][] a, int[][] b) {
        int rows = a.length;
        int columns = b[0].length;
        if (a[0].length != b.length) throw new RuntimeException("a's columns need to match b's rows");
        int pairs = a[0].length;
        int[][] res = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                for (int k = 0; k < pairs; k++) {
                    int addition = (int) (((long) a[i][k]) * b[k][j] % 1_000_000_007);
                    res[i][j] = (res[i][j] + addition) % 1_000_000_007;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        for(int i = 10; i < 20; i++) {
            System.out.println(concatenatedBinaryBitHint(i));
            System.out.println(concatenatedBinaryMatrixOptimization(i));

        }
//        System.out.println(concatenatedBinaryMatrixOptimization(12));
//        System.out.println(concatenatedBinaryBitHint(5));
//        System.out.println(concatenatedBinaryBitHint(12));
//        System.out.println(concatenatedBinaryMatrixOptimization(5));
//        System.out.println(concatenatedBinaryMatrixOptimization(12));
    }
}

