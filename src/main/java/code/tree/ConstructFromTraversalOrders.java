package code.tree;

import java.util.ArrayList;
import java.util.List;

import static code.tree.TreeUtils.*;

public class ConstructFromTraversalOrders {
    // no duplicated value should exist
    public void fillPostOrder(Integer[] preOrderInput, Integer[] inOrderInput, Integer[] postOrderOutput,
                              int preL, int preR,
                              int inL, int inR,
                              int postL, int postR) {
        if (preOrderInput.length != inOrderInput.length || inOrderInput.length != postOrderOutput.length)
            throw new IllegalArgumentException();

        if (preR - preL != inR - inL || inR - inL != postR - postL)
            throw new IllegalArgumentException();

        if (preR < preL) {
            return;
        }

        if (preL == preR) {
            postOrderOutput[postL] = preOrderInput[preL];
            return;
        }

        postOrderOutput[postR] = preOrderInput[preL];
        int leftTreeSize = 0;
        for (int i = inL; i <= inR; i++) {
            if (inOrderInput[i].equals(preOrderInput[preL])) {
                break;
            }
            leftTreeSize++;
        }
        fillPostOrder(preOrderInput, inOrderInput, postOrderOutput,
                preL + 1, preL + leftTreeSize,
                inL, inL + leftTreeSize - 1,
                postL, postL + leftTreeSize - 1
        );
        fillPostOrder(preOrderInput, inOrderInput, postOrderOutput,
                preL + leftTreeSize + 1, preR,
                inL + leftTreeSize + 1, inR,
                postL + leftTreeSize, postR - 1
        );
    }

    // no duplicated value should exist
    public TreeUtils.Node constructTree(Integer[] preOrderInput, Integer[] inOrderInput,
                                        int preL, int preR,
                                        int inL, int inR) {
        if (preOrderInput.length != inOrderInput.length)
            throw new IllegalArgumentException();

        if (preR - preL != inR - inL)
            throw new IllegalArgumentException();

        if (preR < preL) {
            return null;
        }

        TreeUtils.Node node = new TreeUtils.Node();
        node.value = preOrderInput[preL];
        if (preL == preR) {
            return node;
        }

        int leftTreeSize = 0;
        for (int i = inL; i <= inR; i++) {
            if (inOrderInput[i].equals(preOrderInput[preL])) {
                break;
            }
            leftTreeSize++;
        }

        TreeUtils.Node leftChild = constructTree(preOrderInput, inOrderInput,
                preL + 1, preL + leftTreeSize,
                inL, inL + leftTreeSize - 1
        );
        TreeUtils.Node rightChild = constructTree(preOrderInput, inOrderInput,
                preL + leftTreeSize + 1, preR,
                inL + leftTreeSize + 1, inR
        );
        node.left = leftChild;
        node.right = rightChild;
        return node;
    }

    public static void main(String[] args) {
        int min = 0;
        int max = 1000;
        int height = 4;
        TreeUtils.Node testDataRoot = testData(min, max, height);
        printTree(testDataRoot, height);
        TreeTraversal treeTraversal = new TreeTraversal();
        List<Integer> preOrder = new ArrayList<>();
        treeTraversal.shortPreOrder(testDataRoot, o -> preOrder.add(o.value));
        List<Integer> inOrder = new ArrayList<>();
        treeTraversal.shortInOrder(testDataRoot, o -> inOrder.add(o.value));
        Integer[] postOrder = new Integer[preOrder.size()];

        ConstructFromTraversalOrders algo = new ConstructFromTraversalOrders();
        algo.fillPostOrder(preOrder.toArray(Integer[]::new), inOrder.toArray(Integer[]::new), postOrder,
                0, preOrder.size() - 1, 0, preOrder.size() - 1, 0, preOrder.size() - 1);

        preOrder.forEach(o -> System.out.printf("%4d", o));
        System.out.println();
        inOrder.forEach(o -> System.out.printf("%4d", o));
        System.out.println();
        for (int o : postOrder) {
            System.out.printf("%4d", o);
        }
        System.out.println();

        TreeUtils.Node constructed = algo.constructTree(preOrder.toArray(Integer[]::new), inOrder.toArray(Integer[]::new),
                0, preOrder.size() - 1, 0, preOrder.size() - 1);

        printTree(constructed, maxHeight(constructed));
    }
}
