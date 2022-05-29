package code.dp;

public class Problem_Snake {
    public Integer dp(int[][] weights) {
        int columns = weights.length;
        if (columns == 0) return 0;
        int columnHeight = weights[0].length;
        Integer[][] memo = new Integer[columns][columnHeight];
        Integer[][] flippedMemo = new Integer[columns][columnHeight];
        Integer max = null;
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < columnHeight; j++) {
                if (i == 0) {
                    if (weights[i][j] >= 0) {
                        memo[i][j] = weights[i][j];
                        flippedMemo[i][j] = weights[i][j];
                    } else {
                        memo[i][j] = null;
                        flippedMemo[i][j] = -weights[i][j];
                    }
                } else {
                    Integer prevMaxNoFlip = maxFromNeighbours(memo[i - 1], j);
                    Integer prevMaxFlipped = maxFromNeighbours(flippedMemo[i - 1], j);
                    if (weights[i][j] >= 0) {
                        memo[i][j] = prevMaxNoFlip == null ? null : weights[i][j] + prevMaxNoFlip;
                        flippedMemo[i][j] = prevMaxFlipped == null ? null : weights[i][j] + prevMaxFlipped;
                    } else {
                        if (prevMaxNoFlip == null) {
                            memo[i][j] = null;
                        } else {
                            if (weights[i][j] + prevMaxNoFlip >= 0) {
                                memo[i][j] = weights[i][j] + prevMaxNoFlip;
                            } else {
                                memo[i][j] = null;
                            }
                        }
                        if (prevMaxNoFlip != null && prevMaxFlipped != null) {
                            flippedMemo[i][j] = Math.max(weights[i][j] + prevMaxFlipped, -weights[i][j] + prevMaxNoFlip);
                        } else if (prevMaxNoFlip != null) {
                            flippedMemo[i][j] = -weights[i][j] + prevMaxNoFlip;
                        } else if (prevMaxFlipped != null) {
                            flippedMemo[i][j] = weights[i][j] + prevMaxFlipped;
                        } else {
                            flippedMemo[i][j] = null;
                        }
                    }
                }

                if (max == null || (flippedMemo[i][j] != null && max < flippedMemo[i][j])) {
                    max = flippedMemo[i][j];
                }
                if (max == null || (memo[i][j] != null && max < memo[i][j])) {
                    max = memo[i][j];
                }
            }
        }
        return max;
    }

    private Integer maxFromNeighbours(Integer[] weights, int j) {
        Integer max = null;
        for (int i = j - 1; i <= j + 1; i++) {
            if (i >= 0 && i < weights.length) {
                Integer weight = weights[i];
                if (max == null || (weight != null && max < weight)) {
                    max = weight;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Problem_Snake p = new Problem_Snake();
        int[][] input = new int[][]{
                {1, 2, 3, 4, 5},
                {1, 2, 3, 4, 5},
                {-10, -10, -10, -10, -10},
                {500, 200, 300, 400, 100},
                {-1000, -1000, -1000, -1000, -1000},
        };
        System.out.println(p.dp(input));
    }
}
