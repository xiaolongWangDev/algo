package practice.other;

import datastructure.MonotonicQueue;
import helper.ValueAndIndex;

import java.util.Arrays;
import java.util.Comparator;

/**
 * A problem to exercise monotonic queue, function monotonicity.
 * <p>
 * # https://oi-wiki.org/ds/monotonous-queue/
 * # https://www.luogu.com.cn/problem/P2698
 * <p>
 * :param coordinates: [x, y] of each rain drop. 1 ≤ N ≤ 100000
 * :param D: required length of time. 1 ≤ D ≤ 1000000
 * :return: the minimum width of basket needed to collect d length of rain water
 */
public class P2698_USACO12MAR_FlowerPot {
    static class Coordinate {
        private final int x;
        private final int y;

        public Coordinate(int[] vector) {
            this.x = vector[0];
            this.y = vector[1];
        }
    }

    public Integer solve(int[][] coordinates, int D) {
        MonotonicQueue<ValueAndIndex<Coordinate>> maxQ = new MonotonicQueue<>(Comparator.comparingInt(a -> a.getValue().y), true);
        MonotonicQueue<ValueAndIndex<Coordinate>> minQ = new MonotonicQueue<>(Comparator.comparingInt(a -> a.getValue().y), false);

        // sort drops by x
        Arrays.sort(coordinates, Comparator.comparingInt((int[] a) -> a[0]));

        // a window covering the first point. Note, nothing is added to the queue at this point
        int left = 0;
        int right = 0;

        Integer minWidth = null;

        while (right < coordinates.length) {

            // keep expand the right of the window till it's at end of data, or we found a valid solution
            while (right < coordinates.length && (maxQ.isEmpty() || maxQ.peekExtreme().getValue().y - minQ.peekExtreme().getValue().y < D)) {
                // when then window size increase, add new elements in
                maxQ.add(new ValueAndIndex<>(new Coordinate(coordinates[right]), right));
                minQ.add(new ValueAndIndex<>(new Coordinate(coordinates[right]), right));
                right++;
            }

            // while the solution is valid, try push the left of the window right, to make the window smaller
            while (maxQ.peekExtreme().getValue().y - minQ.peekExtreme().getValue().y >= D) {
                // the minWidth is the delta of the x of the extreme drops found in the window
                if (minWidth == null) {
                    minWidth = Math.abs(maxQ.peekExtreme().getValue().x - minQ.peekExtreme().getValue().x);
                } else {
                    minWidth = Math.min(minWidth, Math.abs(maxQ.peekExtreme().getValue().x - minQ.peekExtreme().getValue().x));
                }

                // make the window smaller
                left++;

                // when left is moved, the values in the queues might become invalid, evict them
                if(maxQ.peekExtreme().getIndex() < left) {
                    maxQ.popExtreme();
                }
                if(minQ.peekExtreme().getIndex() < left) {
                    minQ.popExtreme();
                }
            }
            // at this point pushing left bound won't give us a valid solution,
            // if the right bound cannot be pushed either,
            // the loop will end
        }

        return minWidth;
    }

    // the essence of the problem is that:
    // S(L, R + b) > S(L, R) > S(L + a, R)

    public static void main(String[] args) {
        var test_input = new int[][]{{6, 3},{2, 4},{3, 10},{12, 15}};

        var q = new P2698_USACO12MAR_FlowerPot();
        System.out.println(q.solve(test_input, 1));
        System.out.println(q.solve(test_input, 2));
        System.out.println(q.solve(test_input, 3));
        System.out.println(q.solve(test_input, 4));
        System.out.println(q.solve(test_input, 5));
        System.out.println(q.solve(test_input, 6));
        System.out.println(q.solve(test_input, 7));
        System.out.println(q.solve(test_input, 8));
        System.out.println(q.solve(test_input, 9));
        System.out.println(q.solve(test_input, 10));
        System.out.println(q.solve(test_input, 11));
        System.out.println(q.solve(test_input, 12));
        System.out.println(q.solve(test_input, 13));
    }
}
