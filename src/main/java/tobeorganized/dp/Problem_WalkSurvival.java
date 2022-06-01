package tobeorganized.dp;

public class Problem_WalkSurvival {

    /*
        0   1   2   3   4 ... M
    0
    1
    2
    3
    4
    5
    ...
    N

     */
    public int move(int N, int M, int x, int y, int step) {
        if (x < 0 || y < 0 || x >= N || y >= M) {
            return 0;
        }
        if (step == 0) {
            return 1;
        }

        int moves = 0;
        moves += move(N, M, x - 1, y, step - 1);
        moves += move(N, M, x + 1, y, step - 1);
        moves += move(N, M, x, y - 1, step - 1);
        moves += move(N, M, x, y + 1, step - 1);

        return moves;
    }

    public int inductionMemoryEfficient(int N, int M, int targetX, int targetY, int steps) {
        int[][] memo = new int[N][M];
        int[][] temp = new int[N][M];
        for (int step = 0; step <= steps; step++) {
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    if (step == 0) {
                        temp[x][y] = 1;
                    } else {
                        int moves = 0;
                        if (x - 1 >= 0) moves += memo[x - 1][y];
                        if (x + 1 < N) moves += memo[x + 1][y];
                        if (y - 1 >= 0) moves += memo[x][y - 1];
                        if (y + 1 < M) moves += memo[x][y + 1];
                        temp[x][y] = moves;
                    }
                }
            }
            memo = temp;
            temp = new int[N][M];
        }
        return memo[targetX][targetY];
    }

    public static void main(String[] args) {
        Problem_WalkSurvival algo = new Problem_WalkSurvival();
        System.out.println(algo.move(5, 5, 1, 2, 1));
        System.out.println(algo.move(5, 5, 0, 0, 1));
        System.out.println(algo.move(5, 5, 4, 4, 1));
        System.out.println(algo.inductionMemoryEfficient(5, 5, 1, 2, 1));
        System.out.println(algo.inductionMemoryEfficient(5, 5, 0, 0, 1));
        System.out.println(algo.inductionMemoryEfficient(5, 5, 4, 4, 1));
    }
}
