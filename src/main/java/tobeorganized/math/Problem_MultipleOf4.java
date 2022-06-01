package tobeorganized.math;

public class Problem_MultipleOf4 {
    // input needs to be positive
    public boolean check(int[] input) {
        int oddCount = 0;
        int multipleOf2OnlyCount = 0;
        int multipleOf4Count = 0;
        for (int i : input) {
            if (i % 4 == 0) {
                multipleOf4Count++;
            } else if (i % 2 == 0) {
                multipleOf2OnlyCount++;
            } else {
                oddCount++;
            }
        }
        if (multipleOf2OnlyCount == 0) {
            // o444444 or o4o4o4o4o444444444...
            return oddCount == 1 ? multipleOf4Count >= 1 : multipleOf4Count >= oddCount - 1;
        } else {
            // 222224o4o4o4o44444
            return multipleOf4Count >= oddCount;
        }
    }

    public static void main(String[] args) {
        Problem_MultipleOf4 p = new Problem_MultipleOf4();
        // 2 6 4 3 8 1 5 7
        System.out.println(p.check(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
        // 2 6 4 3 8 1 12 5 16 7
        System.out.println(p.check(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 12, 16}));
        System.out.println(p.check(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 12, 16, 18, 10, 100}));
    }
}
