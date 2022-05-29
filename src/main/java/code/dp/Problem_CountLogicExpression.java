package code.dp;

public class Problem_CountLogicExpression {
    public int count(String exp, boolean target) {
        if (exp.length() == 1) {
            return (exp.charAt(0) == '1' && target) || (exp.charAt(0) == '0' && !target) ? 1 : 0;
        }
        int counts = 0;
        for (int i = 1; i <= exp.length() - 2; i += 2) {
            char operator = exp.charAt(i);
            if (target) {
                if (operator == '&') {
                    int lCount = count(exp.substring(0, i), true);
                    if (lCount != 0) counts += lCount * count(exp.substring(i + 1), true);
                } else if (operator == '|') {
                    int lCount = count(exp.substring(0, i), true);
                    if (lCount != 0) counts += lCount * count(exp.substring(i + 1), true);
                    lCount = count(exp.substring(0, i), false);
                    if (lCount != 0) counts += lCount * count(exp.substring(i + 1), true);
                    lCount = count(exp.substring(0, i), true);
                    if (lCount != 0) counts += lCount * count(exp.substring(i + 1), false);
                } else if (operator == '^') {
                    int lCount = count(exp.substring(0, i), true);
                    if (lCount != 0) counts += lCount * count(exp.substring(i + 1), false);
                    lCount = count(exp.substring(0, i), false);
                    if (lCount != 0) counts += lCount * count(exp.substring(i + 1), true);
                }
            } else {
                if (operator == '&') {
                    int lCount = count(exp.substring(0, i), false);
                    if (lCount != 0) counts += lCount * count(exp.substring(i + 1), false);
                    lCount = count(exp.substring(0, i), true);
                    if (lCount != 0) counts += lCount * count(exp.substring(i + 1), false);
                    lCount = count(exp.substring(0, i), false);
                    if (lCount != 0) counts += lCount * count(exp.substring(i + 1), true);
                } else if (operator == '|') {
                    int lCount = count(exp.substring(0, i), false);
                    if (lCount != 0) counts += lCount * count(exp.substring(i + 1), false);
                } else if (operator == '^') {
                    int lCount = count(exp.substring(0, i), true);
                    if (lCount != 0) counts += lCount * count(exp.substring(i + 1), true);
                    lCount = count(exp.substring(0, i), false);
                    if (lCount != 0) counts += lCount * count(exp.substring(i + 1), false);
                }
            }
        }
        return counts;
    }

    /*
    1^0|0|1
    target = true
    l\r   0  1  2  3  4  5  6
    0     1  x  1  x  2  x  3
    1     x  x  x  x  x  x  x
    2     x  x  0  x  0  x  2
    3     x  x  x  x  x  x  x
    4     x  x  x  x  0  x  1
    5     x  x  x  x  x  x  x
    6     x  x  x  x  x  x  1

    1^0|0|1
    target = false
    l\r  0  1  2  3  4  5  6
    0    0  x  0  x  0  x  2
    1    x  x  x  x  x  x  x
    2    x  x  1  x  1  x  0
    3    x  x  x  x  x  x  x
    4    x  x  x  x  1  x  0
    5    x  x  x  x  x  x  x
    6    x  x  x  x  x  x  0

     */
    public int countDp(char[] input, boolean target) {
        // we can reduce dimension here. but to beautifully print the table, I kept it like this
        int[][] trueMemo = new int[input.length][input.length];
        int[][] falseMemo = new int[input.length][input.length];
        // mark all cells as unused
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                trueMemo[i][j] = -1;
                falseMemo[i][j] = -1;
            }
        }
        // populate states on diagonal
        for (int i = 0; i < input.length; i += 2) {
            trueMemo[i][i] = input[i] == '1' ? 1 : 0;
            falseMemo[i][i] = input[i] == '0' ? 1 : 0;
        }

        // populate cells going bottom up and left to right
        for (int l = input.length - 3; l >= 0; l -= 2) {
            for (int r = l + 2; r < input.length; r += 2) {
                trueMemo[l][r] = 0;
                falseMemo[l][r] = 0;
                for (int i = l + 1; i <= r; i += 2) {
                    char operator = input[i];
                    if (operator == '&') {
                        trueMemo[l][r] += trueMemo[l][i - 1] * trueMemo[i + 1][r];
                        falseMemo[l][r] += trueMemo[l][i - 1] * falseMemo[i + 1][r];
                        falseMemo[l][r] += falseMemo[l][i - 1] * trueMemo[i + 1][r];
                        falseMemo[l][r] += falseMemo[l][i - 1] * falseMemo[i + 1][r];
                    } else if (operator == '|') {
                        trueMemo[l][r] += trueMemo[l][i - 1] * trueMemo[i + 1][r];
                        trueMemo[l][r] += trueMemo[l][i - 1] * falseMemo[i + 1][r];
                        trueMemo[l][r] += falseMemo[l][i - 1] * trueMemo[i + 1][r];
                        falseMemo[l][r] += falseMemo[l][i - 1] * falseMemo[i + 1][r];
                    } else if (operator == '^') {
                        trueMemo[l][r] += trueMemo[l][i - 1] * falseMemo[i + 1][r];
                        trueMemo[l][r] += falseMemo[l][i - 1] * trueMemo[i + 1][r];
                        falseMemo[l][r] += trueMemo[l][i - 1] * trueMemo[i + 1][r];
                        falseMemo[l][r] += falseMemo[l][i - 1] * falseMemo[i + 1][r];
                    }
                }
            }
        }

        // print memo tables
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                System.out.printf("%3s", trueMemo[i][j] == -1 ? "x" : trueMemo[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                System.out.printf("%3s", falseMemo[i][j] == -1 ? "x" : falseMemo[i][j]);
            }
            System.out.println();
        }

        return target ? trueMemo[0][input.length - 1] : falseMemo[0][input.length - 1];
    }

    public static void main(String[] args) {
        Problem_CountLogicExpression p = new Problem_CountLogicExpression();
        System.out.println(p.countDp("1^0|0|1".toCharArray(), false));
        System.out.println(p.countDp("1".toCharArray(), false));
        System.out.println(p.countDp("1^0&1".toCharArray(), true));
    }
}
