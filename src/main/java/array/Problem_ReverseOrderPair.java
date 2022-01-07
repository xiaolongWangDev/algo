package array;

import java.util.ArrayList;
import java.util.List;

import static array.sorting.SortingUtils.print;

public class Problem_ReverseOrderPair {

    public List<List<Integer>> sortAndFindReversePair(int left, int right, int[] input) {
        if (left >= right) return List.of();
        // the same as (left + right) / 2; but faster and safer
        // that bracket is important!
        int mid = left + ((right - left) >> 1);

        List<List<Integer>> reversePair = new ArrayList<>();
        reversePair.addAll(sortAndFindReversePair(left, mid, input));
        reversePair.addAll(sortAndFindReversePair(mid + 1, right, input));
        reversePair.addAll(merge(left, right, mid, input));
        return reversePair;
    }

    List<List<Integer>> merge(int left, int right, int mid, int[] input) {
        List<List<Integer>> reversePair = new ArrayList<>();
        int[] out = new int[right - left + 1];
        int pOut = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            if (input[p1] > input[p2]) {
                reversePair.add(List.of(input[p1], input[p2]));
                out[pOut++] = input[p1];
                p1++;
            } else {
                out[pOut++] = input[p2];
                p2++;
            }
        }
        while (p1 <= mid) {
            out[pOut++] = input[p1++];
        }
        while (p2 <= right) {
            out[pOut++] = input[p2++];
        }

        // easily tricked! out's index is not the same as input's index
        for (int i = 0; i < out.length; i++) {
            input[left + i] = out[i];
        }

        return reversePair;
    }

    public static void main(String[] args) {
        int[] testData = {3, 2, 4, 5, 0};
        print(testData);
        var algo = new Problem_ReverseOrderPair();
        List<List<Integer>> reversePairs = algo.sortAndFindReversePair(0, testData.length - 1, testData);
        print(testData);
        reversePairs.forEach(l -> {
            System.out.printf("(%d, %d)%n", l.get(0), l.get(1));
        });
    }
}
