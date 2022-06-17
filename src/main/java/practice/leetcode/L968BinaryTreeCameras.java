package practice.leetcode;


import helper.TreeUtils.TreeNode;

import static helper.NullSafe.nullSafeAdd;
import static helper.NullSafe.nullSafeMinOf;
import static helper.TreeUtils.maxHeight;
import static helper.TreeUtils.printTree;

public class L968BinaryTreeCameras {
    public static class Info {
        Integer putCameraMin;
        Integer notCameraLitMin;
        Integer notCameraNotLitMin;

        public Info(Integer putCameraMin, Integer notCameraLitMin, Integer notCameraNotLitMin) {
            this.putCameraMin = putCameraMin;
            this.notCameraLitMin = notCameraLitMin;
            this.notCameraNotLitMin = notCameraNotLitMin;
        }
    }

    public static int minCameraCover(TreeNode root) {
        var result = minCameraCoverRec(root);
        return nullSafeMinOf(result.putCameraMin, result.notCameraLitMin);
    }


    public static Info minCameraCoverRec(TreeNode root) {
        if (root == null) {
            return new Info(null, 0, 0);
        }
        Info leftInfo = minCameraCoverRec(root.left);
        Info rightInfo = minCameraCoverRec(root.right);
        Integer leftLitMin = nullSafeMinOf(leftInfo.putCameraMin, leftInfo.notCameraLitMin);
        Integer rightLitMin = nullSafeMinOf(rightInfo.putCameraMin, rightInfo.notCameraLitMin);
        Integer leftMin = nullSafeMinOf(leftLitMin, leftInfo.notCameraNotLitMin);
        Integer rightMin = nullSafeMinOf(rightLitMin, rightInfo.notCameraNotLitMin);
        Integer putCameraMin = nullSafeAdd(leftMin, rightMin, 1);
        Integer notPutCameraLitMin = nullSafeMinOf(nullSafeAdd(leftInfo.putCameraMin, rightLitMin), nullSafeAdd(leftLitMin, rightInfo.putCameraMin));
        Integer notPutCameraNotLitMin = nullSafeAdd(leftInfo.notCameraLitMin, rightInfo.notCameraLitMin);
        return new Info(putCameraMin, notPutCameraLitMin, notPutCameraNotLitMin);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.value = 1;
        TreeNode n1 = new TreeNode();
        n1.value = 2;
        TreeNode n2 = new TreeNode();
        n2.value = 3;
        TreeNode n3 = new TreeNode();
        n3.value = 4;
        TreeNode n4 = new TreeNode();
        n4.value = 5;

        root.left = n1;
        root.right = n2;
//        n2.left = n3;
        n2.right = n3;

        printTree(root, maxHeight(root));


//        System.out.println(Arrays.toString(solve(root)));
        System.out.println(minCameraCover(root));

    }
}
