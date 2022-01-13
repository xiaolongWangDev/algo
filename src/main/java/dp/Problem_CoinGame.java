package dp;

public class Problem_CoinGame {


    /*
         coins 0 1 2 3 4 5 6 7 8 9
         Win   F T T T F T T T F T
     */
    public boolean win(int coins) {
        if (coins == 0) return false;
        if (coins > 0 && coins <= 3) {
            return true;
        }

        for (int i = 1; i <= 3; i++) {
            if (!win(coins - i)) {
                return true;
            }
        }
        return false;
    }


    /*
           take / coins   0   1   2   3   4   5   6   7   8   9   10
             1            C   A   B   C   A   B   C   A   B   C   A
             2            C   A   A   B   B   C   C   A   A   B   B
             3            C   A   A   A   B   B   B   C   C   C   A
             W            F   T   T   T   T   F   F   T   T   F   T
     */
    public boolean win3Player(int coins) {
        int[][] memo = new int[4][coins + 1];
        memo[0][0] = 2;
        memo[1][0] = 2;
        memo[2][0] = 2;

        for (int i = 2; i <= coins; i++) {
            memo[0][i] = (memo[0][i - 1] + 1) % 3;
        }
        for (int i = 3; i <= coins; i++) {
            memo[1][i] = (memo[1][i - 2] + 1) % 3;
        }
        for (int i = 4; i <= coins; i++) {
            memo[2][i] = (memo[2][i - 3] + 1) % 3;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <= coins; j++) {
                System.out.printf("%2c", 'A' + memo[i][j]);
                memo[3][j] |= (memo[i][j] == 0 ? 1 : 0);
            }
            System.out.println();
        }
        for (int i = 0; i <= coins; i++) {
            System.out.printf("%2c", memo[3][i] == 1 ? 'T' : 'F');
        }
        System.out.println();
        return memo[3][coins] == 1;
    }


    public static void main(String[] args) {
        Problem_CoinGame algo = new Problem_CoinGame();

//        for (int i = 0; i < 20; i++) {
//            System.out.println(algo.win(i));
//        }
        algo.win3Player(40);
    }

}
