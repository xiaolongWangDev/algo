package tobeorganized.array;

public class Problem_CollectRainWater {
    public int collect(int[] heights) {
        if (heights.length <= 2) return 0;
        int leftMax = heights[0];
        int rightMax = heights[heights.length - 1];
        int leftPointer = 1;
        int rightPointer = heights.length - 2;
        int[] water = new int[heights.length];
        while (leftPointer <= rightPointer) {
            if (leftMax <= rightMax) {
                water[leftPointer] = Math.max(0, leftMax - heights[leftPointer]);
                if (leftMax < heights[leftPointer]) {
                    leftMax = heights[leftPointer];
                }
                leftPointer++;
            } else {
                water[rightPointer] = Math.max(0, rightMax - heights[rightPointer]);
                if (rightMax < heights[rightPointer]) {
                    rightMax = heights[rightPointer];
                }
                rightPointer--;
            }
        }
        int sum = 0;
        for (int j : water) {
            sum += j;
        }

        return sum;
    }

    public static void main(String[] args) {
        Problem_CollectRainWater p = new Problem_CollectRainWater();
        /*
        water
        0 2 0 6 9 7 0 4 0
         */
        System.out.println(p.collect(new int[]{10, 8, 12, 5, 2, 4, 11, 3, 7}));
    }
}
