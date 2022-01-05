package random;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public class AnyRandomGen {
    public int pToEqualProbability01(Supplier<Integer> givenRandGen) {
        do {
            int first = givenRandGen.get();
            int second = givenRandGen.get();
            if (first == 0 && second == 1) {
                return 0;
            }
            if (first == 1 && second == 0) {
                return 1;
            }
        } while (true);
    }

    public int equalProbability01(Supplier<Integer> givenRandGen, int lower, int upper) {
        int len = upper - lower + 1;
        Integer seed = givenRandGen.get();
        if (len % 2 == 1) {
            while (seed == upper) {
                seed = givenRandGen.get();
            }
        }

        if (seed >= lower + len / 2) {
            return 1;
        } else {
            return 0;
        }
    }

    public int equalProbabilityAny(Supplier<Integer> givenRandGen, int lower, int upper, int tLower, int tUpper) {
        int range = tUpper - tLower + 1;
        int bits = 0;
        while (range > 0) {
            range = range >> 1;
            bits++;
        }
        range = tUpper - tLower + 1;

        int sum;
        do {
            sum = 0;
            for (int i = 0; i < bits; i++) {
                sum += (1 << i) * equalProbability01(givenRandGen, lower, upper);
            }
        } while (sum >= range);

        return tLower + sum;

    }

    public static void main(String[] args) {
        AnyRandomGen algo = new AnyRandomGen();
        Random random = new Random();
        Map<Integer, Integer> counts = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            int res = algo.equalProbabilityAny(() -> 5 + random.nextInt(100), 5, 104, 7, 10);
//            int res = algo.pToEqualProbability01(() -> random.nextInt(100) > 30 ? 0 : 1);
            counts.put(res, counts.getOrDefault(res, 0) + 1);
        }

        System.out.println(counts);
    }
}
