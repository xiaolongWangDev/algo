package practice.leetcode;

import java.util.Arrays;
import java.util.function.Predicate;

public class L34FindFirstAndLastPositionOfElementInSortedArray {
    enum TargetElement {
        LAST_VALID,
        FIRST_INVALID
    }

    public static int[] searchRange(int[] nums, int target) {
        int firstGe = search(nums, (v) -> v < target, TargetElement.FIRST_INVALID);
        int lastLe = search(nums, (v) -> v <= target, TargetElement.LAST_VALID);
        if (firstGe == -1 || lastLe == -1 || firstGe > lastLe ) {
            return new int[]{-1, -1};
        } else {
            return new int[]{firstGe, lastLe};
        }
    }

    public static int search(int[] nums, Predicate<Integer> valid, TargetElement targetElement) {
        int undeterminedZoneLeft = -1;
        int undeterminedZoneRight = nums.length;
        while (undeterminedZoneLeft != undeterminedZoneRight - 1) {
            int m = undeterminedZoneLeft + ((undeterminedZoneRight - undeterminedZoneLeft) >> 1);
            if (valid.test(nums[m])) {
                undeterminedZoneLeft = m;
            } else {
                undeterminedZoneRight = m;
            }
        }
        if (targetElement == TargetElement.LAST_VALID) {
            return undeterminedZoneLeft;
        } else {
            return undeterminedZoneRight == nums.length ? -1 : undeterminedZoneRight;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));
    }
}
