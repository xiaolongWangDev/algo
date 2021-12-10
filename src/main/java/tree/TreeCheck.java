package tree;

import static tree.TreeUtils.*;

public class TreeCheck extends TreeIteration {

    private static class BstChecker {
        Integer prevValue;
        boolean isBst = true;

        public void check(Node node) {
            if (isBst && prevValue != null) {
                isBst = prevValue < node.value;
            }
            prevValue = node.value;
        }
    }

    private static class CbtChecker {
        boolean onlyAllLeaf;
        boolean isCbt = true;

        public void check(Node node) {
            if (isCbt) {
                if (onlyAllLeaf && (node.left != null || node.right != null)) {
                    isCbt = false;
                }
                if (node.right != null && node.left == null) {
                    isCbt = false;
                }
                if (node.left == null || node.right == null) {
                    onlyAllLeaf = true;
                }
            }
        }
    }

    public boolean isBst(Node root) {
        BstChecker checker = new BstChecker();
        shortInOrder(root, checker::check);
        return checker.isBst;
    }

    public boolean isCbt(Node root) {
        CbtChecker checker = new CbtChecker();
        breadthFirst(root, checker::check);
        return checker.isCbt;
    }

    private static class BalanceInfo {
        int height;
        boolean balance;

        BalanceInfo(int height, boolean balance) {
            this.height = height;
            this.balance = balance;
        }

        @Override
        public String toString() {
            return String.format("(%d, %b)", height, balance);
        }
    }

    public BalanceInfo isBalanced(Node node) {
        if (node.left != null && node.right != null) {
            BalanceInfo leftInfo = isBalanced(node.left);
            BalanceInfo rightInfo = isBalanced(node.right);
            int newHeight = Math.max(leftInfo.height, rightInfo.height);
            if (!leftInfo.balance || !rightInfo.balance || Math.abs(leftInfo.height - rightInfo.height) > 1) {
                return new BalanceInfo(newHeight + 1, false);
            } else {
                return new BalanceInfo(newHeight + 1, true);
            }
        } else if (node.left != null || node.right != null) {
            BalanceInfo info = isBalanced(node.left != null ? node.left : node.right);
            int newHeight = info.height + 1;
            if (!info.balance || info.height > 1) {
                return new BalanceInfo(newHeight, false);
            } else {
                return new BalanceInfo(newHeight, true);
            }
        } else {
            return new BalanceInfo(1, true);
        }

    }

    public static void main(String[] args) {
        int min = 0;
        int max = 100;
        int height = 3;
        Node testDataRoot = testData(min, max, height);
        printTree(testDataRoot, height);

        TreeCheck treeCheck = new TreeCheck();
        System.out.println(treeCheck.isBst(testDataRoot));
        System.out.println(treeCheck.isCbt(testDataRoot));
        System.out.println(treeCheck.isBalanced(testDataRoot));


    }
}
