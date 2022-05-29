package code.forcerecursive;

import java.util.*;
import java.util.function.Consumer;

public class AllPermutation {

    public void find(int i, char[] input, Consumer<String> resultCollector) {
        if (i == input.length) {
            resultCollector.accept(String.valueOf(input));
        } else {
            Set<Character> tried = new HashSet<>();
            for (int j = i; j < input.length; j++) {
                if (!tried.contains(input[j])) {
                    tried.add(input[j]);
                    swap(input, i, j);
                    find(i + 1, input, resultCollector);
                    swap(input, i, j);
                }
            }
        }
    }

    private void swap(char[] input, int i, int j) {
        char temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    public static void main(String[] args) {
        AllPermutation algo = new AllPermutation();
        algo.find(0, "aacadwe".toCharArray(), System.out::println);
    }
}
