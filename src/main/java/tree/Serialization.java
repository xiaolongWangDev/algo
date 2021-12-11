package tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static tree.TreeUtils.*;

public class Serialization extends TreeIteration {

    public String serialize(Node node) {
        if (node == null) {
            return "#";
        }
        return node.value + "_" +
                serialize(node.left) + "_" +
                serialize(node.right);
    }

    public Node deserialize(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        Queue<String> parts = new LinkedList<>(Arrays.asList(str.split("_")));
        return recursiveDeserializePreOrder(parts);
    }

    public Node recursiveDeserializePreOrder(Queue<String> parts) {
        String part = parts.poll();
        if ("#".equals(part)) {
            return null;
        }
        Node node = new Node();
        node.value = Integer.parseInt(part);
        node.left = recursiveDeserializePreOrder(parts);
        node.right = recursiveDeserializePreOrder(parts);
        return node;
    }


    public static void main(String[] args) {
        int min = 0;
        int max = 100;
        int height = 4;
        // build a random tree of height
        Node testDataRoot = testData(min, max, height);
        printTree(testDataRoot, height);
        Serialization algo = new Serialization();

        // pre-order
        String serialized = algo.serialize(testDataRoot);
        System.out.println(serialized);
        Node deserialized = algo.deserialize(serialized);
        printTree(deserialized, maxHeight(deserialized));

    }
}
