package practice.leetcode;

import java.util.Arrays;

public class L66PlusOne {
    public static int[] plusOne(int[] digits) {
        boolean all9 = true;
        for (int digit : digits) {
            if (digit != 9) {
                all9 = false;
                break;
            }
        }
        if (all9) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            return result;
        }

        int carry = 1;
        int[] result = new int[digits.length];
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = carry + digits[i];
            carry = sum / 10;
            result[i] = sum % 10;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[]{9, 9})));
        System.out.println(Arrays.toString(plusOne(new int[]{1, 2, 9, 9})));
    }
}
