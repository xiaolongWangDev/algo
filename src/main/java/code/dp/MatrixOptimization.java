package code.dp;

public class MatrixOptimization {
    public int f(int n, int[] initVector, int[][] transferMatrix) {
        if(n <= initVector.length) {
            return initVector[initVector.length - n];
        }

        // [fk, ..., f2, f1] = [fk + 1, ..., f2] * transferMatrix
        // [fn, ..., f(n - k + 2), f(n - k + 1)] = [fk, ..., f1] * transferMatrix^(n - k)
        int[][] cumulatedMatrix = selfMultiply(transferMatrix, n - initVector.length);
        int sum = 0;
        for (int i = 0; i < initVector.length; i++) {
            sum += initVector[i] * cumulatedMatrix[i][0];
        }
        return sum;
    }

    private int[][] selfMultiply(int[][] transferMatrix, int power) {
        int N = transferMatrix.length;
        if (N <= 0) throw new IllegalArgumentException("cannot be empty");
        if (N != transferMatrix[0].length) throw new IllegalArgumentException("only support square matrix");
        int[][] result = new int[N][N];

        if (power == 1) return transferMatrix;

        for (int i = 0; i < N; i++) {
            result[i][i] = 1;
        }

        int[][] tmp = transferMatrix;
        while (power != 0) {
            if ((power & 1) == 1) {
                result = naiveMatrixMultiply(result, tmp);
            }
            tmp = naiveMatrixMultiply(tmp, tmp);
            power = power >> 1;
        }
        return result;
    }

    private int[][] naiveMatrixMultiply(int[][] a, int[][] b) {
        int N = a.length;

        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += a[i][k] * b[k][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MatrixOptimization algo = new MatrixOptimization();
        for (int i = 3; i < 20; i++) {
            System.out.println(algo.f(i, new int[]{1, 1}, new int[][]{
                    {1, 1},
                    {1, 0}
            }));
        }
    }
}

