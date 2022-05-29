package code.math;

public class Problem_DivideBy3 {
    public boolean divides(int l, int r) {
        int sum = 0;
        for (int i = l; i <= r; i++) {
            sum += i % 3;
//            System.out.print(i);
        }
        return sum % 3 == 0;
    }

    public boolean betterDivides(int l, int r) {
        if(l % 3 == 2) {
            return r % 3 == 1;
        } else {
            return r % 3 != 1;
        }
    }
//    f(0, 0) = 1
//    f(0, 1) = 0
//    f(0, 2) = 1
//    f(1, 0) = 1
//    f(1, 1) = 0
//    f(1, 2) = 1
//    f(2, 0) = 0
//    f(2, 1) = 1
//    f(2, 2) = 0

    private int trajectory(int x) {
        return 2 * x * x - 3 * x + 1;
    }


    public static void main(String[] args) {
        Problem_DivideBy3 p = new Problem_DivideBy3();
        for (int i = 1; i < 20; i++) {
            for (int j = 1; j < 20; j++) {
                if (j < i) System.out.print(" ");
                else {
                    System.out.print(p.divides(i, j) ? 1 : 0);
                    if(p.divides(i,j) != p.betterDivides(i,j)) throw new RuntimeException();
                }
            }
            System.out.println();
        }
            System.out.println();

//        for (int i = 1; i < 20; i++) {
//            for (int j = 1; j < 20; j++) {
//                if (j < i) System.out.print(" ");
//                else {
//                    System.out.print(p.betterDivides(i, j) ? 1 : 0);
//                }
//            }
//            System.out.println();
//        }
    }
}
