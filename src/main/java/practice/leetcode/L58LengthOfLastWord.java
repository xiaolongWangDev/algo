package practice.leetcode;

public class L58LengthOfLastWord {
    public static int lengthOfLastWord(String s) {
        int l = 0;
        char[] chars = s.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            char c = chars[i];
            if (c == ' ') {
                if (l > 0) break;
            } else {
                l++;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("hello we  "));
    }
}
