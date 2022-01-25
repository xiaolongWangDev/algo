package math;

public class Problem_NimGame {
    public boolean win(int[] piles) {
        if (piles.length == 0) return false;
        int accumulator = piles[0];
        for (int i = 1; i < piles.length; i++) {
            accumulator ^= piles[i];
        }
        return accumulator != 0;
    }

    public static void main(String[] args) {
        Problem_NimGame p = new Problem_NimGame();
        /*
        001
        011
        101
        111
        ---
        000
         */
        System.out.println(p.win(new int[]{1, 3, 5, 7}));
        System.out.println(p.win(new int[]{1, 3, 5, 8}));
        System.out.println(p.win(new int[]{3, 4, 5}));
    }
}
