package algorithm.bigdata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class FindMissingNumber {
    // there is a long number that's not in the provided data stream.
    // we know the range of the number is [0, rBound]
    // we want to solve this problem using only minimum mem use
    public static Long findAbsenceUsingBinarySearch(Supplier<Iterator<Long>> source, long rBound) {
        long lBound = 0;

        while (lBound < rBound) {
            long mid = lBound + ((rBound - lBound) >> 1);
            Iterator<Long> iter = source.get();
            long leCount = 0;
            long gtCount = 0;
            while (iter.hasNext()) {
                Long num = iter.next();
                if (num >= lBound && num <= rBound) {
                    if (num <= mid) {
                        leCount++;
                    } else {
                        gtCount++;
                    }
                }
            }

            // base case [X, X] when there's only 2 spots to check, if left half has no count then it's left, or it's the right
            if (leCount == 0) {
                return lBound;
            }

            if (gtCount == 0) {
                return rBound;
            }

            // left section can have only more items than the right. if it equals, it means left section is missing value
            if (leCount <= gtCount) {
                rBound = mid;
            } else {
                lBound = mid + 1;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        List<Long> data = new ArrayList<>();
        int nBits = 16;
        long rBound = (1L << nBits) - 19;
        for (long i = 0; i <= rBound; i++) {
            data.add(i);
        }
        int toRemove = new Random().nextInt((int) rBound + 1);
        data.remove(toRemove);
        System.out.println(toRemove);
        System.out.println(findAbsenceUsingBinarySearch(data::iterator, rBound));
    }
}
