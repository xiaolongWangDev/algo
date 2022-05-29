package code.string;

import java.util.HashMap;
import java.util.Map;

public class KMP {
    public int findSubstring(String s1, String s2) {
        if (s2.length() > s1.length()) {
            return -1;
        }

        // for each index in s2, calculate a max length of reusable prefix
        Map<Integer, Integer> maxReusablePrefix = findMaxReusablePrefix(s2);

        int i = 0;
        int j = 0;

        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                // char match. both pointer move on
                i++;
                j++;
            } else if (j == 0) {
                // the head of s2 doesn't even match the char at current pointer in s1, s1 pointer has to move on
                i++;
            } else {
                // move s2 pointer to the end of its max prefix string, this way those don't need to be compared again.
                // this is recursively called if no match is found, so j will fallback to 0 eventually, and trigger branch 2 logic
                j = maxReusablePrefix.get(j);
            }
        }

        // only when s2 is finished can we say there's a match
        return j == s2.length() ? i - j : -1;
    }

    private Map<Integer, Integer> findMaxReusablePrefix(String str) {
        Map<Integer, Integer> maxReusablePrefix = new HashMap<>();
        maxReusablePrefix.put(0, -1);
        maxReusablePrefix.put(1, 0);

//        a b a b c
//       -1 0 0 1 2
        for (int i = 2; i < str.length(); i++) {

            int lookAt = i - 1;
            int prefixEndIndex = maxReusablePrefix.get(lookAt);

            // compare the char after the max prefix and the char before lookAt location(i)
            // if they are the same, then, current index's max reusable prefix length is that + 1
            // otherwise, move the "lookAt" index to the different index and compare recursivelly
            while (prefixEndIndex != -1 && str.charAt(prefixEndIndex) != str.charAt(i - 1)) {
                lookAt = prefixEndIndex;
                prefixEndIndex = maxReusablePrefix.get(lookAt);
            }
            maxReusablePrefix.put(i, maxReusablePrefix.get(lookAt) + 1);
        }
        return maxReusablePrefix;
    }

    public static void main(String[] args) {
        KMP algo = new KMP();
        System.out.println(algo.findSubstring("acbefghijkacbefghijkga", "acbefghijkg"));
        System.out.println(algo.findSubstring("abbabababc", "ababc"));
        System.out.println(algo.findSubstring("abbstabbecabbstabbfabbstabbecabbstabbe", "abbstabbecabbstabbsg"));

    }
}
