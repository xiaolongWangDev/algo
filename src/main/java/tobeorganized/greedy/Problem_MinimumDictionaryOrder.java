package tobeorganized.greedy;

import java.util.ArrayList;
import java.util.List;

public class Problem_MinimumDictionaryOrder {
    public List<String> sort(List<String> input) {
        List<String> result = new ArrayList<>(input);
        result.sort((s1, s2) -> (s1 + s2).compareTo(s2 + s1));
        return result;
    }

    public static void main(String[] args) {
        Problem_MinimumDictionaryOrder algo = new Problem_MinimumDictionaryOrder();
        List<String> result = algo.sort(List.of("ab", "a", "b", "a", "cca", "acbe", "abc", "d", "a"));
        System.out.println(String.join("", result));
    }
}
