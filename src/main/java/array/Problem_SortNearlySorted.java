package array;

import java.util.Arrays;
import java.util.PriorityQueue;

import static array.sorting.SortingUtils.*;

public class Problem_SortNearlySorted {

    public void sort(int maxDistance, int[] input) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(maxDistance + 1);
        for (int i = 0; i < input.length; i++) {
            minHeap.add(input[i]);

            if (minHeap.size() > maxDistance) {
                //noinspection ConstantConditions
                input[i - maxDistance] = minHeap.poll();
            }
        }

        // add the remaining elements into the end of the array from heap
        int heapSize = minHeap.size();
        while (heapSize > 0) {
            input[input.length - heapSize] = minHeap.poll();
            heapSize = minHeap.size();
        }
    }

    public static void main(String[] args) {
        int min = 0;
        int max = 2000;
        int maxDistance = 4;
        int[] testData = generateTestData(2000000, min, max);
        Arrays.sort(testData);
        int[] copiedArray = new int[testData.length];
        System.arraycopy(testData, 0, copiedArray, 0, testData.length);
//        print(testData);
        new ShuffleWithMaxOffset().shuffle(maxDistance, testData);
//        print(testData);
        new Problem_SortNearlySorted().sort(maxDistance, testData);
//        print(testData);
        compareArray(copiedArray, testData);
    }
}
