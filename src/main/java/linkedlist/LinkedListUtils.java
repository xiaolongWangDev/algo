package linkedlist;

import java.util.Random;

public class LinkedListUtils {

    public static class Node {
        int value;
        Node next;
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

    public static Node findMedian(Node head) {
        Node fast = head;
        Node slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            if (fast != null) {
                slow = slow.next;
            }
        }

        return slow;
    }

    public static Node reverse(Node head) {
        Node current = head;
        Node prev = null;
        while (current != null) {
            Node temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }

        return prev;
    }

    public static int length(Node head) {
        Node current = head;
        int length = 0;
        while (current != null) {
            current = current.next;
            length++;
        }
        return length;
    }

    public static Node copyTestData(Node head) {

        Node resultHead = null;
        Node resultPrev = null;
        Node current = head;
        while (current != null) {
            Node newNode = new Node();
            newNode.value = current.value;
            if (resultPrev != null) {
                resultPrev.next = newNode;
            }

            if (resultHead == null) {
                resultHead = newNode;
            }
            current = current.next;
            resultPrev = newNode;
        }

        return resultHead;
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

    public static Node generateTestData(int size, int min, int max) {
        Random random = new Random();
        int[] temp = random.ints(size, min, max).toArray();
        return generateTestData(temp);
    }

}
