package practice.leetcode;

public class L14LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (true) {
            Character cur = null;
            for (String s : strs) {
                if (i == s.length()) {
                    cur = null;
                    break;
                } else if (cur == null) {
                    cur = s.charAt(i);
                } else if (cur != s.charAt(i)) {
                    cur = null;
                    break;
                }
            }
            if (cur == null) break;
            sb.append(cur);
            i++;
        }
        return sb.toString();
    }
}
