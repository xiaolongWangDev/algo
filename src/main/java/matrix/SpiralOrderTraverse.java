package matrix;

import java.util.function.Consumer;

public class SpiralOrderTraverse {
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

    public static void main(String[] args) {
        SpiralOrderTraverse algo = new SpiralOrderTraverse();
        int N = 5;
        int M = 5;
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
        algo.spiralOrder(matrix, printer::print);
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
