package practice.leetcode;

public class L135Candy {
    public static int candy(int[] ratings) {
        int[] candyArrLeftRight = new int[ratings.length];
        for (int i = 0; i < ratings.length; i++) {
            if (i == 0) {
                candyArrLeftRight[i] = 1;
            } else {
                if (ratings[i] <= ratings[i - 1]) {
                    candyArrLeftRight[i] = 1;
                } else {
                    candyArrLeftRight[i] = candyArrLeftRight[i - 1] + 1;
                }
            }
        }
        int[] candyArrRightLeft = new int[ratings.length];
        for (int i = ratings.length - 1; i >= 0; i--) {
            if (i == ratings.length - 1) {
                candyArrRightLeft[i] = 1;
            } else {
                if (ratings[i] <= ratings[i + 1]) {
                    candyArrRightLeft[i] = 1;
                } else {
                    candyArrRightLeft[i] = candyArrRightLeft[i + 1] + 1;
                }
            }
        }

        int result = 0;
        for (int i = 0; i < ratings.length; i++) {
            // why is using the max correct?
            result += Math.max(candyArrLeftRight[i], candyArrRightLeft[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(candy(new int[]{1, 3, 4, 2, 7, 5}));
    }
}
