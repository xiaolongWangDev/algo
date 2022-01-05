package preprocess;

public class BiggestSquare {
    @SuppressWarnings("DuplicatedCode")
    public int find(int[][] matrix) {
        int height = matrix.length;
        if (height <= 0) return 0;
        int width = matrix[0].length;
        int[][] rightReach = new int[height][width];
        for (int i = 0; i < height; i++) {
            int l = 0;
            for (int j = width - 1; j >= 0; j--) {
                if (matrix[i][j] == 0) {
                    l = 0;
                } else {
                    l++;
                }
                rightReach[i][j] = l;
            }
        }

        int[][] bottomReach = new int[height][width];
        for (int j = 0; j < height; j++) {
            int l = 0;
            for (int i = height - 1; i >= 0; i--) {
                if (matrix[i][j] == 0) {
                    l = 0;
                } else {
                    l++;
                }
                bottomReach[i][j] = l;
            }
        }

        int max = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int maxPotential = Math.min(rightReach[i][j], bottomReach[i][j]);
                if (maxPotential <= max) {
                    continue;
                }
                for (int l = maxPotential; l > 0; l--) {
                    if (rightReach[i + l - 1][j] < l || bottomReach[i][j + l - 1] < l) {
                        continue;
                    }
                    max = Math.max(max, l);
                    break;
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        BiggestSquare algo = new BiggestSquare();
        int[][] matrix = new int[][]{
                {1, 1, 1, 1, 1, 1},
                {1, 1, 0, 0, 1, 1},
                {0, 1, 0, 0, 1, 1},
                {0, 1, 1, 1, 1, 1},
                {0, 1, 0, 1, 1, 1},
        };
        System.out.println(algo.find(matrix));
    }
}
