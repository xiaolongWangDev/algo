package math;

public class FastPowerFunction {
    public int standard(int base, int n) {
        int res = 1;
        int optCount = 0;
        for (int i = 1; i <= n; i++) {
            res *= base;
            optCount++;
        }
        System.out.println("opts: " + optCount);
        return res;
    }

    public int better(int base, int n) {
        int res = 1;
        int optCount = 0;
        int tmp = base;
        while (n != 0) {
            if ((n & 1) == 1) {
                res = res * tmp;
            }
            tmp = tmp * tmp;
            optCount ++;
            n = n >> 1;
        }
        System.out.println("opts: " + optCount);
        return res;
    }

    public static void main(String[] args) {
        FastPowerFunction algo = new FastPowerFunction();
        System.out.println(algo.standard(3, 5));
        System.out.println(algo.better(3, 5));
    }
}
