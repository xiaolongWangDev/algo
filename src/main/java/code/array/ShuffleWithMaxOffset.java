package code.array;

import code.array.sorting.SortingUtils;

import java.util.*;

import static code.array.sorting.SortingUtils.generateTestData;

/**
 * this is a util I created to provide a slightly shuffled sorted list that can be used for {@link Problem_SortNearlySorted} problem
 */
public class ShuffleWithMaxOffset {


    public void shuffle(int maxDistance, int[] input) {
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < input.length; i++) {
            available.add(i);
        }

        int currentIndex = 0;
        int currentValue = input[currentIndex];
        int emptyIndex = currentIndex;
        input[emptyIndex] = -1;
        Random random = new Random();

        while (!available.isEmpty()) {
            int[] options = getOptions(maxDistance, currentIndex, emptyIndex, input.length, available);
            if (options.length == 0) {
                throw new IllegalArgumentException("there's no further solution");
            }
            int nextIndexToTry = options[random.nextInt(options.length)];
            available.remove(nextIndexToTry);
            if (nextIndexToTry == emptyIndex || nextIndexToTry == currentIndex) {
                input[nextIndexToTry] = currentValue;
                // just pick one available spot to start again
                if (!available.isEmpty()) {
                    currentIndex = available.first();
                    currentValue = input[currentIndex];
                    emptyIndex = currentIndex;
                }
            } else {
                int temp = input[nextIndexToTry];
                input[nextIndexToTry] = currentValue;
                currentIndex = nextIndexToTry;
                currentValue = temp;
            }
        }

    }

    private int[] getOptions(int maxDistance, int currentIndex, int emptyIndex, int arrayLength, Set<Integer> available) {
        int minIndex = currentIndex > emptyIndex ? Math.max(currentIndex - maxDistance, 0) : currentIndex;
        int maxIndex = currentIndex > emptyIndex ? currentIndex : Math.min(currentIndex + maxDistance, arrayLength - 1);
        List<Integer> result = new ArrayList<>();
        for (int i = minIndex; i <= maxIndex; i++) {
            if (available.contains(i)) {
                result.add(i);
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int min = 0;
        int max = 20;
        int[] testData = SortingUtils.generateTestData(20, min, max);
        SortingUtils.print(testData);
        new ShuffleWithMaxOffset().shuffle(4, testData);
        SortingUtils.print(testData);
    }
}
