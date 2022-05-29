package code.string;

import java.util.*;

public class Problem_DistinctValueInDictionaryOrder {
    public String solve(String input) {
        if (input == null || input.length() < 2) return input;
        HashMap<Character, Integer> counts = new HashMap<>();
        for (Character c : input.toCharArray()) {
            counts.put(c, 1 + counts.getOrDefault(c, 0));
        }
        if (counts.size() == 1) return input.substring(0, 1);
        int minCharacterIndex = 0;
        char minCharacter = input.charAt(0);
        for (int i = 0; i < input.length(); i++) {
            Character c = input.charAt(i);
            counts.put(c, counts.get(c) - 1);
            if (counts.get(c) == 0) {
                break;
            }
            if (minCharacter > c) {
                minCharacter = c;
                minCharacterIndex = i;
            }
        }
        return minCharacter + solve(input.substring(minCharacterIndex + 1).replace("" + minCharacter, ""));
    }

    public static void main(String[] args) {
        Problem_DistinctValueInDictionaryOrder p = new Problem_DistinctValueInDictionaryOrder();
        System.out.println(p.solve("taabcbatbca"));
    }
}
