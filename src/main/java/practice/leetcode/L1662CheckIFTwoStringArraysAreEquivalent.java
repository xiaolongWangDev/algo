package practice.leetcode;

public class L1662CheckIFTwoStringArraysAreEquivalent {
    static class Info {
        char val;
        int nextWordIndex;
        int nextCharIndex;

        public Info(char val, int nextWordIndex, int nextCharIndex) {
            this.val = val;
            this.nextWordIndex = nextWordIndex;
            this.nextCharIndex = nextCharIndex;
        }
    }

    public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        Info word1Info = getCharAt(0, 0, word1);
        Info word2Info = getCharAt(0, 0, word2);

        while (true) {
            if (word1Info == null && word2Info == null) return true;
            if (word1Info == null || word2Info == null) return false;
            if (word1Info.val != word2Info.val) return false;
            word1Info = getCharAt(word1Info.nextWordIndex, word1Info.nextCharIndex, word1);
            word2Info = getCharAt(word2Info.nextWordIndex, word2Info.nextCharIndex, word2);
        }
    }

    private static Info getCharAt(int wordIndex, int charIndex, String[] word) {
        if (wordIndex < word.length) {
            if (charIndex < word[wordIndex].length()) {
                boolean lastChar = charIndex == word[wordIndex].length() - 1;
                return new Info(word[wordIndex].charAt(charIndex), lastChar ? wordIndex + 1 : wordIndex, lastChar ? 0 : charIndex + 1);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(arrayStringsAreEqual(new String[]{"a", "bc", "d"}, new String[]{"ab", "cd"}));
    }
}
