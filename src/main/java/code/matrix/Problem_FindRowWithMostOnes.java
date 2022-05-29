package code.matrix;

public class Problem_FindRowWithMostOnes {
    public int find(int[][] matrix) {
        int N = matrix.length;
        if (N == 0) return -1;
        int M = matrix[0].length;
        int i = 0;
        int j = M - 1;
        int max = 0;
        int current = 0;
        int maxRow = 0;
        while (i < N && j >= 0) {
            if (matrix[i][j] == 1) {
                current++;
                j--;
            } else {
                if (max < current) {
                    max = current;
                    maxRow = i;
                }
                i++;
            }
        }
        return j == -1 ? i : maxRow;
    }

    public static void main(String[] args) {
        Problem_FindRowWithMostOnes algo = new Problem_FindRowWithMostOnes();
        int[][] matrix = {
                {0, 0, 0, 0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        System.out.println(algo.find(matrix));
    }
}
