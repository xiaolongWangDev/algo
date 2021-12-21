package dp;

import static dp.DpUtils.*;

public class RobotWalk {

    public int simpleRec(final int LENGTH, final int TARGET_INDEX, int startIndex, int steps) {
        if (steps == 0) {
            return TARGET_INDEX == startIndex ? 1 : 0;
        }
        if (startIndex == 0) {
            return simpleRec(LENGTH, TARGET_INDEX, startIndex + 1, steps - 1);
        } else if (startIndex == LENGTH - 1) {
            return simpleRec(LENGTH, TARGET_INDEX, startIndex - 1, steps - 1);
        } else {
            return simpleRec(LENGTH, TARGET_INDEX, startIndex - 1, steps - 1)
                    + simpleRec(LENGTH, TARGET_INDEX, startIndex + 1, steps - 1);
        }
    }

    public int memoized(final int LENGTH, final int TARGET_INDEX, int startIndex, int steps) {
        int[][] memo = get2DMemo(LENGTH, steps + 1);
        return memoizedRec(LENGTH, TARGET_INDEX, startIndex, steps, memo);
    }

    public int memoizedRec(final int LENGTH, final int TARGET_INDEX, int startIndex, int steps, int[][] memo) {
        if (memo[startIndex][steps] == -1) {
            if (steps == 0) {
                memo[startIndex][steps] = TARGET_INDEX == startIndex ? 1 : 0;
            } else if (startIndex == 0) {
                memo[startIndex][steps] = simpleRec(LENGTH, TARGET_INDEX, startIndex + 1, steps - 1);
            } else if (startIndex == LENGTH - 1) {
                memo[startIndex][steps] =
                        simpleRec(LENGTH, TARGET_INDEX, startIndex - 1, steps - 1);
            } else {
                memo[startIndex][steps] =
                        simpleRec(LENGTH, TARGET_INDEX, startIndex - 1, steps - 1)
                                + simpleRec(LENGTH, TARGET_INDEX, startIndex + 1, steps - 1);
            }
        }
        return memo[startIndex][steps];
    }

    /*
    step/startIndex 0 1 2 3 4
    4                 *
    3               1 0 3 0 0
    2               0 1 0 2 0
    1               0 0 1 0 1
    0               0 0 0 1 0
     */
    public int induction(final int LENGTH, final int TARGET_INDEX, int startIndex, int steps) {
        int[][] memo = new int[steps + 1][LENGTH];
        for (int step = 0; step <= steps; step++) {
            for (int index = 0; index < LENGTH; index++) {
                if (step == 0) {
                    memo[step][index] = index == TARGET_INDEX ? 1 : 0;
                } else {
                    memo[step][index] = 0;
                    if (index - 1 >= 0) {
                        memo[step][index] += memo[step - 1][index - 1];
                    }
                    if (index + 1 <= LENGTH - 1) {
                        memo[step][index] += memo[step - 1][index + 1];
                    }
                }
            }
        }
        return memo[steps][startIndex];
    }

    public int inductionMemoryEfficient(final int LENGTH, final int TARGET_INDEX, int startIndex, int steps) {
        if (steps < 0) return 0; // make warning go away

        int[] memo = null;
        int[] temp = new int[LENGTH];
        for (int step = 0; step <= steps; step++) {
            for (int index = 0; index < LENGTH; index++) {
                if (step == 0) {
                    temp[index] = index == TARGET_INDEX ? 1 : 0;
                } else {
                    temp[index] = 0;
                    if (index - 1 >= 0) {
                        temp[index] += memo[index - 1];
                    }
                    if (index + 1 <= LENGTH - 1) {
                        temp[index] += memo[index + 1];
                    }
                }
            }
            memo = temp;
            temp = new int[LENGTH];
        }
        return memo[startIndex];
    }

    //0 1 2 3 4
    public static void main(String[] args) {
        RobotWalk algo = new RobotWalk();
        final int LENGTH = 5;
        final int TARGET_INDEX = 3;
        int startIndex = 1;
        int steps = 4;
        System.out.println(algo.simpleRec(LENGTH, TARGET_INDEX, startIndex, steps));
        System.out.println(algo.memoized(LENGTH, TARGET_INDEX, startIndex, steps));
        System.out.println(algo.induction(LENGTH, TARGET_INDEX, startIndex, steps));
        System.out.println(algo.inductionMemoryEfficient(LENGTH, TARGET_INDEX, startIndex, steps));
    }
}
