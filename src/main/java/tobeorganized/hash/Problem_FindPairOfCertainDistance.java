package tobeorganized.hash;

import java.util.*;

public class Problem_FindPairOfCertainDistance {
    public List<List<Integer>> find(int[] input, int diff) {
        Set<Integer> numbers = new HashSet<>();
        for (int i : input) {
            numbers.add(i);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i : numbers) {
            if (numbers.contains(i + diff)) {
                result.add(List.of(i, i + diff));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Problem_FindPairOfCertainDistance p = new Problem_FindPairOfCertainDistance();
        System.out.println(p.find(new int[]{3, 2, 5, 7, 0, 0}, 2));
    }
}
