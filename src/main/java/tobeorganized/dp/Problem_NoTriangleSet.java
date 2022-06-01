package tobeorganized.dp;

import java.util.ArrayList;
import java.util.List;

public class Problem_NoTriangleSet {

    public List<Integer> find(int n) {
        int i = 1;
        MatrixOptimization algo = new MatrixOptimization();
        int[] initVector = {2, 1};
        int[][] transferMatrix = {
                {1, 1},
                {1, 0},
        };
        int stepNumber = algo.f(i, initVector, transferMatrix);
        List<Integer> res = new ArrayList<>();
        while (stepNumber <= n) {
            res.add(stepNumber);
            stepNumber = algo.f(++i, initVector, transferMatrix);
        }
        return res;
    }

    public static void main(String[] args) {
        Problem_NoTriangleSet p = new Problem_NoTriangleSet();
        System.out.println(p.find(17));
    }
}
