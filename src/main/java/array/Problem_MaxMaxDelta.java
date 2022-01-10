package array;

public class Problem_MaxMaxDelta {
    public int findMaxDelta(int[] input) {
        int max = 0;
        for (int i : input) {
            if (max < i) {
                max = i;
            }
        }
        // either max is put in the left section, or the right section.
        // whichever max is in, we want the max of the other section to be as small as possible
        // when in left, the right section must include the last element, so the max of right section cannot be less
        // than the last element's value. there's no need to add more elements into the right section because it won't
        // bring down the max of that section.
        // vice versa for the other scenario

        return Math.max(max - input[0], max - input[input.length - 1]);
    }

    public static void main(String[] args) {
        Problem_MaxMaxDelta p = new Problem_MaxMaxDelta();
        System.out.println(p.findMaxDelta(new int[]{10, 4, 3, 9, 15, 20, 3, 9}));
    }
}
