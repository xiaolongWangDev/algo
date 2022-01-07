package set;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Problem_SetValueBalance {
    public int maxOps(Set<Integer> a, Set<Integer> b) {
        int sumA = a.stream().mapToInt(o -> (int) o).sum();
        int sumB = b.stream().mapToInt(o -> (int) o).sum();
        double avgA = avg(sumA, a.size());
        double avgB = avg(sumB, b.size());
        if (avgA == avgB) return 0;
        int biggerSum = avgA > avgB ? sumA : sumB;
        int smallerSum = avgA > avgB ? sumB : sumA;
        int biggerN = avgA > avgB ? a.size() : b.size();
        int smallerN = avgA > avgB ? b.size() : a.size();
        double biggerAvg = Math.max(avgA, avgB);
        double smallerAvg = Math.min(avgA, avgB);
        List<Integer> bigger = avgA > avgB ? new ArrayList<>(a) : new ArrayList<>(b);
        Set<Integer> smaller = avgA > avgB ? b : a;
        bigger.sort(Comparator.comparingInt(o -> o));

        int ops = 0;
        for (int i : bigger) {
            if (!smaller.contains(i) && i > smallerAvg && i < biggerAvg) {
                biggerSum -= i;
                smallerSum += i;
                biggerN--;
                smallerN++;
                biggerAvg = avg(biggerSum, biggerN);
                smallerAvg = avg(smallerSum, smallerN);
                ops++;
            }
        }

        return ops;
    }

    private double avg(double sum, int n) {
        return sum / n;
    }

    public static void main(String[] args) {
        Problem_SetValueBalance p = new Problem_SetValueBalance();
        System.out.println(p.maxOps(Set.of(0, 10, 11, 12, 13, 14, 15), Set.of(1, 2, 3, 4, 5)));
    }
}
