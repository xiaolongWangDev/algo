package practice.leetcode;

public class L1480RunningSumOf1dArray {
    // int is [-2147483648, 2147483647]
    // because 2147483647 = 2.147483647 * 10^9 > 10^9 = 1000 * 10^6
    // therefore, int is good to keep the cumulated sum
    public int[] solve(int[] input) {
        int acc = 0;
        int[] result = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            acc += input[i];
            result[i] = acc;
        }
        return result;
    }
}
