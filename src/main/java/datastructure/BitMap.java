package datastructure;

import java.util.Arrays;

public class BitMap {
    private final int len;
    private final int[] map;

    public BitMap(int len) {
        this.len = len;
        this.map = new int[(int) Math.ceil(len / 32.0)];
    }

    public void set(int bit) {
        int indexInMap = bit / 32;
        int bitInInteger = bit % 32;
        map[indexInMap] = (1 << (31 - bitInInteger)) | map[indexInMap];
    }

    public boolean get(int bit) {
        int indexInMap = bit / 32;
        int bitInInteger = bit % 32;
        int mask = (1 << (31 - bitInInteger));
        return  (mask & map[indexInMap]) == mask;
    }

    public static void main(String[] args) {
        var bitMap = new BitMap(60);
        bitMap.set(61);
        System.out.println(Arrays.toString(bitMap.map));
        System.out.println(bitMap.get(32));
        System.out.println(bitMap.get(31));
        System.out.println(bitMap.get(33));
    }
}
