package tobeorganized.forcerecursive;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class AllPermutation2 {

    public void find(String cumulated, Map<Character, Integer> counts, int targetLen, Consumer<String> resultCollector) {
        if (cumulated.length() == targetLen) {
            resultCollector.accept(cumulated);
        } else {
            for(Character c : counts.keySet()) {
                if(counts.get(c) > 0) {
                    counts.put(c, counts.get(c) - 1);
                    find(cumulated + c, counts, targetLen, resultCollector);
                    counts.put(c, counts.get(c) + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        AllPermutation2 algo = new AllPermutation2();
        String input = "0333";
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : input.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }
        algo.find("", counts, input.length(), System.out::println);
    }
}
