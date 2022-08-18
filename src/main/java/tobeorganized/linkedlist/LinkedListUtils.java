package tobeorganized.linkedlist;

public class LinkedListUtils {

    public static class Node {
        public int value;
        public Node next;
    }

    public static void print(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.value);
            System.out.print(" ");
            current = current.next;
        }
        System.out.println();
    }

    public static Node generateTestData(int[] elements) {
        Node head = null;
        Node prev = null;
        for (int j : elements) {
            Node node = new Node();
            node.value = j;
            if (prev != null) {
                prev.next = node;
            }
            if (head == null) {
                head = node;
            }
            prev = node;
        }
        return head;
    }
}
