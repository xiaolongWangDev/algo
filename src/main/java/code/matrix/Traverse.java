package code.matrix;

import java.util.function.Consumer;

public class Traverse {
    public void spiralOrder(int[][] matrix, Consumer<Integer> consumer) {
        int n = matrix.length;
        if (n == 0) return;
        int m = matrix[0].length;
        int topLeftX = 0;
        int topLeftY = 0;
        int bottomRightX = n - 1;
        int bottomRightY = m - 1;
        while (topLeftX <= bottomRightX && topLeftY <= bottomRightY) {
            traverseLoop(matrix, topLeftX, topLeftY, bottomRightX, bottomRightY, consumer);
            topLeftX++;
            topLeftY++;
            bottomRightX--;
            bottomRightY--;
        }
    }

    public void zigzagOrder(int[][] matrix, Consumer<Integer> consumer) {
        int n = matrix.length;
        if (n == 0) return;
        int m = matrix[0].length;
        int aX = 0;
        int aY = 0;
        int bX = 0;
        int bY = 0;
        boolean up = false;
        while (aX < n && bY < m) {
            traverseDiagonal(matrix, aX, aY, bX, bY, up, consumer);
            if (aY == m - 1) {
                aX++;
            } else {
                aY++;
            }
            if (bX == n - 1) {
                bY++;
            } else {
                bX++;
            }
            up = !up;
        }
    }


    private void traverseLoop(int[][] matrix, int topLeftX, int topLeftY, int bottomRightX, int bottomRightY, Consumer<Integer> consumer) {
        if (topLeftX == bottomRightX) {
            for (int i = topLeftY; i <= bottomRightY; i++) {
                consumer.accept(matrix[topLeftX][i]);
            }
        } else if (topLeftY == bottomRightY) {
            for (int i = topLeftX; i <= bottomRightX; i++) {
                consumer.accept(matrix[i][topLeftY]);
            }
        } else {
            for (int i = topLeftY; i < bottomRightY; i++) {
                consumer.accept(matrix[topLeftX][i]);
            }
            for (int i = topLeftX; i < bottomRightX; i++) {
                consumer.accept(matrix[i][bottomRightY]);
            }
            for (int i = bottomRightY; i > topLeftY; i--) {
                consumer.accept(matrix[bottomRightX][i]);
            }
            for (int i = bottomRightX; i > topLeftX; i--) {
                consumer.accept(matrix[i][topLeftY]);
            }
        }
    }

    private void traverseDiagonal(int[][] matrix, int aX, int aY, int bX, int bY, boolean up, Consumer<Integer> consumer) {
        for (int i = 0; i <= bX - aX; i++) {
            if (up) {
                consumer.accept(matrix[bX - i][bY + i]);
            } else {
                consumer.accept(matrix[aX + i][aY - i]);
            }
        }
    }

    public static void main(String[] args) {
        Traverse algo = new Traverse();
        int N = 5;
        int M = 6;
        int[][] matrix = new int[N][M];
        int num = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrix[i][j] = num++;
                System.out.printf("%3d", matrix[i][j]);
            }
            System.out.println();
        }

        MyPrinter printer = new MyPrinter(20);
//        algo.spiralOrder(matrix, printer::print);
        algo.zigzagOrder(matrix, printer::print);
    }

    private static class MyPrinter {
        int count = 0;
        final int n;

        public MyPrinter(int n) {
            this.n = n;
        }

        void print(int value) {
            count++;
            System.out.printf("%3d", value);
            if (count % n == 0) {
                System.out.println();
            }
        }
    }
}
