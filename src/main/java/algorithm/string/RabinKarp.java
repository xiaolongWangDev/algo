package algorithm.string;

// an algorithm to find substring using rolling hash
public class RabinKarp {
    static int prime = 5381;//101;
    static int base = 26;//256;

    public static int find(char[] a, char[] b) {
        int len = b.length;
        long bHash = hashArr(b, len);
        long aHash = hashArr(a, len);
        for (int i = 0; i <= a.length - len; i++) {
            if (i != 0) {
                aHash = ((aHash + prime - (a[i - 1] * baseOffset(2)) % prime) * base + a[i + len - 1]) % prime;
            }
            if (aHash == bHash) {
                return i;
            }
        }
        return -1;
    }

    // 97
    // [(97 * 256) % 101 + 98] % 101 = 84
    // arr only has a~z
    private static long hashArr(char[] b, int end) {
        long sum = 0;
        for (int i = 0; i < end; i++) {
            sum = ((sum * base) % prime + b[i]) % prime;
        }
        return sum;
    }

    private static long baseOffset(int n) {
        long sum = 1;
        for (int i = 0; i < n; i++) {
            sum = (base * sum) % prime;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(find("abra".toCharArray(), "bra".toCharArray()));
    }


}
