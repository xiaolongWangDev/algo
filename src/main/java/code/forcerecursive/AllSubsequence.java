package code.forcerecursive;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class AllSubsequence {

    public void allSeq(int i, String input, List<Character> current, Consumer<String> resultCollector) {
        if (i == input.length()) {
            resultCollector.accept(current.stream().map(String::valueOf).collect(Collectors.joining()));
        } else {
            List<Character> newCurrent = new ArrayList<>(current);
            allSeq(i + 1, input, newCurrent, resultCollector);
            newCurrent.add(input.charAt(i));
            allSeq(i + 1, input, newCurrent, resultCollector);
        }
    }

    public void allSeqNoExtraSpace(int i, char[] input, Consumer<String> resultCollector) {
        if (i == input.length) {
            StringBuilder sb = new StringBuilder();

            for (char c : input) {
                if (c != 0) {
                    sb.append(c);
                }
            }
            resultCollector.accept(sb.toString());
        } else {

            char temp = input[i];
            allSeqNoExtraSpace(i + 1, input, resultCollector);
            input[i] = 0;
            allSeqNoExtraSpace(i + 1, input, resultCollector);
            input[i] = temp;
        }
    }

    public static void main(String[] args) {
        AllSubsequence algo = new AllSubsequence();
        algo.allSeqNoExtraSpace(0, "abcde".toCharArray(), System.out::println);
    }
}
