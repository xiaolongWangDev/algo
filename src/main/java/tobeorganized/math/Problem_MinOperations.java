package tobeorganized.math;

import java.util.ArrayList;
import java.util.List;

public class Problem_MinOperations {
    /* Description *
    2 operations:
    1. m = s; s = s + s
    2. s = s + m

    start with s = "a", m = "a"
    what's the minimum steps to take to get s to "aaa...a" that has N a's in it
     */

    /* thoughts *
    first step is to view this string problem to a number problem
    e.g. s = s + s => s = 2*s
    m can only be assigned from s.
    So if we do op1 1 time, and them op2 k time it is equivalent to make the new s a multiple of old s.
    To be exact, s = (2 + k) * s.
    last, if the target string's length is a prime number L, it can only be done by doing op2 L - 1 times or op1 1 time followed
    by op2 L - 2 time.
    So if we get all the prime factors of N. Say N = A * B * C
    First, bring s to A will take A ops.
    Then run op1, A is set as the new multiplier. And it's immediately become 2A already, from A*2 to A*B, it takes
    B - 2 op2. So to total ops to get A*B is B - 1
    For C is the same flow all over again. 1 op1 and C - 2 op2.
    So all the ops are A - 1 + B -1 + C -1 = A + B + C - (number of factors)

    This does not prove it is the minimum, but like most greedy alg, I'll leave the proof to math people
     */
    public int minOps(int N) {
        List<Integer> primeFactors = primeFactors(N);
        if (primeFactors.isEmpty()) {
            return N;
        } else {
            int sum = 0;
            for (int primeFactor : primeFactors) {
                sum += primeFactor;
            }
            return sum - primeFactors.size();
        }
    }

    private List<Integer> primeFactors(int num) {
        int quotient = num;
        List<Integer> res = new ArrayList<>();
        while (!isPrime(quotient)) {
            for (int i = 2; i < quotient; i++) {
                if (quotient % i == 0 && isPrime(i)) {
                    res.add(i);
                    quotient = quotient / i;
                }
            }
        }
        if (quotient != num) {
            res.add(quotient);
        }
        return res;
    }

    private boolean isPrime(int k) {
        for (int i = 2; i <= Math.sqrt(k); i++) {
            if (k % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Problem_MinOperations p = new Problem_MinOperations();
        System.out.println(p.minOps(19));
        System.out.println(p.minOps(10));
        System.out.println(p.minOps(20));
    }
}
