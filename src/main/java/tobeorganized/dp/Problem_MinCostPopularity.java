package tobeorganized.dp;

public class Problem_MinCostPopularity {
    public int findNaive(int currentPopularity, int targetPopularity, int aCost, int bCost, int cCost) {

        int costLimit = findCostLimit(currentPopularity, targetPopularity, aCost, bCost, cCost);
        return findNaive(currentPopularity, 0, costLimit, targetPopularity, aCost, bCost, cCost);
    }

    private int findCostLimit(int currentPopularity, int targetPopularity, int aCost, int bCost, int cCost) {
        // first to need to come up with a trivial solution to prevent our program from diving deep indefinitely
        int costLimit;
        //if targetPopularity - currentPopularity is even, just, using +2 all the way
        if ((targetPopularity - currentPopularity) % 2 == 0) {
            return aCost * (targetPopularity - currentPopularity) / 2;
        } else {
            // targetPopularity is Odd and currentPopularity is even, there's no solution. because all of our ops can
            // only produce even number
            if (targetPopularity % 2 == 1) {
                return -1;
            } else {
                // targetPopularity is even and currentPopularity is odd
                // we first *2 to make it even, and then use +2 or -2 to move it to target
                if (2 * currentPopularity > targetPopularity) {
                    costLimit = bCost + cCost * (2 * currentPopularity - targetPopularity) / 2;
                } else {
                    costLimit = bCost + aCost * (targetPopularity - 2 * currentPopularity) / 2;
                }
            }
        }
        return costLimit;
    }

    public Integer findNaive(int currentPopularity, int cumulatedCost, int costLimit, int targetPopularity, int aCost, int bCost, int cCost) {
        if (cumulatedCost > costLimit) {
            // already surplus a trivial solution, it's definitely not the best solution
            return null;
        }
        if (currentPopularity < 0) {
            return null;
        }
        if (currentPopularity == targetPopularity) {
            return 0;
        }

        Integer aResult = findNaive(currentPopularity + 2, cumulatedCost + aCost, costLimit
                , targetPopularity, aCost, bCost, cCost);
        Integer bResult = findNaive(currentPopularity * 2, cumulatedCost + bCost, costLimit, targetPopularity, aCost, bCost, cCost);
        Integer cResult = findNaive(currentPopularity - 2, cumulatedCost + cCost, costLimit, targetPopularity, aCost, bCost, cCost);

        if (aResult == null && bResult == null && cResult == null) {
            return null;
        } else {
            int costUsingA = aResult == null ? Integer.MAX_VALUE : aCost + aResult;
            int costUsingB = bResult == null ? Integer.MAX_VALUE : bCost + bResult;
            int costUsingC = cResult == null ? Integer.MAX_VALUE : cCost + cResult;
            return Math.min(costUsingA, Math.min(costUsingB, costUsingC));
        }
    }

    /*
        let aCost = 1, bCost = 2, cCost = 3
        let start popularity = 1
        let target popularity = 8
        let costLimit = 8
        popularity \ cost 0 1 2 3 4 5 6 7 8 9
        -1                n n n n n n n n n n
        0                                 n n
        1                                 n n
        2                                 n n
        3                                 n n
        4                             2   n n
        5                                 n n
        6                               1 n n
        7                                 n n
        8                 0 0 0 0 0 0 0 0 0 n
        9                                 n n
        10                          3     n n
        11                                n n
        12                                n n
        13                                n n
        14
        15
     */
    public Integer findDp(int currentPopularity, int targetPopularity, int aCost, int bCost, int cCost) {
        int costLimit = findCostLimit(currentPopularity, targetPopularity, aCost, bCost, cCost);
        Info[][] memo = new Info[costLimit + 1][targetPopularity * 2];
        for (int i = costLimit; i >= 0; i--) {
            for (int j = 0; j < targetPopularity * 2; j++) {
                if (j == targetPopularity) {
                    memo[i][j] = new Info(0, "");
                } else {
                    Info aResult = valueOrNull(memo, i + aCost, j + 2);
                    Info bResult = valueOrNull(memo, i + bCost, j * 2);
                    Info cResult = valueOrNull(memo, i + cCost, j - 2);

                    if (aResult == null && bResult == null && cResult == null) {
                        memo[i][j] = null;
                    } else {
                        int costUsingA = aResult == null ? Integer.MAX_VALUE : aCost + aResult.cost;
                        int costUsingB = bResult == null ? Integer.MAX_VALUE : bCost + bResult.cost;
                        int costUsingC = cResult == null ? Integer.MAX_VALUE : cCost + cResult.cost;
                        int min = Math.min(costUsingA, Math.min(costUsingB, costUsingC));
                        memo[i][j] = new Info(min,
                                min == costUsingA ?
                                        "+2, " + aResult.ops :
                                        min == costUsingB ?
                                                "*2, " + bResult.ops :
                                                "-2, " + cResult.ops
                        );
                    }
                }
            }
        }

//        for (int i = 0; i < costLimit + 1; i++) {
//            for (int j = 0; j < targetPopularity * 2; j++) {
//                System.out.printf("%4d", memo[i][j] == null ? -1 : memo[i][j].cost);
//            }
//            System.out.println();
//        }

        System.out.println(memo[0][currentPopularity].ops);
        return memo[0][currentPopularity].cost;
    }

    private static class Info {
        Integer cost;
        String ops;

        public Info(Integer cost, String ops) {
            this.cost = cost;
            this.ops = ops;
        }
    }

    private Info valueOrNull(Info[][] memo, int i, int j) {
        if (memo.length == 0 || i < 0 || i >= memo.length || j < 0 || j >= memo[0].length) return null;
        return memo[i][j];
    }

    public static void main(String[] args) {
        Problem_MinCostPopularity p = new Problem_MinCostPopularity();
        System.out.println(p.findDp(3, 100, 1, 2, 6));
    }
}
