package code.array;

import code.array.sorting.SortingUtils;

public class Problem_SmallSum {

    public int sortAndGetSmallSum(int left, int right, int[] input) {
        if (left >= right) return 0;
        // the same as (left + right) / 2; but faster and safer
        // that bracket is important!
        int mid = left + ((right - left) >> 1);

        return sortAndGetSmallSum(left, mid, input) + sortAndGetSmallSum(mid + 1, right, input) + merge(left, right, mid, input);
    }

    int merge(int left, int right, int mid, int[] input) {
        int[] out = new int[right - left + 1];
        int smallSum = 0;
        int pOut = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            if (input[p1] < input[p2]) {
                smallSum += input[p1] * (right - p2 + 1);
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

        return smallSum;
    }

    private static int bruteForce(int[] input) {
        int smallSum = 0;
        for (int j = input.length - 1; j > 0; j--) {
            for (int i = 0; i < j; i++) {
                if (input[i] < input[j]) {
                    smallSum += input[i];
                }
            }
        }
        return smallSum;
    }

    public static void main(String[] args) {
        int[] testData = SortingUtils.generateTestData(100000);
        int[] copiedArray = new int[testData.length];
        System.arraycopy(testData, 0, copiedArray, 0, testData.length);
        var algo = new Problem_SmallSum();
        int smallSum = algo.sortAndGetSmallSum(0, testData.length - 1, testData);
        int target = bruteForce(copiedArray);
        if(smallSum != target) {
            throw new IllegalArgumentException(String.format("small sum are different, target %d, result %d", target, smallSum));
        }
    }
}
