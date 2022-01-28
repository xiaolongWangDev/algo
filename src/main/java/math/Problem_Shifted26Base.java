package math;

public class Problem_Shifted26Base {
    public int to10(String input) {
        int sum = 0;
        int multiplier = 1;
        for (int i = input.length() - 1; i >= 0; i--) {
            char c = input.charAt(i);
            int digit = c - 'A' + 1;
            sum = sum + digit * multiplier;
            multiplier *= 26;
        }
        return sum;
    }

    // A   1
    // AA  27
    // [1, 26] -> [0, 25] -> [A, Z]
    public String to26(int input) {
        int quotient = input;
        StringBuilder sb = new StringBuilder();
        while (quotient != 0) {
            int remainder = (quotient - 1) % 26;
            char c = (char) (remainder + 'A');
            sb.append(c);
            quotient = (quotient - 1) / 26;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Problem_Shifted26Base p = new Problem_Shifted26Base();
        System.out.println(p.to10("A"));
        System.out.println(p.to10("B"));
        System.out.println(p.to10("AA"));
        System.out.println(p.to10("AB"));
        System.out.println(p.to10("AZ"));
        System.out.println(p.to10("BA"));
        System.out.println(p.to10("BB"));
        System.out.println(p.to10("ZZ"));
        System.out.println(p.to10("AAA"));
        System.out.println(p.to10("ZZZ"));
        System.out.println(p.to10("AAAA"));
        System.out.println(p.to26(1));
        System.out.println(p.to26(2));
        System.out.println(p.to26(27));
        System.out.println(p.to26(28));
        System.out.println(p.to26(52));
        System.out.println(p.to26(53));
        System.out.println(p.to26(54));
        System.out.println(p.to26(702));
        System.out.println(p.to26(703));
        System.out.println(p.to26(18278));
        System.out.println(p.to26(18279));
    }
}
