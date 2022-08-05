package practice.zuosbook.chapter1;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class FirstValueGreatThanFromBothSides {
    public static int[][] solveNoDup(int[] data) {
        Stack<Integer> ms = new Stack<>();
        int[][] result = new int[data.length][2];
        for (int i = 0; i < data.length; i++) {
            while (!ms.isEmpty() && data[ms.peek()] <= data[i]) {
                setResult_(ms, result, i);
            }
            ms.push(i);
        }

        while (!ms.isEmpty()) {
            setResult_(ms, result, -1);
        }
        return result;
    }

    private static void setResult_(Stack<Integer> ms, int[][] result, int whoKickedMeOut) {
        int poppedIndex = ms.pop();
        result[poppedIndex][0] = ms.isEmpty() ? -1 : ms.peek();
        result[poppedIndex][1] = whoKickedMeOut;
    }

    public static int[][] solve(int[] data) {
        // use linked list to easily get last
        Stack<LinkedList<Integer>> ms = new Stack<>();
        int[][] result = new int[data.length][2];
        for (int i = 0; i < data.length; i++) {
            while (!ms.isEmpty() && data[ms.peek().peekLast()] < data[i]) {
                setResult(ms, result, i);
            }
            if (ms.isEmpty() || data[ms.peek().peekLast()] != data[i]) {
                ms.push(new LinkedList<>());
            }
            ms.peek().addLast(i);
        }

        while (!ms.isEmpty()) {
            setResult(ms, result, -1);
        }
        return result;
    }

    private static void setResult(Stack<LinkedList<Integer>> ms, int[][] result, int whoKickedMeOut) {
        LinkedList<Integer> poppedIndexes = ms.pop();
        int prevIndex = ms.isEmpty() ? -1 : ms.peek().peekLast();
        for (var poppedIndex : poppedIndexes) {
            result[poppedIndex][0] = prevIndex;
            result[poppedIndex][1] = whoKickedMeOut;
        }
    }

    public static void main(String[] args) {
//        int[] data = new int[]{4, 3, 1, 5, 6, 2};
//        int[][] res = solveNoDup(data);
        int[] data = new int[]{4, 3, 1, 5, 6, 2, 5, 6, 7};
        int[][] res = solve(data);
        for (int i = 0; i < res.length; i++) {
            int[] a = res[i];
            System.out.printf("%d, %d, %d %n", a[0] == -1 ? -1 : data[a[0]], data[i], a[1] == -1 ? -1 : data[a[1]]);
        }
    }
}
