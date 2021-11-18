package array.sorting;

import java.util.Random;

import static java.lang.Math.abs;

public class SortingUtils {


    public static void swap(int a, int b, int[] arr) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static int[] generateTestData(int size) {
        Random random = new Random();
        return random.ints(size).toArray();
    }
    public static int[] generateTestData(int size, int min, int max) {
        Random random = new Random();
        return random.ints(size, min, max).toArray();
    }
    public static int generateRandomInt(int min, int max) {
        Random random = new Random();
        return min + random.nextInt(max - min);
    }

    public static void compareArray(int[] target, int[] result) {
        if (target.length != result.length) {
            throw new IllegalArgumentException(String.format("length are different, target %d, result %d", target.length, result.length));
        }
        for (int i = 0; i < target.length; i++) {
            if (target[i] != result[i]) {
                throw new IllegalArgumentException(String.format("value mismatch at %d, target %d, result %d", i, target[i], result[i]));
            }
        }
    }

    public static void print(int[] data) {
        for (int o : data) {
            System.out.print(o);
            System.out.print(" ");
        }
        System.out.println();
    }
}
