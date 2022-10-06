package tobeorganized.bigdata;

import java.util.*;
import java.util.function.Supplier;

public class Problem_FindAnAbsentInteger {

    public Long findUsingBuckets(Supplier<Iterator<Long>> source, long rBound) {

        int nBuckets = 512; // depends on how much memory we can you, 512 * 4  = 2048 Bytes = 2K memory
        int[] buckets = new int[512];
        // rBounds is 2^n - 1
        int expectedCountPerBucket = (int) ((rBound + 1) / nBuckets);

        long lBound = 0;
        while (expectedCountPerBucket >= 0) {
            Arrays.fill(buckets, 0);
            Iterator<Long> iterator = source.get();
            while (iterator.hasNext()) {
                Long num = iterator.next();
                if (num >= lBound && num <= rBound) {
                    if (expectedCountPerBucket == 0) {
                        buckets[(int) (num - lBound)]++;
                    } else {
                        buckets[(int) (num - lBound) / expectedCountPerBucket]++;
                    }
                }
            }
            if (expectedCountPerBucket == 0) {
                for (int i = 0; i <= rBound - lBound; i++) {
                    if (buckets[i] == 0) {
                        return lBound + i;
                    }
                }
                break;
            } else {
                for (int i = 0; i < nBuckets; i++) {
                    if (buckets[i] < expectedCountPerBucket) {
                        lBound = lBound + (long) expectedCountPerBucket * i;
                        rBound = lBound + expectedCountPerBucket - 1;
                        break;
                    }
                }
            }

            expectedCountPerBucket = (int) ((rBound - lBound + 1) / nBuckets);
        }

        return null;
    }

    public static void main(String[] args) {
        Problem_FindAnAbsentInteger algo = new Problem_FindAnAbsentInteger();
        List<Long> data = new ArrayList<>();
        int nBits = 15;
        long rBound = (1L << nBits) - 1;
        for (long i = 0; i <= rBound; i++) {
            data.add(i);
        }
        int toRemove = new Random().nextInt((int) rBound + 1);
        data.remove(toRemove);
        System.out.println(toRemove);
        System.out.println(algo.findUsingBuckets(data::iterator, rBound));
    }
}
