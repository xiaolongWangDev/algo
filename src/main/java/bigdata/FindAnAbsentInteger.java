package bigdata;

import java.util.*;
import java.util.function.Supplier;

public class FindAnAbsentInteger {
    public Long findUsingLeastSpace(Supplier<Iterator<Long>> source, long rBound) {
        int level = 0;
        long lBound = 0;
        while (rBound > 1L << level + 1) {
            level++;
        }
        while (level >= 0) {
            Iterator<Long> iterator = source.get();
            int geCount = 0;
            int ltCount = 0;
            long pivot = lBound + (1L << level);
            while (iterator.hasNext()) {
                Long num = iterator.next();
                if (num >= lBound && num <= rBound) {
                    if (num >= pivot) {
                        geCount++;
                    } else {
                        ltCount++;
                    }
                }
            }
            if (ltCount == 0) return lBound;
            if (geCount == 0) return pivot;
            if (ltCount > geCount) {
                lBound = pivot;
            } else {
                rBound = pivot - 1;
            }
            level = level - 1;
        }
        return null;
    }

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
        FindAnAbsentInteger algo = new FindAnAbsentInteger();
        List<Long> data = new ArrayList<>();
        int nBits = 15;
        long rBound = (1L << nBits) - 1;
        for (long i = 0; i <= rBound; i++) {
            data.add(i);
        }
        int toRemove = new Random().nextInt((int) rBound + 1);
        data.remove(toRemove);
        System.out.println(toRemove);
//        System.out.println(algo.findUsingLeastSpace(data::iterator, rBound));
        System.out.println(algo.findUsingBuckets(data::iterator, rBound));
    }
}
