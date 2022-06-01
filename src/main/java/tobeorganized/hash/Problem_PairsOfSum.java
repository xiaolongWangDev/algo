package tobeorganized.hash;

import java.util.*;

public class Problem_PairsOfSum {
    private static class Pair {
        int one;
        int other;

        public Pair(int one, int other) {
            this.one = one;
            this.other = other;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "one=" + one +
                    ", other=" + other +
                    '}';
        }
    }

    private static class Tuple {
        int one;
        int two;
        int three;

        public Tuple(int one, int two, int three) {
            this.one = one;
            this.two = two;
            this.three = three;
        }

        @Override
        public String toString() {
            return "Tuple{" +
                    "one=" + one +
                    ", two=" + two +
                    ", three=" + three +
                    '}';
        }
    }

    public List<Tuple> solve3(int[] input) {
        List<Tuple> result = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            for (Pair pair : solve2(input, 0, i - 1, -input[i])) {
                result.add(new Tuple(pair.one, pair.other, i));
            }
            for (Pair pair : solve2(input, i + 1, input.length - 1, -input[i])) {
                result.add(new Tuple(i, pair.one, pair.other));
            }
        }
        return result;
    }

    public List<Pair> solve2(int[] input, int targetSum) {
        return solve2(input, 0, input.length - 1, targetSum);
    }

    public List<Pair> solve2(int[] input, int l, int r, int targetSum) {
        Map<Integer, List<Integer>> values = new HashMap<>();

        List<Pair> result = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            if (values.containsKey(targetSum - input[i])) {
                // add to result
                for (int j : values.get(targetSum - input[i])) {
                    result.add(new Pair(j, i));
                }
            }
            values.putIfAbsent(input[i], new ArrayList<>());
            values.get(input[i]).add(i);
        }

        return result;
    }

    public static void main(String[] args) {
        Problem_PairsOfSum p = new Problem_PairsOfSum();
        p.solve2(new int[]{1, 2, 3, 4, 5}, 5).forEach(System.out::println);
//        System.out.println(p.solve(new int[]{1, 2, 3, 4, 5, 5}, 10));
        p.solve3(new int[]{-1, -2, 3, 4, 5, -2, -3}).forEach(System.out::println);
//                          0  1   2  3  4  5   6
    }
}
