package forcerecursive;

import java.util.ArrayList;
import java.util.List;

public class LeftRightDrawCardGame {

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

    public static void main(String[] args) {
        LeftRightDrawCardGame algo = new LeftRightDrawCardGame();
        int[] input = {1, 2, 100, 4};
        List<Integer> firstPlayerDraws = new ArrayList<>();
        List<Integer> secondPlayerDraws = new ArrayList<>();
        Strategy firstStrategy = algo.firstDraw(input, 0, input.length - 1, firstPlayerDraws);
        Strategy secondStrategy = algo.secondDraw(input, 0, input.length - 1, secondPlayerDraws);

        if (firstStrategy.score > secondStrategy.score) {
            System.out.println("first guy won");
        } else {
            System.out.println("second guy won");
        }
        firstStrategy.draws.forEach(System.out::println);
        System.out.println("-------");
        secondStrategy.draws.forEach(System.out::println);
    }
}
