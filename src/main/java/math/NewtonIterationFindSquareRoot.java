package math;

public class NewtonIterationFindSquareRoot {
    public double sqrt(double n, double precision) {
        double x = n;
        double y = x * x - n;
        while (Math.abs(x * x - n) > precision) {
            x = -y / (2 * x) + x;
            y = x * x - n;
        }
        return x;
    }

    public static void main(String[] args) {
        NewtonIterationFindSquareRoot algo = new NewtonIterationFindSquareRoot();
        System.out.println(algo.sqrt(10, 0.0001));
        System.out.println(Math.sqrt(10));
    }
}
