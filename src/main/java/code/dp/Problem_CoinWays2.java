package code.dp;

public class Problem_CoinWays2 {

    public int count(int[] normalCoinValues, int[] specialCoinValues, int targetBalance) {
        int[] dp1 = new Problem_CoinWays().dp(normalCoinValues, targetBalance);
        int[] dp2 = specialCoinDp(specialCoinValues, targetBalance);
        int counts = 0;
        for (int i = 0; i <= targetBalance; i++) {
            counts += dp1[i] * dp2[targetBalance - i];
        }
        return counts;
    }

    public int[] specialCoinDp(int[] coinValues, int targetBalance) {
        int[] memo = new int[targetBalance + 1];
        int[] temp = new int[targetBalance + 1];
        for (int index = coinValues.length; index > -1; index--) {
            for (int balance = 0; balance <= targetBalance; balance++) {
                if (index == coinValues.length) {
                    temp[balance] = balance == 0 ? 1 : 0;
                } else {
                    temp[balance] = memo[balance];
                    if (balance >= coinValues[index]) {
                        temp[balance] += memo[balance - coinValues[index]];
                    }
                }
            }
            memo = temp;
            temp = new int[targetBalance + 1];
        }
        return memo;
    }

    public static void main(String[] args) {
        Problem_CoinWays2 p = new Problem_CoinWays2();
        // (7 0) 2
        // (6 1) 2
        // (5 2) 2
        // (4 3) 1
        System.out.println(p.count(new int[]{2, 3, 5}, new int[]{1, 2}, 7));
    }
}
