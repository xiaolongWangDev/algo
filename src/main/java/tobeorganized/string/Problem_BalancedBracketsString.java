package tobeorganized.string;

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

    public int maxDepth(char[] input) {
        int count = 0;
        int max = 0;
        for (char c : input) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }
            if (max < count) {
                max = count;
            }
        }
        return max;
    }

    public String longestBalancedWrapped(char[] input) {
        int count = 0;
        String result = "";
        String substring = "";
        for (char c : input) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }
            substring += c;
            if (count == 0) {
                if (result.length() < substring.length()) {
                    result = substring;
                }
            }
            if (count <= 0) {
                substring = "";
                count = 0;
            }
        }
        return result;
    }

    public String longestBalanced(char[] input) {
        int count = 0;
        String result = "";
        String substring = "";
        for (char c : input) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            }
            if (count < 0) {
                if (result.length() < substring.length()) {
                    result = substring;
                }
                substring = "";
                count = 0;
            } else {
                substring += c;
            }
        }
        if (count == 0) {
            if (result.length() < substring.length()) {
                result = substring;
            }
        }
        return result;
    }

    public String longestBalancedDp(char[] input) {
        Integer[] memo = new Integer[input.length];

        for (int i = 0; i < memo.length; i++) {
            // balance str cannot end with (
            if (input[i] == '(') {
                memo[i] = 0;
            } else {
                // if my left index is not out of bound, otherwise, I cannot possibly be balance
                if (i - 1 >= 0) {
                    int leftDelta = memo[i - 1];
                    // jump over the "already balance" trunk on my left, and see if the next one to the left is out of bound,
                    if (i - leftDelta - 1 >= 0) {
                        // if I found my potential pair is a ), I cannot balance. that ) also won't be part of a balanced str
                        // because each memo place is reporting the "longest" match
                        if (input[i - leftDelta - 1] == ')') {
                            memo[i] = 0;
                        } else {
                            // if it's a (, let me check what on it's left again.
                            // if it's not out of bound, we just need to add that index's longest match~
                            if (i - leftDelta - 2 >= 0) {
                                memo[i] = memo[i - leftDelta - 2] + leftDelta + 2;
                            } else {
                                // if it's out of bound, it means Im just wrapping my left's longest match with ()
                                memo[i] = leftDelta + 2;
                            }
                        }
                    } else {
                        // if it is out of bound, I don't have a pair
                        memo[i] = 0;
                    }
                } else {
                    memo[i] = 0;
                }
            }
        }
        int indexWithMaxValue = 0;
        int max = 0;
        for (int i = 0; i < memo.length; i++) {
            if (max < memo[i]) {
                max = memo[i];
                indexWithMaxValue = i;
            }
        }

        String str = "";
        for (int i = indexWithMaxValue - memo[indexWithMaxValue] + 1; i <= indexWithMaxValue; i++) {
            str += input[i];
        }

        return str;

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

        System.out.println(p.maxDepth("(())((()))()".toCharArray()));
        System.out.println(p.maxDepth("(((())((()))()))".toCharArray()));

        System.out.println(p.longestBalancedWrapped("(((())((()))()))".toCharArray()));
        System.out.println(p.longestBalancedWrapped("))(())((()))()".toCharArray()));
        System.out.println(p.longestBalanced("(((())((()))()))".toCharArray()));
        System.out.println(p.longestBalanced("))(())((()))()".toCharArray()));
        System.out.println(p.longestBalanced("))(())((()))())(((())((()))()))".toCharArray()));
        System.out.println(p.longestBalanced("()()(())()))(())".toCharArray()));

        System.out.println(p.longestBalancedDp("()()(())()))(())".toCharArray()));
    }
}
