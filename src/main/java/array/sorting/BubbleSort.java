package array.sorting;

import java.util.Arrays;

import static array.sorting.SortingUtils.*;

public class BubbleSort {

    public void sort(int[] input) {
        for (int i = input.length - 1; i > 0; i--) {
            boolean noChange = true;
            for (int j = 0; j < i; j++) {
                if (input[j] > input[j + 1]) {
                    swap(j, j + 1, input);
                    noChange = false;
                }
            }
            if (noChange) {
                break;
            }
        }
    }


    public static void main(String[] args) {
        int[] testData = generateTestData(100000);
        int[] copiedArray = new int[testData.length];
        System.arraycopy(testData, 0, copiedArray, 0, testData.length);
        var algo = new BubbleSort();
        algo.sort(testData);
        Arrays.sort(copiedArray);
        compareArray(copiedArray, testData);
    }
}
