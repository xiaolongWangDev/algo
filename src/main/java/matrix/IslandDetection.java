package matrix;

public class IslandDetection {

    public int detect(int[][] nodeMatrix) {
        int count = 0;
        for (int i = 0; i < nodeMatrix.length; i++) {
            for (int j = 0; j < nodeMatrix[i].length; j++) {
                if (nodeMatrix[i][j] == 1) {
                    infect(nodeMatrix, i, j, nodeMatrix.length, nodeMatrix[i].length);
                    count++;
                }
            }
        }
        return count;
    }

    private void infect(int[][] nodeMatrix, int i, int j, int height, int width) {
        if (i < 0 || i >= height || j < 0 || j >= width || nodeMatrix[i][j] != 1) {
            return;
        }
        nodeMatrix[i][j] = 2;
        infect(nodeMatrix, i - 1, j, height, width);
        infect(nodeMatrix, i + 1, j, height, width);
        infect(nodeMatrix, i, j - 1, height, width);
        infect(nodeMatrix, i, j + 1, height, width);
    }

    public static void main(String[] args) {

        int[][] testMatrix = {
                {0, 1, 0, 0, 0},
                {0, 1, 1, 0, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 1, 0},
                {1, 0, 0, 0, 0}
        };

        System.out.println(new IslandDetection().detect(testMatrix));
    }
}
