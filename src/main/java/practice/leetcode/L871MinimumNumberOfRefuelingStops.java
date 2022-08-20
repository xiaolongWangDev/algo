package practice.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class L871MinimumNumberOfRefuelingStops {

    /**
     * the problem with this is the dp array is too long because the target could be very large.
     * So, we need to use the other approach
     */
    public static int minRefuelStops1(int target, int startFuel, int[][] stations) {
        int fuelAtFirstStop = Math.min(target, stations.length == 0 ? startFuel - target : startFuel - stations[0][0]);
        if (fuelAtFirstStop < 0) return -1;

        int[] dp = new int[target + 1];

        for (int i = stations.length - 1; i >= 0; i--) {
            int[] temp = new int[target + 1];
            int distanceToNext;
            if (i == stations.length - 1) {
                distanceToNext = target - stations[i][0];
            } else {
                distanceToNext = stations[i + 1][0] - stations[i][0];
            }
            for (int fuel = 0; fuel <= target; fuel++) {
                int nextFuelIfFueled = Math.min(target, fuel + stations[i][1] - distanceToNext);
                int nextFuelIfNotFueled = fuel - distanceToNext;
                int costIfFueled = nextFuelIfFueled < 0 ? -1 : dp[nextFuelIfFueled];
                int costIfNotFueled = nextFuelIfNotFueled < 0 ? -1 : dp[nextFuelIfNotFueled];

                if (costIfFueled == -1 && costIfNotFueled == -1) {
                    temp[fuel] = -1;
                } else if (costIfFueled == -1) {
                    temp[fuel] = costIfNotFueled;
                } else if (costIfNotFueled == -1) {
                    temp[fuel] = 1 + costIfFueled;
                } else {
                    temp[fuel] = Math.min(costIfNotFueled, 1 + costIfFueled);
                }
            }
            dp = temp;
            for (int t : temp) {
                System.out.printf("%1d", t);
            }
            System.out.println();
        }
        return dp[fuelAtFirstStop];
    }

    /**
     * only use stations ending inclusive at \ # of refills   0   1   2   3   4
     * 0                                                     25  85
     * 1                                                     25  85 115
     * 2                                                     25  85 115 145
     * 3                                                     25  85 125 155 185
     */
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        long[] dp = new long[stations.length + 1];
        dp[0] = startFuel;

        // iterate stations
        for (int i = 0; i < stations.length; i++) {
            for (int j = i + 1; j >= 1; j--) {
                // if doing j-1 refills using previous stations get me to current station,
                if (dp[j - 1] >= stations[i][0]) {
                    dp[j] = Math.max(dp[j], dp[j - 1] + stations[i][1]);
                }
            }
            System.out.println(Arrays.toString(dp));
        }

        // the first refill distance that's greater than the target is a valid refill
        for (int i = 0; i <= stations.length; i++) {
            if (dp[i] >= target) return i;
        }
        return -1;
    }

    public static int minRefuelStopsGreedy(int target, int startFuel, int[][] stations) {
        PriorityQueue<Integer> passedStations = new PriorityQueue<>(Collections.reverseOrder());

        int visited = 0;
        int fuel = startFuel;
        for (int[] station : stations) {
            while (fuel < station[0] && !passedStations.isEmpty()) {
                Integer newFuel = passedStations.poll();
                fuel += newFuel;
                visited++;
            }

            if (fuel >= station[0]) {
                passedStations.add(station[1]);
            } else {
                return -1;
            }
        }

        while (fuel < target && !passedStations.isEmpty()) {
            Integer newFuel = passedStations.poll();
            fuel += newFuel;
            visited++;
        }

        return fuel >= target ? visited : -1;
    }

    public static void main(String[] args) {
//        int[][] stations = {
//                {10, 60}, {20, 30}, {30, 30}, {60, 40}
//        };
//        System.out.println(minRefuelStopsGreedy(100, 10, stations));

        int[][] stations = {
                {25, 50}, {50, 25}
        };
        System.out.println(minRefuelStopsGreedy(100, 50, stations));
//        System.out.println(minRefuelStops(1, 1, new int[][]{}));
    }
}
