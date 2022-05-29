package code.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_ValidParentheses {

    public List<String> solve(int n, Map<Integer, List<String>> memo) {
        if (memo.get(n) != null) return memo.get(n);
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            List<String> firstStrings = solve(i - 1, memo);
            List<String> rightStrings = solve(n - i, memo);
            for (String s : firstStrings) {
                for (String s1 : rightStrings) {
                    result.add("(" + s + ")" + s1);
                }
            }
        }
        memo.put(n, result);
        return result;
    }

    private static class Result {
        List<String> strings;

        public Result(List<String> strings) {
            this.strings = strings;
        }
    }

    public List<String> dp(int n) {
        Result[] memo = new Result[n + 1];
        memo[0] = new Result(List.of(""));
        for (int i = 1; i <= n; i++) {
            List<String> result = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                Result firstStrings = memo[j - 1];
                Result rightStrings = memo[i - j];
                for (String s : firstStrings.strings) {
                    for (String s1 : rightStrings.strings) {
                        StringBuilder sb = new StringBuilder();
                        sb.append('(');
                        sb.append(s);
                        sb.append(')');
                        sb.append(s1);
                        result.add(sb.toString());
                    }
                }
            }
            memo[i] = new Result(result);
        }
        return memo[n].strings;
    }

    StringBuilder sb = new StringBuilder();
    List<String> ans = new ArrayList<>();

    public void solve2(int leftRemaining, int rightRemaining) {
        if (leftRemaining == 0 && rightRemaining == 0) {
            ans.add(sb.toString());
        }
        if (leftRemaining > rightRemaining) return;
        if (leftRemaining >= 1) {
            sb.append('(');
            solve2(leftRemaining - 1, rightRemaining);
            sb.setLength(sb.length() - 1);
        }

        if (rightRemaining >= 1) {
            sb.append(')');
            solve2(leftRemaining, rightRemaining - 1);
            sb.setLength(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        Map<Integer, List<String>> memo = new HashMap<>();
        memo.put(0, List.of(""));
        memo.put(1, List.of("()"));
        Problem_ValidParentheses p = new Problem_ValidParentheses();
        System.out.println(p.solve(3, memo));
        System.out.println(p.dp(3));
        p.solve2(3, 3);
        System.out.println(p.ans);
    }
}
