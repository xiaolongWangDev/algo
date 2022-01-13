package array;

import java.util.function.Predicate;

public class BinarySearch {
    public Integer exactMatch(int[] ordered, int target, int l, int r) {
        if (l > r) {
            return null;
        }
        int mid = l + ((r - l) >> 1);
        if (ordered[mid] == target) {
            return mid;
        } else if (ordered[mid] > target) {
            return exactMatch(ordered, target, l, mid - 1);
        } else {
            return exactMatch(ordered, target, mid + 1, r);
        }
    }

    public Integer closest(int[] ordered, int target, int l, int r) {
        if (l > r) {
            if (l == ordered.length) return r;
            if (r == -1) return l;

            return ordered[l] - target < target - ordered[r] ? l : r;
        }
        int mid = l + ((r - l) >> 1);
        if (ordered[mid] == target) {
            return mid;
        } else if (ordered[mid] > target) {
            return closest(ordered, target, l, mid - 1);
        } else {
            return closest(ordered, target, mid + 1, r);
        }
    }

    //    smallest > 2
//    1 2 3 4 5
//    L R
//     LR
//      R L
    public Integer minGreaterThan(int[] ordered, int target, int l, int r) {
        if (l > r) {
            return l == ordered.length ? null : l;
        }
        int mid = l + ((r - l) >> 1);
        if (ordered[mid] >= target) {
            return closest(ordered, target, l, mid - 1);
        } else {
            return closest(ordered, target, mid + 1, r);
        }
    }

    public static void main(String[] args) {
        BinarySearch algo = new BinarySearch();
        int[] ordered = {1, 2, 3, 4, 6};
        for (int j : ordered) {
            System.out.println(algo.exactMatch(ordered, j, 0, ordered.length - 1));
        }
        System.out.println(algo.exactMatch(ordered, 5, 0, ordered.length - 1));
        System.out.println(algo.exactMatch(ordered, 10, 0, ordered.length - 1));
        System.out.println(algo.exactMatch(ordered, -1, 0, ordered.length - 1));

        for (int j : ordered) {
            System.out.println(algo.closest(ordered, j, 0, ordered.length - 1));
        }
        System.out.println(ordered[algo.closest(ordered, -1, 0, ordered.length - 1)]);
        System.out.println(ordered[algo.closest(ordered, 5, 0, ordered.length - 1)]);
        System.out.println(ordered[algo.closest(ordered, 7, 0, ordered.length - 1)]);
    }
}
