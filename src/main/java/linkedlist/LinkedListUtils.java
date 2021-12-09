package linkedlist;

import java.util.*;

public class LinkedListUtils {

    public static class Node {
        int value;
        Node next;
    }

    public static class RandNode {
        String name;
        int value;
        RandNode next;
        RandNode rand;
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

    public static void printFirstN(Node head, int n) {
        Node current = head;
        int count = 0;
        while (current != null && count < n) {
            System.out.printf("(v: %d, c: %s, n: %s)", current.value, current, current.next == null ? null : current.next);
            System.out.println();
            current = current.next;
            count++;
        }
    }

    public static void printRand(RandNode head) {
        RandNode current = head;
        while (current != null) {
            System.out.printf("(v: %d, r: %s)", current.value, current.rand == null ? null : current.rand.name);
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

    public static List<Object> generateTestDataWithLoop(int size, int min, int max) {
        Node head = generateTestData(size, min, max);
        Node cur = head;
        Node randomNode = null;
        Node tail = null;
        Random random = new Random();
        int randomIndex = random.nextInt(size);
        for (int i = 0; i < size; i++) {
            if (i == randomIndex) {
                randomNode = cur;
            }
            if (i == size - 1) {
                tail = cur;
            }
            cur = cur.next;
        }
        assert tail != null;
        tail.next = randomNode;
        return List.of(head, randomIndex);
    }

    public static RandNode generateTestDataRandNode(int size, int min, int max) {
        Random random = new Random();
        int[] temp = random.ints(size, min, max).toArray();
        List<RandNode> allNodes = new ArrayList<>();
        for (int i = 0; i < temp.length; i++) {
            RandNode newNode = new RandNode();
            newNode.name = "n_" + (i + 1);
            newNode.value = temp[i];
            allNodes.add(newNode);
        }
        for (int i = 0; i < allNodes.size() - 1; i++) {
            allNodes.get(i).next = allNodes.get(i + 1);
            int index = random.nextInt(allNodes.size() + 1);
            allNodes.get(i).rand = index == allNodes.size() ? null : allNodes.get(index);
        }
        return allNodes.get(0);
    }

}
