package tobeorganized.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static tobeorganized.tree.TreeUtils.*;

public class Problem_TreeMaxWeight extends TreeTraversal {
    private static class Info {
        int maxSum;
        List<Integer> values;

        public Info(int maxSum, List<Integer> values) {
            this.maxSum = maxSum;
            this.values = values;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "maxSum=" + maxSum +
                    ", values=" + values +
                    '}';
        }
    }

    public Info maxWeight(Node root, Consumer<Info> maxKeeper) {
        if (root == null) {
            return new Info(0, List.of());
        }

        Info left = maxWeight(root.left, maxKeeper);
        Info right = maxWeight(root.right, maxKeeper);
        int maxChildSum = Math.max(left.maxSum, right.maxSum);

        if (maxChildSum > 0) {
            List<Integer> values = new ArrayList<>(maxChildSum == left.maxSum ? left.values : right.values);
            values.add(root.value);
            var info = new Info(root.value + maxChildSum, values);
            maxKeeper.accept(info);
            return info;
        } else {
            var info = new Info(root.value, List.of(root.value));
            maxKeeper.accept(info);
            return info;
        }
    }

    private static class MaxKeeper {
        Info max;

        public void updateMax(Info val) {
            if (max == null || max.maxSum < val.maxSum) {
                max = val;
            }
        }
    }

    public static void main(String[] args) {
        int height = 4;
        Problem_TreeMaxWeight p = new Problem_TreeMaxWeight();
        Node testData = testData(-100, 100, height);
        printTree(testData, height);
        MaxKeeper maxKeeper = new MaxKeeper();
        p.maxWeight(testData, maxKeeper::updateMax);
        System.out.println(maxKeeper.max);
    }
}
