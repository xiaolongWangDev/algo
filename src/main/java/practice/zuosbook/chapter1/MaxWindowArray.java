package practice.zuosbook.chapter1;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class MaxWindowArray {
    public static int[] solve(int[] data, int w) {
        int[] result = new int[data.length - w + 1];
        Deque<Integer> mq = new LinkedList<>();
        for (int i = 0; i < data.length; i++) {
            int d = data[i];
            while (!mq.isEmpty() && data[mq.peekLast()] < d) mq.pollLast();
            mq.addLast(i);

            if (i >= w - 1) {
                result[i - w + 1] = data[mq.peekFirst()];
                if (mq.peekFirst() == i - w + 1) mq.pollFirst();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solve(new int[]{4, 3, 5, 4, 3, 3, 6, 7}, 3)));
    }
}
