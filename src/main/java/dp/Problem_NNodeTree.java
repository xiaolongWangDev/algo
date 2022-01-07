package dp;

public class Problem_NNodeTree {
    public int countNaive(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        int counts = 0;
        for (int i = 0; i < n; i++) {
            counts += countNaive(i) * countNaive(n - 1 - i);
        }
        return counts;
    }

    /*
     * n  f(n)
     * 5
     * 4  f(0) * f(3) + f(1) * f(2) + f(2) * f(1) + f(3) * f(0)
     * 3  f(0) * f(2) + f(1) * f(1) + f(2) * f(0)
     * 2  f(0) * f(1) + f(1) * f(0)
     * 1  1
     * 0  1
     *
     * Since, each step will use all previous result, we cannot really same memory
     */
    public int countEfficient(int n) {
        int[] memo = new int[n + 1];
        memo[0] = 1;
        memo[1] = 1;
        for (int k = 2; k <= n; k++) {
            int counts = 0;
            for (int i = 0; i < n; i++) {
                counts += countNaive(i) * countNaive(n - 1 - i);
            }
            memo[k] = counts;
        }
        return memo[n];
    }

    public static void main(String[] args) {
        Problem_NNodeTree algo = new Problem_NNodeTree();
        int n = 10;
        for(int i = 0; i <= 10; i++)
            System.out.println(algo.countNaive(i));
        System.out.println(algo.countEfficient(n));
    }
}
