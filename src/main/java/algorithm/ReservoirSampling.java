package algorithm;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ReservoirSampling {
    public static int sampleOne(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            var r = Math.random();
            if (r <= 1.0 / i) { // divide by i is the key here. The later value need to have smaller probability
                result = i;
            }
        }
        return result;
    }

    public static int sampleOneNaive(int n) {
        int result = 1;
        var r = Math.random();
        for (int i = 1; i < n; i++) {
            if (r <= ((double) i) / n) return i;
        }
        return n;
    }

    public static int[] sampleKNaive(int n, int k) {
        int[] res = new int[k];
        for(int i = 0; i<k; i++) {
            res[i] = sampleOneNaive(n);
        }
        return res;
    }
    public static int[] sampleK(int n, int k) {
        int[] results = new int[k];
        Random random = new Random();
        for (int i = 1; i <= n; i++) {
            if (i <= k) {
                results[i - 1] = i;
            } else {
                int j = random.nextInt(i) + 1; // [0, i -1] -> [1, i]
                if (j <= k) { // probability to select i is k/i
                    results[j - 1] = i;
                }
            }
        }

        return results;
    }

    public static void main(String[] args) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            int sample = sampleOneNaive(5);
            counts.putIfAbsent(sample, 0);
            counts.put(sample, counts.get(sample) + 1);
        }
        System.out.println(counts);

        //
        counts.clear();
        for (int i = 0; i < 1000; i++) {
            int[] samples = sampleKNaive(10, 4);
            for (int sample : samples) {
                counts.put(sample, counts.getOrDefault(sample, 0) + 1);
            }
        }
        System.out.println(counts);
    }
}
