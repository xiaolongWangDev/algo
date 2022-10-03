package practice.leetcode;

public class L1578MinimumTimeToMakeRopeColorful {

    public static int minCost(String colors, int[] neededTime) {

        int allCost = neededTime[0];
        int[] maxTimeEachChar = new int[26];
        maxTimeEachChar[colors.charAt(0) - 'a'] = neededTime[0];
        for (int i = 1; i < neededTime.length; i++) {
            allCost += neededTime[i];
            int max = 0;
            int indexInHashTable = colors.charAt(i) - 'a';

            for (int j = 0; j < 26; j++) {
                if (j != indexInHashTable) {
                    max = Math.max(max, maxTimeEachChar[j]);
                }
            }

            if (max + neededTime[i] > maxTimeEachChar[indexInHashTable]) {
                maxTimeEachChar[indexInHashTable] = max + neededTime[i];
            }
        }
        int max = 0;
        for (int i = 0; i < 26; i++) {
            max = Math.max(max, maxTimeEachChar[i]);
        }

        return allCost - max;
    }

    public static int minCostExample(String colors, int[] neededTime) {
        // totalTime: total time needed to make rope colorful;
        // currMaxTime: maximum time of a balloon needed.
        int totalTime = 0, currMaxTime = 0;

        // For each balloon in the array:
        for (int i = 0; i < colors.length(); ++i) {
            // If this balloon is the first balloon of a new group
            // set the currMaxTime as 0.
            if (i > 0 && colors.charAt(i) != colors.charAt(i - 1)) {
                currMaxTime = 0;
            }

            // Increment totalTime by the smaller one.
            // Update currMaxTime as the larger one.
            totalTime += Math.min(currMaxTime, neededTime[i]);
            currMaxTime = Math.max(currMaxTime, neededTime[i]);
        }

        // Return totalTime as the minimum removal time.
        return totalTime;
    }

    public static int minCostGreedy(String colors, int[] neededTime) {

        int result = 0;
        int sumOfGroup = neededTime[0];
        int maxInGroup = neededTime[0];
        for (int i = 1; i < neededTime.length; i++) {
            if (colors.charAt(i) != colors.charAt(i - 1)) {
                result += sumOfGroup - maxInGroup; // maxInGroup is the one to keep, the others need to be removed
                maxInGroup = neededTime[i];
                sumOfGroup = neededTime[i];
            } else {
                maxInGroup = Math.max(maxInGroup, neededTime[i]);
                sumOfGroup += neededTime[i];
            }
        }
        result += sumOfGroup - maxInGroup;
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(minCostGreedy("abaac", new int[]{1, 2, 3, 4, 5}));
//        System.out.println(minCostGreedy("abc", new int[]{1, 2, 3}));
//        System.out.println(minCostGreedy("aabaa", new int[]{1, 2, 3, 4, 1}));
//        System.out.println(minCostGreedy("baab", new int[]{8, 7, 2, 10}));
        minCostExample("aa", new int[]{2, 1});
    }

}
