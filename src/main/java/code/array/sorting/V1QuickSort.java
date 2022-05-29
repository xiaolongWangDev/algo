package code.array.sorting;

import java.util.Arrays;

import static code.array.sorting.SortingUtils.*;

public class V1QuickSort {
    public void sort(int left, int right, int[] input) {
        if(left >= right) return;
        // leave out the pivot element so that after we swap it into the middle, the size of sub problem is guaranteed to be smaller.
        // otherwise, left sub problem is not gonna divide properly
        int leftPartitionRightPointer = partition(left, right - 1, input[right], input);
        swap(leftPartitionRightPointer + 1, right, input);
        sort(left, leftPartitionRightPointer, input);
        sort(leftPartitionRightPointer + 2, right, input);
    }

    private int partition(int left, int right, int pivot, int[] input) {
        int leftPartitionRightPointer = left - 1;
        for (int i = left; i <= right; i++) {
            if (input[i] <= pivot) {
                leftPartitionRightPointer++;
                swap(i, leftPartitionRightPointer, input);
            }
        }

        return  leftPartitionRightPointer;
    }


    public static void main(String[] args) {
        int[] testData = generateTestData(1000000);
        int[] copiedArray = new int[testData.length];
        System.arraycopy(testData, 0, copiedArray, 0, testData.length);
        var algo = new V1QuickSort();
        algo.sort(0, testData.length -1, testData);
        Arrays.sort(copiedArray);
        compareArray(copiedArray, testData);
    }
}
