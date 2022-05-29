package code.array;

import code.array.sorting.SortingUtils;

public class TwoTierPartition {

    public int partition(int pivot, int[] input) {
        int leftPartitionRightPointer = -1;
        for (int i = 0; i < input.length; i++) {
            if (input[i] < pivot) {
                leftPartitionRightPointer++;
                SortingUtils.swap(i, leftPartitionRightPointer, input);
            }
        }

        return leftPartitionRightPointer;
    }

    public static void main(String[] args) {
        int min = 0;
        int max = 3000;
        int[] testData = SortingUtils.generateTestData(1000000, min, max);
        int pivot = SortingUtils.generateRandomInt(min, max);
        var algo = new TwoTierPartition();
        int leftPartitionRightPointer = algo.partition(pivot, testData);

        for (int i = 0; i < testData.length; i++) {
            if (i <= leftPartitionRightPointer) {
                if (testData[i] >= pivot) {
                    throw new IllegalArgumentException(String.format("violation %d at %d", testData[i], i));
                }
            } else {
                if (testData[i] < pivot) {
                    throw new IllegalArgumentException(String.format("violation %d at %d", testData[i], i));
                }
            }
        }
    }
}
