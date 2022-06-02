package practice.leetcode;

import java.util.*;

// O(n)
public class L1TwoSum {
    /**
     * use hashmap to keep the remainder
     * requirement is easy. only find 1 pair
     */
    public static int[] solve(int[] nums, int target) {
        // remainder, index
        int[] result = new int[2];
        Map<Integer, Integer> remain = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            // check first, so we don't grab the number itself back
            Integer otherIndex = remain.get(num);
            if(remain.get(num) != null) {
                result[0] = i;
                result[1] = otherIndex;
            }

            remain.put(target - num, i);

        }

        return result;
    }

    /**
     * find all pairs of different numbers. This can be used by the three sum problem
     *
     * @param nums num array
     * @param target target sum
     * @param start where to start trying. It's used by the three sum problem
     * @return A collection of all pairs. Using linked list for quicker append head operation
     */
    public static Collection<LinkedList<Integer>> twoSumAllUniqueValues(int[] nums, int target, int start) {
        Map<Integer, LinkedList<Integer>> pairs = new HashMap<>();

        Map<Integer, Integer> remain = new HashMap<>();
        for (int i = start; i < nums.length; i++) {
            int num = nums[i];

            // check first, so we don't grab the number itself back
            Integer otherIndex = remain.get(num);
            if (remain.get(num) != null) {
                LinkedList<Integer> pair = new LinkedList<>();
                pair.add(nums[otherIndex]);
                pair.add(nums[i]);
                // to prevent a case like: 3, 6, 3 target 9 reporting (3, [3, 6]), (6, [6, 3])
                if (!pairs.containsKey(target - num)) {
                    pairs.put(num, pair);
                }
            }
            remain.put(target - num, i);
        }

        return pairs.values();
    }

    /**
     * https://en.wikipedia.org/wiki/3SUM#Quadratic_algorithm
     * saving the result and avoid dups takes a lot of lines
     */
    public static Collection<LinkedList<Integer>> orderedTwoSumAllUniqueValues(int[] nums, int target, int start) {
        int left = start;
        int right = nums.length - 1;
        Map<Integer, LinkedList<Integer>> pairs = new HashMap<>();

        while (left < right) {
            if (nums[left] + nums[right] == target) {
                LinkedList<Integer> pair = new LinkedList<>();
                pair.add(nums[left]);
                pair.add(nums[right]);
                pairs.put(nums[left], pair);
                right--;
                left++;
            } else if (nums[left] + nums[right] > target) {
                right--;
            } else {
                left++;
            }
        }

        return pairs.values();
    }

    public static void main(String[] args) {
        var input1 = new int[]{3, 3};
        var input2 = new int[]{3, 3, 6, 6};
        System.out.println(Arrays.toString(solve(input1, 6)));
        System.out.println(orderedTwoSumAllUniqueValues(input2, 9, 0));
    }
}
