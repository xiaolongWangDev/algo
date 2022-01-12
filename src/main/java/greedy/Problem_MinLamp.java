package greedy;

public class Problem_MinLamp {
    public int min(String s) {
        int spaceCount = 0;
        int lamps = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'x') {
                lamps += Math.ceil(spaceCount / 3.0);
                spaceCount = 0;
            } else {
                spaceCount++;
            }
        }
        if (spaceCount > 0) {
            lamps += Math.ceil(spaceCount / 3.0);
        }
        return lamps;
    }

    public static void main(String[] args) {
        Problem_MinLamp p = new Problem_MinLamp();
        System.out.println(p.min("x..x...xxx..x"));
        System.out.println(p.min("x..x...xxx..x......"));
        System.out.println(p.min("x..x...xxx..x......."));
    }
}
