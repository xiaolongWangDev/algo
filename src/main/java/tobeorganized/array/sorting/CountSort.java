package tobeorganized.array.sorting;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountSort {

    public void sort(int[] input, int min, int max) {
        int[] counts = new int[max - min + 1];

        for (int i = 0; i < input.length; i++) {
            counts[input[i] - min]++;
        }
        int k = 0;
        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts[i]; j++) {
                input[k++] = min + i;
            }
        }
    }

    public void sortUsingHashMap(int[] input, int min, int max) {

        Map<Integer, Long> counts = Arrays.stream(input).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        int k = 0;
        for (int i = min; i <= max; i++) {
            Long count = counts.get(i);
            if(count != null) {
                for (int j = 0; j < count; j++) {
                    input[k++] = i;
                }
            }
        }
    }


    public static void main(String[] args) {
        int min = 30;
        int max = 100;
        int[] testData = SortingUtils.generateTestData(10000000, min, max);
        int[] copiedArray = new int[testData.length];
        System.arraycopy(testData, 0, copiedArray, 0, testData.length);
        var algo = new CountSort();
        algo.sortUsingHashMap(testData, min, max);
//        print(testData);
        Arrays.sort(copiedArray);
//        print(copiedArray);
        SortingUtils.compareArray(copiedArray, testData);
    }
}
