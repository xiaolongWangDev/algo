package practice.leetcode;

public class L28ImplementStrstr {
    public static int strStr(String haystack, String needle) {
        if (haystack.length() < needle.length()) return -1;
        if (needle.length() == 0) return 0;
        int i = 0;
        int j = 0;
        int[] maxReusable = getMaxReusable(needle);
        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = maxReusable[j];
            }
        }
        if (j == needle.length()) return i - j;
        return -1;
    }

    // a b a b c

    private static int[] getMaxReusable(String needle) {
        int[] result = new int[needle.length()];
        result[0] = -1;
        result[1] = 0;
        for (int i = 2; i < needle.length(); i++) {
            int lookAt = result[i - 1];
            while (lookAt != -1 && needle.charAt(lookAt) != needle.charAt(i - 1)) {
                lookAt = result[lookAt];
            }
            result[i] = lookAt + 1;
        }
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(getMaxReusable("ababcdd")));
//        System.out.println(strStr("hello", "ll"));
//        System.out.println(strStr("aaaaa", "bba"));
        System.out.println(strStr("acbefghijkacbefghijkga", "acbefghijkg"));
    }
}
