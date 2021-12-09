package tree;

import static tree.TreeUtils.*;

public class TreeIteration {
    public static void main(String[] args) {
        int min = 0;
        int max = 10;
        int height = 4;
        Node testDataRoot = testData(min, max, height);
        printTree(testDataRoot, height);
    }

}
