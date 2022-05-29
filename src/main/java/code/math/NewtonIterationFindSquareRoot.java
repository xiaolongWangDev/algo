package code.math;

public class NewtonIterationFindSquareRoot {
    public double sqrt(double n, double epsilon) {
        double x = n;
        double y = x * x - n;
        while (Math.abs(-y / (2 * x)) > epsilon) {
            x = -y / (2 * x) + x;
            y = x * x - n;
        }
        return x;
    }

    public static void main(String[] args) {
        NewtonIterationFindSquareRoot algo = new NewtonIterationFindSquareRoot();
        System.out.println(algo.sqrt(10, 1e-4));
        System.out.println(Math.sqrt(10));
    }
}
