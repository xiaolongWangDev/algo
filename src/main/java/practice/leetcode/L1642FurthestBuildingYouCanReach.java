package practice.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class L1642FurthestBuildingYouCanReach {

    // 4,2,7,6,9,14,12
    // 0 1 2 3 4 5 6
    // x 5 x 3 5 x
    // 2l 4b
    //   l   b l
    public static int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < heights.length - 1; i++) {
            int diff = heights[i + 1] - heights[i];
            // only care if it's going up
            if (diff > 0) {
                // deduct regardless, this way we don't need to compare the root of the heap with current diff
                bricks -= diff;
                heap.add(diff);

                // meaning we need to replace a brick path with a ladder
                if (bricks < 0) {
                    // this makes sure we are always gonna have non-negative bricks. (worse case, the current diff is added back)
                    bricks += heap.poll();
                    if (ladders > 0) {
                        ladders--;
                    } else {
                        // no available ladder, cannot go further
                        return i;
                    }
                }
            }
        }

        return heights.length - 1;
    }

    public static void main(String[] args) {
        System.out.println(furthestBuilding(new int[]{0, 3, 5, 10, 16}, 5, 2));
        System.out.println(furthestBuilding(new int[]{4, 2, 7, 6, 9, 14, 12}, 5, 1));
        System.out.println(furthestBuilding(new int[]{4, 12, 2, 7, 3, 18, 20, 3, 19}, 10, 2));
        System.out.println(furthestBuilding(new int[]{14, 3, 19, 3}, 17, 0));
    }
}
