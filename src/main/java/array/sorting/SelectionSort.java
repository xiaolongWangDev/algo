package array.sorting;

import java.util.Arrays;

import static array.sorting.SortingUtils.*;

public class SelectionSort {

    public void sort(int[] input) {
        for (int i = 0; i < input.length; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = i; j < input.length; j++) {
                if (minIndex == -1 || input[j] < min) {
                    min = input[j];
                    minIndex = j;
                }
            }
            swap(minIndex, i, input);
        }
    }

    public static void main(String[] args) {
        int[] testData = generateTestData(100000);
        int[] copiedArray = new int[testData.length];
        System.arraycopy(testData, 0, copiedArray, 0, testData.length);
        var algo = new SelectionSort();
        algo.sort(testData);
        Arrays.sort(copiedArray);
        compareArray(copiedArray, testData);
    }
}
