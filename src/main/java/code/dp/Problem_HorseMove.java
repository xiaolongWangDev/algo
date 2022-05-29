package code.dp;

public class Problem_HorseMove {

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
        if (step == 0) {
            return x == 0 && y == 0 ? 1 : 0;
        }
        int moves = 0;
        if (x - 2 >= 0 && y - 1 >= 0) {
            moves += move(N, M, x - 2, y - 1, step - 1);
        }
        if (x - 2 >= 0 && y + 1 < M) {
            moves += move(N, M, x - 2, y + 1, step - 1);
        }
        if (x + 2 < N && y - 1 >= 0) {
            moves += move(N, M, x + 2, y - 1, step - 1);
        }
        if (x + 2 < N && y + 1 < M) {
            moves += move(N, M, x + 2, y + 1, step - 1);
        }
        if (x - 1 >= 0 && y - 2 >= 0) {
            moves += move(N, M, x - 1, y - 2, step - 1);
        }
        if (x + 1 < N && y - 2 >= 0) {
            moves += move(N, M, x + 1, y - 2, step - 1);
        }
        if (x - 1 >= 0 && y + 2 < M) {
            moves += move(N, M, x - 1, y + 2, step - 1);
        }
        if (x + 1 < N && y + 2 < M) {
            moves += move(N, M, x + 1, y + 2, step - 1);
        }

        return moves;
    }

    public int induction(int N, int M, int targetX, int targetY, int steps) {
        int[][][] memo = new int[steps + 1][N][M];
        for (int step = 0; step <= steps; step++) {
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    if (step == 0) {
                        memo[step][x][y] = x == 0 && y == 0 ? 1 : 0;
                    } else {
                        int moves = 0;
                        if (x - 2 >= 0 && y - 1 >= 0) {
                            moves += memo[step - 1][x - 2][y - 1];
                        }
                        if (x - 2 >= 0 && y + 1 < M) {
                            moves += memo[step - 1][x - 2][y + 1];
                        }
                        if (x + 2 < N && y - 1 >= 0) {
                            moves += memo[step - 1][x + 2][y - 1];
                        }
                        if (x + 2 < N && y + 1 < M) {
                            moves += memo[step - 1][x + 2][y + 1];
                        }
                        if (x - 1 >= 0 && y - 2 >= 0) {
                            moves += memo[step - 1][x - 1][y - 2];
                        }
                        if (x + 1 < N && y - 2 >= 0) {
                            moves += memo[step - 1][x + 1][y - 2];
                        }
                        if (x - 1 >= 0 && y + 2 < M) {
                            moves += memo[step - 1][x - 1][y + 2];
                        }
                        if (x + 1 < N && y + 2 < M) {
                            moves += memo[step - 1][x + 1][y + 2];
                        }
                        memo[step][x][y] = moves;
                    }
                }
            }
        }
        return memo[steps][targetX][targetY];
    }

    public int inductionMemoryEfficient(int N, int M, int targetX, int targetY, int steps) {
        int[][] memo = new int[N][M];
        int[][] temp = new int[N][M];
        for (int step = 0; step <= steps; step++) {
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    if (step == 0) {
                        temp[x][y] = x == 0 && y == 0 ? 1 : 0;
                    } else {
                        int moves = 0;
                        if (x - 2 >= 0 && y - 1 >= 0) {
                            moves += memo[x - 2][y - 1];
                        }
                        if (x - 2 >= 0 && y + 1 < M) {
                            moves += memo[x - 2][y + 1];
                        }
                        if (x + 2 < N && y - 1 >= 0) {
                            moves += memo[x + 2][y - 1];
                        }
                        if (x + 2 < N && y + 1 < M) {
                            moves += memo[x + 2][y + 1];
                        }
                        if (x - 1 >= 0 && y - 2 >= 0) {
                            moves += memo[x - 1][y - 2];
                        }
                        if (x + 1 < N && y - 2 >= 0) {
                            moves += memo[x + 1][y - 2];
                        }
                        if (x - 1 >= 0 && y + 2 < M) {
                            moves += memo[x - 1][y + 2];
                        }
                        if (x + 1 < N && y + 2 < M) {
                            moves += memo[x + 1][y + 2];
                        }
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
        Problem_HorseMove algo = new Problem_HorseMove();
        System.out.println(algo.move(10, 9, 1, 2, 3));
        System.out.println(algo.induction(10, 9, 1, 2, 3));
        System.out.println(algo.inductionMemoryEfficient(10, 9, 1, 2, 3));
    }
}
