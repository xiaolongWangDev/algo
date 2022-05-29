package code.pattern;

public class Problem_Apple68 {
    public int minBagsNaive(int nApples) {
        if (nApples % 2 == 1) {
            return -1;
        }
        if (nApples == 0) {
            return 0;
        }

        int eights = nApples / 8;
        int sixes = 0;
        int remainder = nApples - eights * 8;

        while (eights >= 0) {
            if (remainder % 6 == 0) {
                sixes = remainder / 6;
                break;
            } else {
                sixes = -1;
                eights--;
                remainder = nApples - eights * 8;
            }
        }

        return sixes == -1 ? -1 : sixes + eights;
    }

    public int minBagsFormula(int nApples) {
        if (nApples == 0) {
            return 0;
        } else if (nApples % 2 == 1 || nApples < 6 || nApples == 10) {
            return -1;
        } else if (nApples == 6 || nApples == 8) {
            return 1;
        } else if (nApples == 12 || nApples == 14 || nApples == 16) {
            return 2;
        }

        return (nApples - 1) / 8 + 1;
    }

    // when we "charted" the output of the naive solution, we can find a pattern and then squeeze it to a formula
    public static void main(String[] args) {
        Problem_Apple68 algo = new Problem_Apple68();
        for (int i = 0; i < 1000; i++) {
            int bags = algo.minBagsNaive(i);
            int padding = bags == -1 ? 2 : 2 + bags * 2;
            if (bags != algo.minBagsFormula(i)) {
                throw new RuntimeException("failed at " + i);
            }
            System.out.printf("%3d:%" + padding + "d\n", i, bags);
        }
    }
}
