package practice.leetcode;

public class L393Utf8Validation {
    public static boolean validUtf8(int[] data) {
        int mask1Byte = 0b10000000;
        int mask2Byte = 0b11100000;
        int mask3Byte = 0b11110000;
        int mask4Byte = 0b11111000;

        int pattern1Byte = 0b00000000;
        int pattern2Byte = 0b11000000;
        int pattern3Byte = 0b11100000;
        int pattern4Byte = 0b11110000;

        int followByteMask = 0b11000000;
        int followBytePattern = 0b10000000;

        for (int i = 0; i < data.length; i++) {
            int datum = data[i];
            if ((datum & mask1Byte) == pattern1Byte) {
                continue;
            } else if ((datum & mask2Byte) == pattern2Byte) {
                for(int j = 0; j < 1; j++) {
                    i++;
                    if(i == data.length) return false;
                    if((data[i] & followByteMask) != followBytePattern) return false;
                }
            } else if ((datum & mask3Byte) == pattern3Byte) {
                for(int j = 0; j < 2; j++) {
                    i++;
                    if(i == data.length) return false;
                    if((data[i] & followByteMask) != followBytePattern) return false;
                }

            } else if ((datum & mask4Byte) == pattern4Byte) {
                for(int j = 0; j < 3; j++) {
                    i++;
                    if(i == data.length) return false;
                    if((data[i] & followByteMask) != followBytePattern) return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(validUtf8(new int[]{197,130,1}));
        System.out.println(validUtf8(new int[]{235,140,4}));
    }
}
