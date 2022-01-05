package preprocess;

public class GreenRedBlocks {

    //  RGRGRGG to RRRGGGG, paint 2 times
    public int paint(char[] blocks) {
        int[] leftGs = new int[blocks.length + 1];
        int gs = 0;
        for (int i = 0; i < blocks.length; i++) {
            leftGs[i] = gs;
            if (blocks[i] == 'G') gs++;
        }
        leftGs[blocks.length] = gs;

        int[] rightRs = new int[blocks.length + 1];
        int rs = 0;
        for (int i = blocks.length - 1; i >= 0; i--) {
            rightRs[i + 1] = rs;
            if (blocks[i] == 'R') rs++;
        }
        rightRs[0] = rs;

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < blocks.length; i++) {
            min = Math.min(min, leftGs[i] + rightRs[i]);
        }
        return min;
    }

    public static void main(String[] args) {
        GreenRedBlocks algo = new GreenRedBlocks();
        System.out.println(algo.paint("RGRGRGG".toCharArray()));
    }
}
