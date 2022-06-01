package tobeorganized.matrix;

public class Rotation {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n == 0) return;
        if(matrix[0].length != n) throw new IllegalArgumentException("I need square");
        int topLeftX = 0;
        int topLeftY = 0;
        int bottomRightX = n - 1;
        int bottomRightY = n - 1;
        while (topLeftX <= bottomRightX && topLeftY <= bottomRightY) {
            rotateLoop(matrix, topLeftX, topLeftY, bottomRightX, bottomRightY);
            topLeftX++;
            topLeftY++;
            bottomRightX--;
            bottomRightY--;
        }
    }

    private void rotateLoop(int[][] matrix, int topLeftX, int topLeftY, int bottomRightX, int bottomRightY) {
        for (int i = 0; i < bottomRightY - topLeftY; i++) {
            int temp = matrix[topLeftX][topLeftY + i];
            matrix[topLeftX][topLeftY + i] = matrix[bottomRightX - i][topLeftY];
            matrix[bottomRightX - i][topLeftY] = matrix[bottomRightX][bottomRightY - i];
            matrix[bottomRightX][bottomRightY - i] = matrix[topLeftX + i][bottomRightY];
            matrix[topLeftX + i][bottomRightY] = temp;
        }
    }

    public static void main(String[] args) {
        Rotation algo = new Rotation();
        int N = 5;
        int[][] matrix = new int[N][N];
        int num = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = num++;
            }
        }
        print(matrix);
        System.out.println();

        algo.rotate(matrix);
        print(matrix);
    }

    private static void print(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%3d", val);
            }
            System.out.println();
        }
    }

}
