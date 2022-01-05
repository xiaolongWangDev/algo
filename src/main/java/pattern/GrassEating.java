package pattern;

public class GrassEating {
    public char naive(int grass) {
        if (grass == 0) return 'S';
        if (grass == 1) return 'F';
        if (grass == 2) return 'S';
        if (grass == 3) return 'F';

        int eat = 1;
        while (eat <= grass) {
            if (naive(grass - eat) == 'S') return 'F';
            if (grass / 4 < eat) break;
            eat *= 4;
        }
        return 'S';
    }

    public char formula(int grass) {
        return grass % 5 == 0 || grass % 5 == 2 ? 'S' : 'F';
    }

    public static void main(String[] args) {
        GrassEating algo = new GrassEating();
        for (int i = 0; i < 50; i++) {
            if (algo.naive(i) != algo.formula(i)) {
                throw new RuntimeException("failed at " + i);
            }
            System.out.printf("%2d: %c\n", i, algo.naive(i));
        }
    }
}
