package tobeorganized.string;

public class Problem_RotationString {
    public boolean shareSameRotation(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        KMP kmp = new KMP();
        return kmp.findSubstring(s1 + s1, s2) > -1;
    }

    public static void main(String[] args) {
        Problem_RotationString p = new Problem_RotationString();
        System.out.println(p.shareSameRotation("abcde", "deabc"));
        System.out.println(p.shareSameRotation("abcde", "decab"));
    }
}
