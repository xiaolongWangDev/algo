package tobeorganized.dp;

import java.util.ArrayList;
import java.util.List;

import static tobeorganized.array.sorting.SortingUtils.generateTestData;

public class Problem_LeftRightDrawCardGame {

    private static class Strategy {
        List<Integer> draws;
        int score;

        public Strategy(List<Integer> draws, int score) {
            this.draws = draws;
            this.score = score;
        }
    }


    public Strategy firstDraw(int[] input, int leftAt, int rightAt, List<Integer> draws) {
        if (leftAt == rightAt) {
            List<Integer> newDraws = new ArrayList<>(draws);
            newDraws.add(input[leftAt]);
            return new Strategy(newDraws, input[leftAt]);
        }
        List<Integer> newDraws = new ArrayList<>(draws);
        newDraws.add(input[leftAt]);
        Strategy leftStrategy = secondDraw(input, leftAt + 1, rightAt, newDraws);
        newDraws.remove(newDraws.size() - 1);
        newDraws.add(input[rightAt]);
        Strategy rightStrategy = secondDraw(input, leftAt, rightAt - 1, newDraws);
        if (leftStrategy.score + input[leftAt] > rightStrategy.score + input[rightAt]) {
            return new Strategy(leftStrategy.draws, leftStrategy.score + input[leftAt]);
        } else {
            return new Strategy(rightStrategy.draws, rightStrategy.score + input[rightAt]);
        }
    }

    public Strategy secondDraw(int[] input, int leftAt, int rightAt, List<Integer> draws) {
        if (leftAt == rightAt) {
            return new Strategy(new ArrayList<>(draws), 0);
        }

        List<Integer> newDraws = new ArrayList<>(draws);
        Strategy leftStrategy = firstDraw(input, leftAt + 1, rightAt, newDraws);
        Strategy rightStrategy = firstDraw(input, leftAt, rightAt - 1, newDraws);
        if (leftStrategy.score > rightStrategy.score) {
            return new Strategy(rightStrategy.draws, rightStrategy.score);
        } else {
            return new Strategy(leftStrategy.draws, leftStrategy.score);
        }
    }

    /*
    Firsthand table
    leftAt\rightAt 0    1    2    3
    0(1)           1    2  101  101
    1(2)           0    2  100    6
    2(100)         0    0  100  100
    3(4)           0    0    0    4

    Secondhand table
    leftAt\rightAt 0    1    2    3
    0(1)           0    1    2    6
    1(2)           0    0    2  100
    2(100)         0    0    0    4
    3(4)           0    0    0    0
     */
    public void induction(int[] input) {
        Strategy[][] memoFirst = new Strategy[input.length][input.length];
        Strategy[][] memoSecond = new Strategy[input.length][input.length];
        for (int leftAt = input.length - 1; leftAt >= 0; leftAt--) {
            for (int rightAt = leftAt; rightAt < input.length; rightAt++) {
                if (leftAt == rightAt) {
                    memoFirst[leftAt][rightAt] = new Strategy(List.of(leftAt), input[leftAt]);
                    memoSecond[leftAt][rightAt] = new Strategy(List.of(), 0);
                } else {

                    int leftFirsthandScore = input[leftAt] + memoSecond[leftAt + 1][rightAt].score;
                    int rightFirsthandScore = input[rightAt] + memoSecond[leftAt][rightAt - 1].score;
                    if (leftFirsthandScore > rightFirsthandScore) {
                        List<Integer> draws = new ArrayList<>(memoSecond[leftAt + 1][rightAt].draws);
                        draws.add(leftAt);
                        memoFirst[leftAt][rightAt] = new Strategy(draws, leftFirsthandScore);
                    } else {
                        List<Integer> draws = new ArrayList<>(memoSecond[leftAt][rightAt - 1].draws);
                        draws.add(rightAt);
                        memoFirst[leftAt][rightAt] = new Strategy(draws, rightFirsthandScore);

                    }
                    int leftSecondHandScore = memoFirst[leftAt + 1][rightAt].score;
                    int rightSecondHandScore = memoFirst[leftAt][rightAt - 1].score;
                    if (leftSecondHandScore > rightSecondHandScore) {
                        memoSecond[leftAt][rightAt] = memoFirst[leftAt][rightAt - 1];
                    } else {
                        memoSecond[leftAt][rightAt] = memoFirst[leftAt + 1][rightAt];
                    }
                }
            }
        }

        printResult(memoFirst[0][input.length - 1], memoSecond[0][input.length - 1]);
    }

    public void inductionMemoryEfficient(int[] input) {
        Strategy[] memoFirst = new Strategy[input.length];
        Strategy[] memoSecond = new Strategy[input.length];
        Strategy[] tempFirst = new Strategy[input.length];
        Strategy[] tempSecond = new Strategy[input.length];
        for (int i = 0; i < input.length; i++) {
            memoFirst[i] = new Strategy(List.of(i), input[i]);
            memoSecond[i] = new Strategy(List.of(), 0);
        }
        //i  = 1: (0,1), (1,2), (2,3)
        //i  = 2: (0,2), (1,3)
        //i  = 3: (0,3)
        for (int i = 1; i < input.length; i++) {
            for (int j = i; j < input.length; j++) {
                int leftFirsthandScore = input[j - i] + memoSecond[j].score;
                int rightFirsthandScore = input[j] + memoSecond[j - 1].score;
                if (leftFirsthandScore > rightFirsthandScore) {
                    List<Integer> draws = new ArrayList<>(memoSecond[j].draws);
                    draws.add(j - i);
                    tempFirst[j] = new Strategy(draws, leftFirsthandScore);
                } else {
                    List<Integer> draws = new ArrayList<>(memoSecond[j - 1].draws);
                    draws.add(j);
                    tempFirst[j] = new Strategy(draws, rightFirsthandScore);

                }
                int leftSecondHandScore = memoFirst[j].score;
                int rightSecondHandScore = memoFirst[j - 1].score;
                if (leftSecondHandScore > rightSecondHandScore) {
                    tempSecond[j] = memoFirst[j - 1];
                } else {
                    tempSecond[j] = memoFirst[j];
                }
            }
            memoFirst = tempFirst;
            memoSecond = tempSecond;
            tempFirst = new Strategy[input.length];
            tempSecond = new Strategy[input.length];
        }

        System.out.print(memoFirst[input.length - 1].score);

//        printResult(memoFirst[input.length - 1], memoSecond[input.length - 1]);
    }

    public static void main(String[] args) {
        Problem_LeftRightDrawCardGame algo = new Problem_LeftRightDrawCardGame();
//        int[] input = {1, 2, 100, 4};
//        List<Integer> firstPlayerDraws = new ArrayList<>();
//        List<Integer> secondPlayerDraws = new ArrayList<>();
//        Strategy firstStrategy = algo.firstDraw(input, 0, input.length - 1, firstPlayerDraws);
//        Strategy secondStrategy = algo.secondDraw(input, 0, input.length - 1, secondPlayerDraws);
//
//        printResult(firstStrategy, secondStrategy);

        algo.inductionMemoryEfficient(generateTestData(1000, 0, 100));
    }

    private static void printResult(Strategy memoFirst, Strategy memoSecond) {
        if (memoFirst.score > memoSecond.score) {
            System.out.println("first guy won");
        } else {
            System.out.println("second guy won");
        }
        System.out.println("-------");
        List<Integer> firstHandDraws = memoFirst.draws;
        for (int i = firstHandDraws.size() - 1; i >= 0; i--) {
            Integer draw = firstHandDraws.get(i);
            System.out.println(draw);
        }
        System.out.println("-------");
        List<Integer> secondHandDraws = memoSecond.draws;
        for (int i = secondHandDraws.size() - 1; i >= 0; i--) {
            Integer draw = secondHandDraws.get(i);
            System.out.println(draw);
        }
    }
}
