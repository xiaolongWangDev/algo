package code.math;

public class Problem_SubSequenceIndex {

    // number of combinations for taking k elements out of n
    private int combination(int n, int k) {
        double result = 1;
        for (int i = 1; i <= k; i++) {
            result *= (n - i + 1) / (double) i;
        }
        return (int) result;
    }


    private int count(int n, int length) {
        if (length == 1) return 1;
        return combination(n - 1, length - 1);
    }

    private int countAll(int length) {
        return combination(26, length);
    }

    public int findIndex(String s) {
        int length = s.length();
        int index = 0;
        for (int i = 1; i < length; i++) {
            index += countAll(i);
        }
        char startingChar = 'a';
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);

            for(int j = 0; j < c - startingChar; j++) {
                index += count(26 - (startingChar - 'a') - j, length - i);
            }
            startingChar = (char)(c + 1);
        }
        return index + 1;
    }

    public static void main(String[] args) {
        Problem_SubSequenceIndex p = new Problem_SubSequenceIndex();
        System.out.println(p.findIndex("abc"));
        System.out.println(p.findIndex("abd"));
        System.out.println(p.findIndex("aeg"));
        System.out.println(p.findIndex("z"));
        System.out.println(p.findIndex("ab"));
    }
}
