package tobeorganized.dp;

import algorithm.dp.MatrixOptimization;

public class Problem_String10 {
    // find all valid string(made of 1 and 0) when the length is N and there's always a 1 left side of any 0
    // f(1) = 1; 1
    // f(2) = 2; 10, 11
    // f(3) = 3; 101, 110, 111

    // f(k) always starts with a 1, it's fixed.
    // The 2nd digit can be either 1 or 0.
    // if it's 1, we already new all the possible counts are in f(k-1)
    // if it's 0, it's next digit (the 3rd digit) must be 1. And we know all valid counts are in f(k-2)
    // so:
    // f(n) = f(n - 1) + f(n - 2)

    public static void main(String[] args) {
        MatrixOptimization algo = new MatrixOptimization();
        int[] initVector = {2, 1};
        for (int i = initVector.length + 1; i < 20; i++) {
            System.out.println(algo.f(i, initVector, new int[][]{
                    {1, 1},
                    {1, 0},
            }));
        }
    }
}
