package tobeorganized.array;

public class Problem_MaxCoverage {
    // key takeaway: pointer never goes back
    public int find(int windowSize, int[] points) {
        int result = 0;
        int count = 0;
        int j = 0;
        for (int point : points) {
            while (j < points.length && points[j] - point <= windowSize) {
                count++;
                j++;
            }
            if (result < count) {
                result = count;
            }
            count--;
        }
        return result;
    }

    public static void main(String[] args) {
        Problem_MaxCoverage algo = new Problem_MaxCoverage();
        System.out.println(algo.find(5,  new int[]{2, 4, 8, 9, 12, 17}));
        System.out.println(algo.find(14,  new int[]{0, 13, 24, 35, 46, 57, 60, 72, 87}));
    }

}
