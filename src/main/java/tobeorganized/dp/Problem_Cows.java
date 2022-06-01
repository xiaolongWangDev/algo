package tobeorganized.dp;

public class Problem_Cows {
    // start with 1 cow,
    // every year the existing cows give birth to new cows
    // new cow takes 3 year to grow mature
    // cow never dies
    // solve the number of cows at year N
    public static void main(String[] args) {
        // f(n) = f(n - 1) + f(n -3)
        // f(4) = (f3) + f(1)
        // f(3) = (f3)
        // f(2) = f(2)
        // f(1) = f(1)
        MatrixOptimization algo = new MatrixOptimization();
        for (int i = 4; i < 9; i++) {
            System.out.println(algo.f(i, new int[]{3, 2, 1}, new int[][]{
                    {1, 1, 0},
                    {0, 0, 1},
                    {1, 0, 0},
            }));
        }
    }
}
