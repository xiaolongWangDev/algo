package tobeorganized.forcerecursive;

import java.util.List;

public class Problem_NQueen {

    public int placeQueen(int row, List<Integer> prev, int n) {
        if (row == n) {
            for (int col : prev) {
                System.out.printf("%" + 2 * (col + 1) + "s\n", "*");
            }
            System.out.println("-----------------");
            return 1;
        } else {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (isValid(row, i, prev)) {
                    prev.add(i);
                    count += placeQueen(row + 1, prev, n);
                    prev.remove(prev.size() - 1);
                }
            }
            return count;
        }
    }

    public int placeQueenBitOptimization(int n) {
        if (n == 0 || n > 32) {
            return 0;
        }

        int allPlaced = (1 << n) - 1;

        return placeQueenBitOptimizationRec(allPlaced, 0, 0, 0);
    }

    public int placeQueenBitOptimizationRec(int allPlaced, int usedCol, int leftDiagonalConstraints, int rightDiagonalConstraints) {
        if (usedCol == allPlaced) {
            return 1;
        }
        int candidates = allPlaced & ~(usedCol | leftDiagonalConstraints | rightDiagonalConstraints);
        int count = 0;
        while (candidates != 0) {
            int candidate = candidates & (~candidates + 1);
            count += placeQueenBitOptimizationRec(allPlaced, usedCol | candidate, (leftDiagonalConstraints | candidate) << 1, (rightDiagonalConstraints | candidate) >>> 1);
            candidates = candidates - candidate;
        }
        return count;
    }

    private boolean isValid(int row, int col, List<Integer> prev) {
        for (int existingRow = 0; existingRow < prev.size(); existingRow++) {
            Integer existingCol = prev.get(existingRow);
            if (col == existingCol || Math.abs(existingRow - row) == Math.abs(existingCol - col)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Problem_NQueen algo = new Problem_NQueen();
//        System.out.println(algo.placeQueen(0, new ArrayList<>(), 8));
        System.out.println(algo.placeQueenBitOptimization(8));
    }
}
