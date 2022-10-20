package practice.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class L60PermutationSequence {

    public static String getPermutation(int n, int k) {
        List<Integer> results = new ArrayList<>();
        rec(results, new HashSet<>(), 0, n, k);
        StringBuilder sb = new StringBuilder();
        for (int num : results) {
            sb.append(num);
        }
        return sb.toString();
    }

    public static int rec(List<Integer> result, Set<Integer> used, int count, int n, int k) {
        if (result.size() == n) {
            System.out.println(result);
//            if(count == k) System.out.println("here");
            return count + 1;
        }

        for (int i = 1; i <= n; i++) {
            if (!used.contains(i)) {
                used.add(i);
                result.add(i);
                count = rec(result, used, count, n, k);
                if (count == k) return count;
                result.remove(result.size() - 1);
                used.remove(i);
            }
        }
        return count;
    }


    public static String getPermutation2(int n, int k) {
        long[] factorials = new long[10];
        factorials[1] = 1;
        for (int i = 2; i <= n; i++) factorials[i] = factorials[i - 1] * i;
        return rec2(n, n, k - 1, factorials, new HashSet<>());
    }

    public static String rec2(int cur, int n, int k, long[] factorials, Set<Integer> used) {
        if (cur == 1) {
            for (int i = 1; i <= n; i++) {
                if (!used.contains(i)) {
                    char currentChar = (char) ('0' + i);
                    return currentChar + "";
                }
            }
        }

        int quotient = (int) (k / factorials[cur - 1]);
        int rem = (int) (k % factorials[cur - 1]);
        for (int i = 1; i <= n; i++) {
            if (!used.contains(i)) {
                if (quotient == 0) {
                    used.add(i);
                    char currentChar = (char) ('0' + i);
                    return currentChar + rec2(cur - 1, n, rem, factorials, used);
                }
                quotient--;
            }
        }

        return null;
    }

    public static void main(String[] args) {
//        System.out.println(getPermutation(3, 6));
        System.out.println(getPermutation2(3, 6));
    }
}
