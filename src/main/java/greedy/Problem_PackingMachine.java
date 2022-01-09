package greedy;

public class Problem_PackingMachine {
    public int moves(int[] initLoads) {
        int sum = 0;
        for (int initLoad : initLoads) {
            sum += initLoad;
        }
        if (sum % initLoads.length != 0) return -1;
        int targetLoad = sum / initLoads.length;
        int[] minMoves = new int[initLoads.length];

        int leftSum = 0;
        int rightSum = sum;
        for (int i = 0; i < initLoads.length; i++) {
            rightSum -= initLoads[i];
            int leftNeed = i * targetLoad - leftSum;
            int rightNeed = (initLoads.length - 1 - i) * targetLoad - rightSum;
            if (leftNeed > 0 && rightNeed > 0) {
                minMoves[i] = leftNeed + rightNeed;
            } else {
                minMoves[i] = Math.max(Math.abs(leftNeed), Math.abs(rightNeed));
            }

            leftSum += initLoads[i];
        }

        int maxMinMoves = 0;
        for (int moves : minMoves) {
            if (maxMinMoves < moves) {
                maxMinMoves = moves;
            }
        }
        return maxMinMoves;
    }

    public static void main(String[] args) {
        Problem_PackingMachine p = new Problem_PackingMachine();
        System.out.println(p.moves(new int[] { 100, 0, 0, 0}));
    }
}
