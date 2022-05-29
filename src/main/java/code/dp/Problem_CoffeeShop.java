package code.dp;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem_CoffeeShop {
    private static class Time {
        int becomeAvailable;
        int makeTime;

        public Time(int becomeAvailable, int makeTime) {
            this.becomeAvailable = becomeAvailable;
            this.makeTime = makeTime;
        }
    }

    // if let a cup dry, it won't need be washed
    // wash machine is sequential. dry can occur in parallel
    public int timeFinish(int[] makeTimeArr, int customers, int singleWashTime, int singleDryTime) {
        if (customers == 0) return 0;
        if (makeTimeArr.length == 0) return -1;
        // first half of the problem:
        // calc when each customer gets his coffee
        // then he just finishes in no time
        // then the cup is good for wash
        PriorityQueue<Time> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o.becomeAvailable + o.makeTime));
        for (int makeTime : makeTimeArr) {
            minHeap.add(new Time(0, makeTime));
        }
        int[] finishDrink = new int[customers];
        for (int i = 0; i < customers; i++) {
            Time myTime = minHeap.poll();
            finishDrink[i] = myTime.becomeAvailable + myTime.makeTime;
            minHeap.add(new Time(finishDrink[i], myTime.makeTime));
        }

        // second half, a DP.
        return washDp(finishDrink, singleWashTime, singleDryTime);
    }

    private int wash(int[] finishDrink, int singleWashTime, int singleDryTime, int i, int becomeAvailable) {
        int finishWashThisTime = Math.max(finishDrink[i], becomeAvailable) + singleWashTime;
        int dryThisTime = finishDrink[i] + singleDryTime;

        if (i == finishDrink.length - 1) {
            return Math.min(finishWashThisTime, dryThisTime);
        }

        // 2 options:
        // 1. wash it
        int finishTimeOfTheRemainingCups1 = wash(finishDrink, singleWashTime, singleDryTime, i + 1, finishWashThisTime);
        int time1 = Math.max(finishTimeOfTheRemainingCups1, finishWashThisTime);
        // 2. dry it
        int finishTimeOfTheRemainingCups2 = wash(finishDrink, singleWashTime, singleDryTime, i + 1, becomeAvailable);
        int time2 = Math.max(finishTimeOfTheRemainingCups2, dryThisTime);

        return Math.min(time1, time2);
    }

    private int washDp(int[] finishDrink, int singleWashTime, int singleDryTime) {

        int possibleAvailableTime = getPossibleAvailableTime(finishDrink, singleWashTime, finishDrink.length - 1);
        int[] memo = new int[possibleAvailableTime];
        int[] buffer = new int[possibleAvailableTime];

        for (int j = 0; j < possibleAvailableTime; j++) {
            int finishWashThisTime = Math.max(finishDrink[finishDrink.length - 1], j) + singleWashTime;
            int dryThisTime = finishDrink[finishDrink.length - 1] + singleDryTime;
            memo[j] = Math.min(finishWashThisTime, dryThisTime);
        }

        for (int i = finishDrink.length - 2; i >= 0; i--) {
            for (int j = 0; j < getPossibleAvailableTime(finishDrink, singleWashTime, i); j++) {
                int finishWashThisTime = Math.max(finishDrink[i], j) + singleWashTime;
                int dryThisTime = finishDrink[i] + singleDryTime;
                // 2 options:
                // 1. wash it
                int finishTimeOfTheRemainingCups1 = memo[finishWashThisTime];
                int time1 = Math.max(finishTimeOfTheRemainingCups1, finishWashThisTime);
                // 2. dry it
                int finishTimeOfTheRemainingCups2 = memo[j];
                int time2 = Math.max(finishTimeOfTheRemainingCups2, dryThisTime);
                buffer[j] = Math.min(time1, time2);
            }
            memo = buffer;
            buffer = new int[possibleAvailableTime];
        }
        return memo[0];
    }

    private int getPossibleAvailableTime(int[] finishDrink, int singleWashTime, int i) {
        return finishDrink[i] + (i + 1) * singleWashTime;
    }

    public static void main(String[] args) {
        Problem_CoffeeShop p = new Problem_CoffeeShop();
        /*
            finish drink time:
            2 3 4 6 6 7  8  9  10 12
         op w d w d w d  d  d  w  w
         f  4 6 6 9 8 10 11 12 12 14
         ba 0 4 4 6 6 10 10 10 10 12
         */
        System.out.println(p.timeFinish(new int[]{3, 2, 7}, 10, 2, 3));
    }
}
