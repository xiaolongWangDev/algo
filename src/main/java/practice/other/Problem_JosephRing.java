package practice.other;

import java.util.ArrayList;
import java.util.List;

public class Problem_JosephRing {
    private static class Info {
        int survivorIndex;
        List<Integer> killed;

        public Info(int survivorIndex, List<Integer> killed) {
            this.survivorIndex = survivorIndex;
            this.killed = killed;
        }
    }

    public Info solveWrapper(int n, int[] killIndex) {
        return solve(0, killIndex, n);
    }

    public Info solve(int round, int[] killIndex, int TOTAL) {
        int nPeople = TOTAL - round;
        if (nPeople == 1) return new Info(0, List.of());

        Info nextRoundResult = solve(round + 1, killIndex, TOTAL);
        int indexOfThisRoundKilled = killIndex[round % killIndex.length] % nPeople;

        int survivorIndex = indexShift(nextRoundResult.survivorIndex, +indexOfThisRoundKilled + 1, nPeople);
        List<Integer> killed = new ArrayList<>();
        for (int killedIndex : nextRoundResult.killed) {
            killed.add(indexShift(killedIndex, indexOfThisRoundKilled + 1, nPeople));
        }
        killed.add(indexOfThisRoundKilled);
//        System.out.printf("survivor index: %d in %d players%n", survivorIndex, nPeople);
        return new Info(survivorIndex, killed);
    }

    private int indexShift(int srcIndex, int offset, int size) {
        return (srcIndex + offset) % size;
    }

    public static void main(String[] args) {
        Problem_JosephRing p = new Problem_JosephRing();
        Info result = p.solveWrapper(5, new int[]{3, 5});
        System.out.println("survivor:" + result.survivorIndex);
//        List<Integer> killed = result.killed;
//        for (int i = killed.size() - 1; i >= 0; i--) {
//            System.out.println("kill: " + killed.get(i));
//        }

        // kill every other
        result = p.solveWrapper(20, new int[]{1});
        System.out.println("survivor:" + result.survivorIndex);

        result = p.solveWrapper(1, new int[]{2});
        System.out.println("survivor:" + result.survivorIndex);
        result = p.solveWrapper(3, new int[]{2});
        System.out.println("survivor:" + result.survivorIndex);
        result = p.solveWrapper(9, new int[]{2});
        System.out.println("survivor:" + result.survivorIndex);
        result = p.solveWrapper(27, new int[]{2});
        System.out.println("survivor:" + result.survivorIndex);

    }

}
