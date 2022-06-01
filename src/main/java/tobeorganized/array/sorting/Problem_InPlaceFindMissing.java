package tobeorganized.array.sorting;

import java.util.ArrayList;
import java.util.List;

public class Problem_InPlaceFindMissing {
    public List<Integer> find(int[] input) {
        for (int i = 0; i < input.length; i++) {
            int cur = input[i];
            input[i] = -1;
            while (input[cur - 1] != -1 && cur != input[cur - 1]) {
                int temp = input[cur - 1];
                input[cur - 1] = cur;
                cur = temp;
            }
            input[i] = cur;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            if (i != input[i] - 1) {
                res.add(i + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Problem_InPlaceFindMissing p = new Problem_InPlaceFindMissing();
        System.out.println(p.find(new int[]{3, 2, 3, 5, 6, 1, 6}));
    }
}
