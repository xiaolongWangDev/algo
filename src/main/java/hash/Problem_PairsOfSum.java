package hash;

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
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return (one == pair.one && other == pair.other) || (one == pair.other && other == pair.one);
        }

        @Override
        public int hashCode() {
            return Objects.hash(Objects.hash(one, other) + Objects.hash(other, one));
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "one=" + one +
                    ", other=" + other +
                    '}';
        }
    }

    public Set<Pair> solve(int[] input, int targetSum) {
        Map<Integer, List<Integer>> valueToIndexes = new HashMap<>();

        for (int i = 0; i < input.length; i++) {
            // add to valueToIndexes
            valueToIndexes.putIfAbsent(input[i], new ArrayList<>());
            valueToIndexes.get(input[i]).add(i);
        }

        HashSet<Pair> result = new HashSet<>();
        for (int val : input) {
            if (valueToIndexes.containsKey(targetSum - val)) {
                for (int i : valueToIndexes.get(val)) {
                    for (int j : valueToIndexes.get(targetSum - val)) {
                        if (i != j) result.add(new Pair(i, j));
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Problem_PairsOfSum p = new Problem_PairsOfSum();
        p.solve(new int[]{1,2,3,4,5}, 5).forEach(System.out::println);
//        System.out.println(p.solve(new int[]{1, 2, 3, 4, 5, 5}, 10));
    }
}
