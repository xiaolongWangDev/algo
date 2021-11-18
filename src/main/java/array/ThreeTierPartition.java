package array;

import java.util.Arrays;
import java.util.Random;

import static array.sorting.SortingUtils.*;

public class ThreeTierPartition {

    public int[] partition(int pivot, int[] input) {
        int leftPartitionRightPointer = -1;
        int rightPartitionLeftPointer = input.length;
        int i = 0;
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
        int min = 0;
        int max = 3000;
        int[] testData = generateTestData(1000000, min, max);
        int pivot = generateRandomInt(min, max);
        var algo = new ThreeTierPartition();
        int[] pointers = algo.partition(pivot, testData);

        for (int i = 0; i < testData.length; i++) {
            if (i <= pointers[0]) {
                if (testData[i] >= pivot) {
                    throw new IllegalArgumentException(String.format("violation %d at %d", testData[i], i));
                }
            } else if(i >= pointers[1]) {
                if (testData[i] <= pivot) {
                    throw new IllegalArgumentException(String.format("violation %d at %d", testData[i], i));
                }
            } else {
                if (testData[i] != pivot) {
                    throw new IllegalArgumentException(String.format("violation %d at %d", testData[i], i));
                }
            }
        }
    }
}
