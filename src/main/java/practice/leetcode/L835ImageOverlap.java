package practice.leetcode;

// pure simulation, no tricks
public class L835ImageOverlap {
    public static int largestOverlap(int[][] img1, int[][] img2) {
        int max = 0;
        int n = img1.length;
        for (int x = -n + 1; x < n; x++) {
            for (int y = -n + 1; y < n; y++) {
                max = Math.max(max, match(img1, img2, x, y));
            }
        }

        return max;
    }

    private static int match(int[][] img1, int[][] img2, int x, int y) {
        int count = 0;
        int n = img2.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int translatedI = i - x;
                int translatedJ = j - y;
                if (translatedI >= 0 && translatedI < n && translatedJ >= 0 && translatedJ < n) {
                    if (img2[i][j] == 1 && img1[translatedI][translatedJ] == 1) count++;

                }
            }
        }
        return count;
    }
}
