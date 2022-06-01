package tobeorganized.array.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DigitBasedSort {

    public void sort(int[] input) {
        int max = findMax(input);
        int maxDigits = countDigits(max);
        Map<Integer, List<Integer>> buckets = resetBuckets();
        for (int i = 1; i <= maxDigits; i++) {
            for (int number : input) {
                buckets.get(getDigit(number, i)).add(number);
            }
            bucketToArray(buckets, input);
            buckets = resetBuckets();
        }
    }

    /**
     * instead of using real buckets to hold the real element,
     * this smartly maintains a table of counts
     */
    public void enhancedSort(int[] input) {
        int max = findMax(input);
        int maxDigits = countDigits(max);
        for (int i = 1; i <= maxDigits; i++) {
            int[] noGreaterThanCount = getNoGreaterThanCount(input, i);
            int[] temp = new int[input.length];
            for (int j = input.length - 1; j >= 0; j--) {
                int number = input[j];
                int digit = getDigit(number, i);
                int targetLocation = noGreaterThanCount[digit] - 1;
                temp[targetLocation] = number;
                noGreaterThanCount[digit]--;
            }
            for (int k = 0; k < temp.length; k++) {
                input[k] = temp[k];
            }
        }
    }

    /**
     * just to show left to right also works
     */
    public void enhancedSortV2(int[] input) {
        int max = findMax(input);
        int maxDigits = countDigits(max);
        for (int i = 1; i <= maxDigits; i++) {
            int[] noGreaterThanCount = getNoGreaterThanCountV2(input, i);
            int[] temp = new int[input.length];
            for (int j = 0; j < input.length; j++) {
                int number = input[j];
                int digit = getDigit(number, i);
                int targetLocation = noGreaterThanCount[digit];
                temp[targetLocation] = number;
                noGreaterThanCount[digit]++;
            }
            for (int k = 0; k < temp.length; k++) {
                input[k] = temp[k];
            }
        }
    }

    private int[] getNoGreaterThanCount(int[] input, int digitAt) {
        int[] result = new int[10];
        for (int number : input) {
            int digit = getDigit(number, digitAt);
            result[digit] += 1;
        }
        for (int i = 1; i < 10; i++) {
            result[i] += result[i - 1];
        }

        return result;
    }

    private int[] getNoGreaterThanCountV2(int[] input, int digitAt) {
        int[] result = new int[10];
        for (int number : input) {
            int digit = getDigit(number, digitAt);
            result[digit] += 1;
        }
        for (int i = 1; i < 10; i++) {
            result[i] += result[i - 1];
        }
        for (int i = 9; i > 0; i--) {
            result[i] = result[i - 1];
        }
        result[0] = 0;

        return result;
    }

    private Map<Integer, List<Integer>> resetBuckets() {
        return Map.of(0, new ArrayList<>(),
                1, new ArrayList<>(),
                2, new ArrayList<>(),
                3, new ArrayList<>(),
                4, new ArrayList<>(),
                5, new ArrayList<>(),
                6, new ArrayList<>(),
                7, new ArrayList<>(),
                8, new ArrayList<>(),
                9, new ArrayList<>()
        );
    }

    private void bucketToArray(Map<Integer, List<Integer>> bucket, int[] output) {
        int k = 0;
        for (int j = 0; j <= 9; j++) {
            for (int value : bucket.get(j)) {
                output[k++] = value;
            }
        }
    }

    private int getDigit(int number, int at) {
        if (number >= Math.pow(10, at - 1)) {
            int quotient = number;
            for (int i = 1; i < at; i++) {
                quotient = quotient / 10;
            }
            return quotient % 10;

        } else {
            return 0;
        }

    }

    private int countDigits(int number) {
        int digits = 0;
        while (number > 0) {
            number = number / 10;
            digits++;
        }
        return digits;
    }

    private int findMax(int[] input) {
        int max = Integer.MIN_VALUE;
        for (int j : input) {
            max = Math.max(max, j);
        }
        return max;
    }

    public static void main(String[] args) {
        int min = 0;
        int max = 30;
        int[] testData = SortingUtils.generateTestData(100000, min, max);
        int[] copiedArray = new int[testData.length];
        System.arraycopy(testData, 0, copiedArray, 0, testData.length);
        var algo = new DigitBasedSort();
        algo.enhancedSortV2(testData);
//        print(testData);
        Arrays.sort(copiedArray);
//        print(copiedArray);
        SortingUtils.compareArray(copiedArray, testData);
    }
}
