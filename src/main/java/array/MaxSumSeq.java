package array;

public class MaxSumSeq {
    public static class Info {
        int start;
        int end;
        int value;

        public Info(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "start=" + start +
                    ", end=" + end +
                    ", value=" + value +
                    '}';
        }
    }

    public Info find(int[] input) {
        Info max = new Info(0, 0, Integer.MIN_VALUE);
        int cur = 0;
        int start = 0;
        for (int i = 0; i < input.length; i++) {
            cur += input[i];
            if (max.value < cur) {
                max = new Info(start, i, cur);
            }
            if (cur < 0) {
                cur = 0;
                start = i + 1;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        MaxSumSeq algo = new MaxSumSeq();
        System.out.println(algo.find(new int[]{3, 2, -1, 4, -9, 4, -2, 3, 4, -2, 6, -1}));
        System.out.println(algo.find(new int[]{3, 2, -9, 4, -4, 4, -2, 3, 4, -2, 6, -1}));
    }
}
