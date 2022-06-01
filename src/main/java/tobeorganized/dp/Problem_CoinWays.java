package tobeorganized.dp;

public class Problem_CoinWays {
    public int countRec(int[] coinValues, int currentIndex, int remaining) {
        if (currentIndex == coinValues.length) {
            return remaining == 0 ? 1 : 0;
        }
        int count = countRec(coinValues, currentIndex + 1, remaining);
        while (remaining >= coinValues[currentIndex]) {
            remaining -= coinValues[currentIndex];
            count += countRec(coinValues, currentIndex + 1, remaining);
        }
        return count;
    }

    public int induction(int[] coinValues, int targetBalance) {
        return dp(coinValues, targetBalance)[targetBalance];
    }

    public int[] dp(int[] coinValues, int targetBalance) {
        int[] memo = new int[targetBalance + 1];
        int[] temp = new int[targetBalance + 1];
        for (int index = coinValues.length; index > -1; index--) {
            for (int balance = 0; balance <= targetBalance; balance++) {
                if (index == coinValues.length) {
                    temp[balance] = balance == 0 ? 1 : 0;
                } else {
                    temp[balance] = memo[balance];
                    // learn more about convex hull optimization
                    if (balance >= coinValues[index]) {
                        temp[balance] += temp[balance - coinValues[index]];
                    }
                }
            }
            memo = temp;
            temp = new int[targetBalance + 1];
        }
        return memo;
    }

    public static void main(String[] args) {
        Problem_CoinWays algo = new Problem_CoinWays();
        System.out.println(algo.countRec(new int[]{3, 5, 1, 2}, 0, 10));
        System.out.println(algo.induction(new int[]{3, 5, 1, 2}, 10));
    }
}
