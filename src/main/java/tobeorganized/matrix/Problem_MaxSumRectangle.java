package tobeorganized.matrix;

import tobeorganized.array.MaxSumSeq;

public class Problem_MaxSumRectangle {
    public static class Info extends MaxSumSeq.Info {
        int startRow;
        int endRow;

        public Info(int start, int end, int value, int startRow, int endRow) {
            super(start, end, value);
            this.startRow = startRow;
            this.endRow = endRow;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "start=" + start +
                    ", end=" + end +
                    ", startRow=" + startRow +
                    ", endRow=" + endRow +
                    ", value=" + value +
                    '}';
        }
    }

    public Info find(int[][] input) {
        Info max = new Info(-1, -1, Integer.MIN_VALUE, -1, -1);

        for (int i = 0; i < input.length; i++) {
            int[] cumulatedSumPerColumn = new int[input[0].length];
            for (int j = i; j < input.length; j++) {
                for (int k = 0; k < input[j].length; k++) {
                    cumulatedSumPerColumn[k] = cumulatedSumPerColumn[k] + input[j][k];
                }
                MaxSumSeq maxSumSeqAlgo = new MaxSumSeq();
                MaxSumSeq.Info lineInfo = maxSumSeqAlgo.find(cumulatedSumPerColumn);
                if (lineInfo.value > max.value) {
                    max = new Info(lineInfo.start, lineInfo.end, lineInfo.value, i, j);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Problem_MaxSumRectangle p = new Problem_MaxSumRectangle();
        int[][] matrix = new int[][]{
                {-5, 3, 6, 4},
                {-7, 4, -5, 3},
                {-10, 1, -100, 4}
        };
        System.out.println(p.find(matrix));
    }

}
