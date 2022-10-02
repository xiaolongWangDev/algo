package algorithm.random;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public class AnyRandomGen {
    // givenRandGen outputs only 0 or 1. The probability is unknown.
    // we want this function to output 0 or 1 at equal probability
    // the trick is though we don't know the input probability P.
    // but the probability of 01 or 10 are both P*(1-P).
    // with that, we can output 0 when we see 01 and 1 when we see 10 or vice versa.
    // when we see the other outcome, just keep going
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

    // givenRandGen output integers from the range [lower, upper], we want to output only 0 or 1 at equal probability
    // when there are even number of integers in the range, it's easy.
    // just when we see the generated number fall in the first half then return 0, otherwise 1.
    // when the number is odd, it's a little extra work to do.
    // basically, we will discard 1 value from the range. if that value is seen, we just regen a value.
    // then depending on which side the value fall onto, we return 0 or 1. just like the even case.
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

    // givenRandGen output integer from range [lower, upper] at equal probability. we want the output range to be
    // [tlower, tupper], at equal probability.
    // the idea is with equalProbability01 we can already gen 0 or 1 equally. Now we just need to do that N times.
    // N being the number of bits of the number range.
    // So, for each bit, there randomly choose 0 or 1. Therefore, the output probability is evenly distributed.
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
