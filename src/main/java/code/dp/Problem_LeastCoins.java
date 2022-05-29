package code.dp;

public class Problem_LeastCoins {

    public int simpleRec(final int[] VALUES, int cur, int balance) {
        if (balance < 0) {
            return -1;
        }
        if (cur == VALUES.length) {
            return balance == 0 ? 0 : -1;
        } else {
            int res1 = simpleRec(VALUES, cur + 1, balance);
            int res2 = simpleRec(VALUES, cur + 1, balance - VALUES[cur]);
            if (res1 != -1 && res2 != -1) {
                return Math.min(res1, 1 + res2);
            } else if (res1 != -1) {
                return res1;
            } else if (res2 != -1) {
                return 1 + res2;
            } else {
                return -1;
            }
        }
    }

    public int memoized(final int[] VALUES, int cur, int balance) {
        int[][] memo = DpUtils.get2DMemo(VALUES.length + 1, balance + 1);
        return memoizedRec(VALUES, cur, balance, memo);
    }

    public int memoizedRec(final int[] VALUES, int cur, int balance, int[][] memo) {
        if (balance < 0) {
            return -1;
        }
        if (memo[cur][balance] == -1) {
            if (cur == VALUES.length) {
                memo[cur][balance] = balance == 0 ? 0 : -1;
            } else {
                int res1 = simpleRec(VALUES, cur + 1, balance);
                int res2 = simpleRec(VALUES, cur + 1, balance - VALUES[cur]);
                if (res1 != -1 && res2 != -1) {
                    memo[cur][balance] = Math.min(res1, 1 + res2);
                } else if (res1 != -1) {
                    memo[cur][balance] = res1;
                } else if (res2 != -1) {
                    memo[cur][balance] = 1 + res2;
                } else {
                    memo[cur][balance] = -1;
                }
            }
        }
        return memo[cur][balance];
    }

    /*
    cur/balance     0  1  2  3  4  5  6  7  8  9 10
    0(2)                                          *
    1(7)            0 -1 -1  1 -1  1  2  1  2 -1  2
    2(3)            0 -1 -1  1 -1  1  2 -1  2 -1 -1
    3(5)            0 -1 -1  1 -1  1 -1 -1  2 -1 -1
    4(3)            0 -1 -1  1 -1 -1 -1 -1 -1 -1 -1
    5               0 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
     */
    public int induction(final int[] VALUES, int targetCur, int targetBalance) {
        int[][] memo = new int[VALUES.length + 1][targetBalance + 1];
        for (int cur = VALUES.length; cur >= 0; cur--) {
            for (int balance = 0; balance <= targetBalance; balance++) {
                if (cur == VALUES.length) {
                    memo[cur][balance] = balance == 0 ? 0 : -1;
                } else {
                    int res1 = memo[cur + 1][balance];
                    int res2 = balance >= VALUES[cur] ? memo[cur + 1][balance - VALUES[cur]] : -1;
                    //noinspection DuplicatedCode
                    if (res1 != -1 && res2 != -1) {
                        memo[cur][balance] = Math.min(res1, 1 + res2);
                    } else if (res1 != -1) {
                        memo[cur][balance] = res1;
                    } else if (res2 != -1) {
                        memo[cur][balance] = 1 + res2;
                    } else {
                        memo[cur][balance] = -1;
                    }
                }
            }
        }
        return memo[targetCur][targetBalance];
    }

    public int inductionMemoryEfficient(final int[] VALUES, int targetBalance) {
        int[] memo = null;
        int[] temp = new int[targetBalance + 1];
        for (int cur = VALUES.length; cur >= 0; cur--) {
            for (int balance = 0; balance <= targetBalance; balance++) {
                if (cur == VALUES.length) {
                    temp[balance] = balance == 0 ? 0 : -1;
                } else {
                    int res1 = memo[balance];
                    int res2 = balance >= VALUES[cur] ? memo[balance - VALUES[cur]] : -1;
                    //noinspection DuplicatedCode
                    if (res1 != -1 && res2 != -1) {
                        temp[balance] = Math.min(res1, 1 + res2);
                    } else if (res1 != -1) {
                        temp[balance] = res1;
                    } else if (res2 != -1) {
                        temp[balance] = 1 + res2;
                    } else {
                        temp[balance] = -1;
                    }
                }
            }
            memo = temp;
            temp = new int[targetBalance + 1];
        }
        return memo[targetBalance];
    }

    //0 1 2 3 4
    public static void main(String[] args) {
        Problem_LeastCoins algo = new Problem_LeastCoins();
        final int[] VALUES = {2, 7, 3, 5, 3};
        int balance = 10;
        System.out.println(algo.simpleRec(VALUES, 0, balance));
        System.out.println(algo.memoized(VALUES, 0, balance));
        System.out.println(algo.induction(VALUES, 0, balance));
        System.out.println(algo.inductionMemoryEfficient(VALUES, balance));
    }


}
