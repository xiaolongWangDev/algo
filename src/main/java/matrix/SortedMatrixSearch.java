package matrix;

public class SortedMatrixSearch {
    public boolean contains(int[][] matrix, int target) {
        int N = matrix.length;
        if (N == 0) return false;
        int M = matrix[0].length;
        int i = 0;
        int j = M - 1;
        while (i < N && j >= 0) {
            if (target == matrix[i][j]) return true;
            if (target > matrix[i][j]) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SortedMatrixSearch algo = new SortedMatrixSearch();
        int[][] matrix = {
                {1, 5, 9, 10},
                {2, 6, 12, 13},
                {7, 9, 15, 17}
        };
        System.out.println(algo.contains(matrix, 7));
        System.out.println(algo.contains(matrix, 15));
        System.out.println(algo.contains(matrix, 8));
    }
}
