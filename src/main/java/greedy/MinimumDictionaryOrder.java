package greedy;

import java.util.ArrayList;
import java.util.List;

public class MinimumDictionaryOrder {
    public List<String> arrange(List<String> input) {
        List<String> result = new ArrayList<>(input);
        result.sort((s1, s2) -> (s1 + s2).compareTo(s2 + s1));
        return result;
    }

    public static void main(String[] args) {
        MinimumDictionaryOrder algo = new MinimumDictionaryOrder();
        List<String> result = algo.arrange(List.of("ab", "a", "b", "a", "cca", "acbe", "abc", "d", "a"));
        System.out.println(String.join("", result));
    }
}
