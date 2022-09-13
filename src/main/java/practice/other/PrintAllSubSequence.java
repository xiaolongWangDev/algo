package practice.other;

public class PrintAllSubSequence {
    static int count = 0;
    public static void solve(char[] s, int cur, StringBuilder sb) {
        count++;
        System.out.println(sb);
        if(cur == s.length) return;

        for (int i = cur; i < s.length; i++) {
            sb.append(s[i]);
            solve(s, i + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void solve2(char[] s, int cur, StringBuilder sb) {
        count++;
        if(cur == s.length) {
            System.out.println(sb);
            return;
        }
        sb.append(s[cur]);
        solve2(s, cur + 1, sb);
        sb.deleteCharAt(sb.length() - 1);
        solve2(s, cur + 1, sb);
    }

    public static void main(String[] args) {
        solve("abc".toCharArray(), 0, new StringBuilder());
        System.out.println(count);
    }
}
