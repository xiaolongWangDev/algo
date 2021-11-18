package array.sorting;

import java.util.Arrays;

import static array.sorting.SortingUtils.*;

public class InsertionSort {

    public void sort(int[] input) {
        for (int i = 0; i < input.length; i++) {
            int pivot = input[i];
            for (int j = i - 1; j >= 0; j--) {
                if (pivot > input[j]) {
                    break;
                } else {
                    swap(j, j + 1, input);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] testData = generateTestData(100000);
        int[] copiedArray = new int[testData.length];
        System.arraycopy(testData, 0, copiedArray, 0, testData.length);
        var algo = new InsertionSort();
        algo.sort(testData);
        Arrays.sort(copiedArray);
        compareArray(copiedArray, testData);
    }
}
