package algorithm;

import java.util.function.Predicate;

/**
 * Very concise and elegant binary search based on
 * https://www.bilibili.com/video/BV1EW4y1C7vA?share_source=copy_web
 * <p>
 * Compare to traditional implementation, this one has the concept of a blue zone, a red zone and a grey zone.
 * In the beginning, all the elements are in the grey zone.
 * On each iteration, we expand either the blue or the red and reduce the grey zone.
 * In the end, all the elements are either in the blue zone or in the red zone.
 * <p>
 * The L and R, again unlike the traditional, are the exclusive bounds of the grey zone.
 */
public class BinarySearch {

    public static <T> Integer search(int origL, int origR, T[] arr, Predicate<T> isBlue, boolean takeBlue) {
        int l = origL - 1;
        int r = origR + 1;
        while (l + 1 != r) {
            int m = l + (r - l) / 2;
            if (isBlue.test(arr[m])) {
                l = m;
            } else {
                r = m;
            }
        }
        if (takeBlue) {
            if (l == origL - 1) {
                return null;
            } else {
                return l;
            }
        } else {
            if (r == origR + 1) {
                return null;
            } else {
                return r;
            }
        }
    }

    public static Integer findFirstGreaterThan(int x, Integer[] arr) {
        return search(0, arr.length - 1, arr, (v) -> v <= x, false);
    }


    public static Integer findLastLessThanOrEqual(int x, Integer[] arr) {
        return search(0, arr.length - 1, arr, (v) -> v <= x, true);
    }

    public static Integer findFirstGreaterThanOrEqual(int x, Integer[] arr) {
        return search(0, arr.length - 1, arr, (v) -> v < x, false);
    }

    public static Integer findLastLessThan(int x, Integer[] arr) {
        return search(0, arr.length - 1, arr, (v) -> v < x, true);
    }


    public static void main(String[] args) {
        //                           0  1  2  3  4  5  6  7  8  9 10 11 12 13
        var testData = new Integer[]{1, 2, 3, 4, 4, 4, 5, 5, 5, 6, 6, 7, 8, 9};
        System.out.println(findFirstGreaterThan(9, testData));
        System.out.println(findFirstGreaterThan(-1, testData));
        System.out.println(findFirstGreaterThan(5, testData));
        System.out.println("----------------");
        System.out.println(findLastLessThanOrEqual(-1, testData));
        System.out.println(findLastLessThanOrEqual(1, testData));
        System.out.println(findLastLessThanOrEqual(5, testData));
        System.out.println("----------------");
        System.out.println(findFirstGreaterThanOrEqual(10, testData));
        System.out.println(findFirstGreaterThanOrEqual(9, testData));
        System.out.println(findFirstGreaterThanOrEqual(4, testData));
        System.out.println("----------------");
        System.out.println(findLastLessThan(-1, testData));
        System.out.println(findLastLessThan(5, testData));
        System.out.println(findLastLessThan(9, testData));
    }
}
