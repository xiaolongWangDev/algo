package practice.leetcode;

public class L33SearchInRotatedSortedArray {
    public static int search(int[] nums, int target) {
        int pivotIndex = indexOfSmallest(nums, 0, nums.length - 1);
        int result = -1;
        if (pivotIndex - 1 >= 0) {
            result = simpleBinarySearch(nums, 0, pivotIndex - 1, target);
        }
        if (result == -1) {
            result = simpleBinarySearch(nums, pivotIndex, nums.length - 1, target);
        }

        return result;
    }

    private static int simpleBinarySearch(int[] nums, int leftIndex, int rightIndex, int target) {
        if (leftIndex > rightIndex) {
            return -1;
        }

        int midIndex = leftIndex + ((rightIndex - leftIndex) >> 1);
        if (nums[midIndex] == target) {
            return midIndex;
        } else if (nums[midIndex] < target) {
            return simpleBinarySearch(nums, midIndex + 1, rightIndex, target);
        } else {
            return simpleBinarySearch(nums, leftIndex, midIndex - 1, target);
        }
    }

    /*
    when Vm > Vr, the min value must be in the range [m+1, r]. This is intuitive, the proof is omitted.
    And Vm cannot be the smallest because it's already greater than Vr.

    When Vm <= Vr, the min value must be in the range [l, m].
    Let's prove that:
        when Vm < Vr, let's assume the min is not in [l, m] and we'll induce a contradiction.
         Then values in [l, m] are non-decreasing, hence Vl < Vm.
         And again since min is not in [l, m], Vl will be greater than all the values that's between [Vmin, Vr].
         Therefore, Vr < Vl < Vm (contradicts Vm < Vr)
         Therefore, when Vm < Vr, we can say the min is in [l, m]
        when Vm = Vr, this implies all values in [m, r] needs to be the same because of the non-decreasing property.
        Let's assume again the min is not in [l, m] and we'll induce a contradiction.
         min = Vm = V(m+1) = ... = Vr
         Vm is in [l, m]. So the min is in [l, m]. (here comes the contradiction)
         Therefore, when Vm = Vr, we can say the min is in [l, m]
     */
    private static int indexOfSmallest(int[] nums, int leftIndex, int rightIndex) {
        if (leftIndex == rightIndex) {
            return leftIndex;
        }
        int middleIndex = leftIndex + ((rightIndex - leftIndex) >> 1);

        if (nums[rightIndex] < nums[middleIndex]) {
            return indexOfSmallest(nums, middleIndex + 1, rightIndex);
        } else {
            return indexOfSmallest(nums, leftIndex, middleIndex);
        }
    }
    // 3 3 1 1 1 2 2 2

    public static void main(String[] args) {
        System.out.println(indexOfSmallest(new int[]{5, 4, 4}, 0, 2));
        System.out.println(simpleBinarySearch(new int[]{1, 2, 4, 6}, 0, 3, 6));
        System.out.println(search(new int[]{9, 13, 1, 2, 4, 6}, 4));
    }
}
