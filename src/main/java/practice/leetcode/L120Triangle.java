package practice.leetcode;

import java.util.Arrays;
import java.util.List;

public class L120Triangle {
    /**
     * 0 1 2 3 4 5
     * 0 x
     * 1 a b
     * 2 c d e
     * 3
     * 4
     * 5
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        Integer[] dp = new Integer[triangle.get(triangle.size() - 1).size()];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {
            for (int j = triangle.get(i).size() - 1; j >= 0; j--) {
                int min = Integer.MAX_VALUE;
                if (j - 1 >= 0) {
                    min = Math.min(min, dp[j - 1]);
                }
                if(dp[j] != null) {
                    min = Math.min(min, dp[j]);
                }
                dp[j] = min + triangle.get(i).get(j);
            }
        }

        return Arrays.stream(dp).min(Integer::compareTo).get();
    }

    public static void main(String[] args) {
        System.out.println(minimumTotal(List.of(
                List.of(2),
                List.of(3,4),
                List.of(6,5,7),
                List.of(4,1,8,3)
        )));
    }
}
