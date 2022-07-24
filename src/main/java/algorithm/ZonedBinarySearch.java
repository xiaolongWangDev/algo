package algorithm;

import java.util.function.Predicate;

/**
 * Very concise and elegant binary search based on
 * https://www.bilibili.com/video/BV1EW4y1C7vA?share_source=copy_web
 * <p>
 * Compare to traditional implementation, this one has the concept of a valid zone,
 * an invalid zone and an undetermined zone.
 * In the beginning, all the elements are undetermined.
 * On each iteration, we expand either the valid or the invalid and reduce the undetermined zone.
 * In the end, all the elements are either in the valid zone or in the invalid zone.
 * <p>
 * The L and R, again unlike the traditional, are the exclusive bounds of the undetermined zone.
 */
public class ZonedBinarySearch {

    enum TargetElement {
        LAST_VALID,
        FIRST_INVALID
    }

    public static <T> Integer search(int l, int r, T[] nums, Predicate<T> valid, TargetElement targetElement) {
        // these bounds are exclusive
        int undeterminedZoneLeft = l - 1;
        int undeterminedZoneRight = r + 1;
        // while undetermined zone still has any element
        while (undeterminedZoneLeft != undeterminedZoneRight - 1) {
            int m = undeterminedZoneLeft + ((undeterminedZoneRight - undeterminedZoneLeft) >> 1);
            if (valid.test(nums[m])) {
                // because the data is monotonic, if m is valid,
                // all elements that are on the left of m and m itself are valid
                // so, they all become determined
                // move undetermined zone left to m. (set to m because it's exclusive. though m is determined)
                undeterminedZoneLeft = m;
            } else {
                // because the data is monotonic, if m is invalid,
                // all elements that are on the right of m and m itself are invalid
                // so, they all become determined
                // move undetermined zone right to m. (set to m because it's exclusive. though m is determined)
                undeterminedZoneRight = m;
            }
        }
        // all elements now are determined. they are either valid or invalid.
        // the data is split into 2 zones: the valid and the invalid

        if (targetElement == TargetElement.LAST_VALID) {
            //if undetermined zone left didn't move, it means there's nothing valid
            return undeterminedZoneLeft == l - 1 ? null : undeterminedZoneLeft;
        } else { // this is when targetElement == TargetElement.FIRST_INVALID
            //if undetermined zone right didn't move, it means there's nothing invalid
            return undeterminedZoneRight == r + 1 ? null : undeterminedZoneRight;
        }
    }

    public static Integer findFirstGreaterThan(int x, Integer[] arr) {
        return search(0, arr.length - 1, arr, (v) -> v <= x, TargetElement.FIRST_INVALID);
    }


    public static Integer findLastLessThanOrEqual(int x, Integer[] arr) {
        return search(0, arr.length - 1, arr, (v) -> v <= x, TargetElement.LAST_VALID);
    }

    public static Integer findFirstGreaterThanOrEqual(int x, Integer[] arr) {
        return search(0, arr.length - 1, arr, (v) -> v < x, TargetElement.FIRST_INVALID);
    }

    public static Integer findLastLessThan(int x, Integer[] arr) {
        return search(0, arr.length - 1, arr, (v) -> v < x, TargetElement.LAST_VALID);
    }


    public static void main(String[] args) {
        //                           0  1  2  3  4  5  6  7  8  9 10 11 12 13
        var testData = new Integer[]{1, 2, 3, 4, 4, 4, 5, 5, 5, 6, 6, 7, 8, 9};
        System.out.println(findFirstGreaterThan(9, testData)); // null
        System.out.println(findFirstGreaterThan(-1, testData)); // 0
        System.out.println(findFirstGreaterThan(5, testData)); // 9
        System.out.println("----------------");
        System.out.println(findLastLessThanOrEqual(-1, testData)); // null
        System.out.println(findLastLessThanOrEqual(1, testData)); // 0
        System.out.println(findLastLessThanOrEqual(5, testData)); // 8
        System.out.println("----------------");
        System.out.println(findFirstGreaterThanOrEqual(10, testData)); // null
        System.out.println(findFirstGreaterThanOrEqual(9, testData)); // 13
        System.out.println(findFirstGreaterThanOrEqual(4, testData)); // 3
        System.out.println("----------------");
        System.out.println(findLastLessThan(-1, testData));// null
        System.out.println(findLastLessThan(5, testData)); // 5
        System.out.println(findLastLessThan(9, testData)); // 12
    }
}
