package practice.leetcode;

import java.util.ArrayList;
import java.util.List;

public class L77Combinations {
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        rec(1, new ArrayList<>(), result, k, n);
        return result;
    }

    private static void rec(int cur, List<Integer> cumulated, List<List<Integer>> results, int K, int N) {
        if (cumulated.size() == K) {
            results.add(new ArrayList<>(cumulated));
            return;
        }


        // N = 5, K = 4
        // cur = 3, len = 2
        //

        int remaining = K - cumulated.size();

        for (int i = cur; i <= N - remaining + 1; i++) {
            cumulated.add(i);
            rec(i + 1, cumulated, results, K, N);
            cumulated.remove(cumulated.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(combine(5, 2));
    }
}
