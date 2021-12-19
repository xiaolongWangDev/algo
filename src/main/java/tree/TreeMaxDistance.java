package tree;

import static tree.TreeUtils.*;

public class TreeMaxDistance extends TreeTraversal {

    private static class Info {
        int maxDistance;
        int height;

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }

        @Override
        public String toString() {
            return "md: " + maxDistance + " h: " + height;
        }
    }

    public Info maxDistance(Node root) {
        if (root == null) {
            return new Info(0, 0);
        }

        Info leftInfo = maxDistance(root.left);
        Info rightInfo = maxDistance(root.right);
        int maxDistanceInChildren = Math.max(leftInfo.maxDistance, rightInfo.maxDistance);
        int maxDistanceViaRoot = 1 + leftInfo.height + rightInfo.height;
        int maxDistance = Math.max(maxDistanceViaRoot, maxDistanceInChildren);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(maxDistance, height);
    }

    public static void main(String[] args) {
        int height = 4;
        TreeMaxDistance algo = new TreeMaxDistance();
        Node testData = testData(0, 100, height);
        printTree(testData, height);
        System.out.println(algo.maxDistance(testData));
    }
}
