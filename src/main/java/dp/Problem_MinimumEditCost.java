package dp;

public class Problem_MinimumEditCost {
    public int dp(String src, String target, int iCost, int dCost, int rCost) {
        int[][] memo = new int[src.length() + 1][target.length() + 1];
        for (int i = 0; i <= src.length(); i++) {
            for (int j = 0; j <= target.length(); j++) {
                if (i == 0 && j == 0) {
                    memo[i][j] = 0;
                } else if (i == 0) {
                    memo[i][j] = (int) Math.pow(iCost, j);
                } else if (j == 0) {
                    memo[i][j] = (int) Math.pow(dCost, i);
                } else {
                    if (src.charAt(i - 1) == target.charAt(j - 1)) {
                        memo[i][j] = memo[i - 1][j - 1];
                    } else {
                        memo[i][j] = Math.min(
                                memo[i - 1][j - 1] + rCost,
                                Math.min(
                                        memo[i - 1][j] + dCost,
                                        memo[i][j - 1] + iCost
                                )
                        );
                    }
                }
            }
        }

        return memo[src.length()][target.length()];
    }

    public static void main(String[] args) {
        Problem_MinimumEditCost p = new Problem_MinimumEditCost();
        System.out.println(p.dp("abc", "adc", 5, 3, 2));
        System.out.println(p.dp("abc", "adc", 5, 3, 100));
        System.out.println(p.dp("abc", "abc", 5, 3, 2));
    }
}
