package array.sorting;

import java.util.Arrays;
import java.util.Random;

import static array.sorting.SortingUtils.*;

public class V3QuickSort {
    public void sort(int left, int right, int[] input) {
        if(left >= right) return;
        int pivot = input[left + new Random().nextInt(right - left + 1)];
        int[] pointers = partition(left, right, pivot, input);
        sort(left, pointers[0], input);
        sort(pointers[1], right, input);
    }

    private int[] partition(int left, int right, int pivot, int[] input) {
        int leftPartitionRightPointer = left - 1;
        int rightPartitionLeftPointer = right + 1;
        int i = left;
        while (i < rightPartitionLeftPointer) {
            if (input[i] < pivot) {
                leftPartitionRightPointer++;
                swap(i, leftPartitionRightPointer, input);
                i++;
            } else if (input[i] > pivot) {
                rightPartitionLeftPointer--;
                swap(i, rightPartitionLeftPointer, input);
            } else {
                i++;
            }
        }

        return  new int[] {leftPartitionRightPointer, rightPartitionLeftPointer};
    }


    public static void main(String[] args) {
        int[] testData = generateTestData(100000, 0, 10000);
        int[] copiedArray = new int[testData.length];
        System.arraycopy(testData, 0, copiedArray, 0, testData.length);
        var algo = new V3QuickSort();
        algo.sort(0, testData.length -1, testData);
        Arrays.sort(copiedArray);
        compareArray(copiedArray, testData);
    }
}
