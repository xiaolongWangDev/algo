package tobeorganized.array.sorting;

import java.util.Arrays;

public class MergeSort {

    public void sort(int left, int right, int[] input) {
        if (left >= right) return;
        // the same as (left + right) / 2; but faster and safer
        // that bracket is important!
        int mid = left + ((right - left) >> 1);
        sort(left, mid, input);
        sort(mid + 1, right, input);
        merge(left, right, mid, input);
    }

    void merge(int left, int right, int mid, int[] input) {
        int[] out = new int[right - left + 1];
        int pOut = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            if (input[p1] <= input[p2]) {
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
    }

    public static void main(String[] args) {
        int[] testData = SortingUtils.generateTestData(100000);
        int[] copiedArray = new int[testData.length];
        System.arraycopy(testData, 0, copiedArray, 0, testData.length);
        var algo = new MergeSort();
        algo.sort(0, testData.length - 1, testData);
        Arrays.sort(copiedArray);
        SortingUtils.compareArray(copiedArray, testData);
    }
}
