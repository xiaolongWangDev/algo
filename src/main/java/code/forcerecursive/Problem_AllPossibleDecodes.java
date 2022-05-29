package code.forcerecursive;

public class Problem_AllPossibleDecodes {

    public int find(int i, char[] input) {
        if (i == input.length) {
            return 1;
        }

        if (input[i] == '0') {
            return 0;
        } else if (input[i] == '1') {
            if (i + 1 < input.length) {
                return find(i + 1, input) + find(i + 2, input);
            } else {
                return find(i + 1, input);
            }
        } else if (input[i] == '2') {
            if (i + 1 < input.length) {
                char nextChar = input[i + 1];
                if (nextChar >= '0' && nextChar <= '6') {
                    return find(i + 1, input) + find(i + 2, input);
                } else {
                    return find(i + 1, input);
                }
            } else {
                return find(i + 1, input);
            }
        } else {
            return find(i + 1, input);
        }
    }

    public static void main(String[] args) {
        Problem_AllPossibleDecodes algo = new Problem_AllPossibleDecodes();
        System.out.println(algo.find(0, "10111".toCharArray()));
    }
}
