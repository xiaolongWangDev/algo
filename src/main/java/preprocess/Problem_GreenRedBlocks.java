package preprocess;

public class Problem_GreenRedBlocks {

    //  RGRGRGG to RRRGGGG, paint 2 times
    public int paint(char[] blocks) {
        // when looking to the left at index i, how many blocks are green
        int[] leftGs = new int[blocks.length + 1];
        int gs = 0;
        for (int i = 0; i < blocks.length; i++) {
            leftGs[i] = gs;
            if (blocks[i] == 'G') gs++;
        }
        leftGs[blocks.length] = gs;

        // when looking to the right at index i - 1, how many blocks are red
        // * you can think rightRs[0] is looking at index -1, so it's observing all blocks
        int[] rightRs = new int[blocks.length + 1];
        int rs = 0;
        for (int i = blocks.length - 1; i >= 0; i--) {
            rightRs[i + 1] = rs;
            if (blocks[i] == 'R') rs++;
        }
        rightRs[0] = rs;

        int min = Integer.MAX_VALUE;
        // assume i is the length of the target R zone
        // getting the index right is the trickiest thing in the problem
        for (int i = 0; i < blocks.length; i++) {
            min = Math.min(min, leftGs[i] + rightRs[i]);
        }
        return min;
    }

    public static void main(String[] args) {
        Problem_GreenRedBlocks algo = new Problem_GreenRedBlocks();
        System.out.println(algo.paint("RGRGRGG".toCharArray()));
    }
}
