package array;

public class Problem_BalancedBracketsString {
    public boolean determine(char[] input) {
        int count = 0;
        for (char c : input) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }

    public int fix(char[] input) {
        int missingLefts = 0;
        int missingRights = 0;
        for (char c : input) {
            if (c == '(') {
                missingRights++;
            } else if (c == ')') {
                missingRights--;
            }
            if (missingRights < 0) {
                missingLefts++;
                missingRights = 0;
            }
        }
        return missingRights + missingLefts;
    }

    public static void main(String[] args) {
        Problem_BalancedBracketsString p = new Problem_BalancedBracketsString();
        System.out.println(p.determine("()()()(()".toCharArray()));
        System.out.println(p.determine("()()()(())".toCharArray()));
        System.out.println(p.determine("())()(())".toCharArray()));

        System.out.println(p.fix("()()())".toCharArray()));
        System.out.println(p.fix("()()()))))".toCharArray()));
        System.out.println(p.fix("()()()))))(()(".toCharArray()));
    }
}
