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

    public static void main(String[] args) {
        Problem_BalancedBracketsString p = new Problem_BalancedBracketsString();
        System.out.println(p.determine("()()()(()".toCharArray()));
        System.out.println(p.determine("()()()(())".toCharArray()));
        System.out.println(p.determine("())()(())".toCharArray()));
    }
}
