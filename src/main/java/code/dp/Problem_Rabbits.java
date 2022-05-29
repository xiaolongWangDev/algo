package code.dp;

public class Problem_Rabbits {
    // start with 1 rabbit,
    // every year each existing rabbit gives birth to new 2rabbits
    // new rabbit takes 2 year to grow mature
    // rabbit dies after 5 years
    // solve the number of rabbits at year N
    public static void main(String[] args) {
        // f(n) = f(n - 1) + 2 * f(n - 2) - f(n -5)
        // f(6) = (f5) + 2 * f(4) - f(1)
        // f(5) = (f5)
        // f(4) = f(4)
        // ...
        // f(1) = f(1)

        // 1 3 5 11 21 42 81
        MatrixOptimization algo = new MatrixOptimization();
        for (int i = 6; i < 20; i++) {
            System.out.println(algo.f(i, new int[]{21, 11, 5, 3, 1}, new int[][]{
                    {1, 1, 0, 0, 0},
                    {2, 0, 1, 0, 0},
                    {0, 0, 0, 1, 0},
                    {0, 0, 0, 0, 1},
                    {-1, 0, 0, 0, 0},
            }));
        }
    }
}
