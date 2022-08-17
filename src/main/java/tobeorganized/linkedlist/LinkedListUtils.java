package tobeorganized.linkedlist;

import java.util.*;

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

    public static List<Node> generateTestDataWithLoop(int[] input, boolean fullLoop) {
        Node head = generateTestData(input);
        return createLoop(head, input.length, fullLoop);
    }

    private static List<Node> createLoop(Node head, int size, boolean fullLoop) {
        Node cur = head;
        Node randomNode = null;
        Node tail = null;
        int randomIndex = fullLoop ? 0 : new Random().nextInt(size);
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
        return List.of(head, randomNode);
    }

    public static Node nodeAt(Node head, int index) {
        Node cur = head;
        while (cur != null && index-- != 0) {
            cur = cur.next;
        }
        return cur;
    }

    public static List<Node> generateTestDataForTheCommonNodeProblem(int firstSize, int secondSize, int commonSize, int min, int max, boolean hasLoop, boolean sameLoopEntry) {
        Random random = new Random();
        Node commonListHead;
        if (commonSize == 0) {
            commonListHead = null;
        } else {
            int[] commonArray = random.ints(commonSize, min, max).toArray();
            commonListHead = hasLoop ? generateTestDataWithLoop(commonArray, !sameLoopEntry).get(0) : generateTestData(commonArray);
        }

        int[] firstDiffArray = random.ints(firstSize - commonSize, min, max).toArray();
        Node firstListHead = createAndConnectList(commonSize, hasLoop, sameLoopEntry, commonListHead, firstDiffArray);

        int[] secondDiffArray = random.ints(secondSize - commonSize, min, max).toArray();
        Node secondListHead = createAndConnectList(commonSize, hasLoop, sameLoopEntry, commonListHead, secondDiffArray);

        List<Node> result = new ArrayList<>();
        result.add(firstListHead);
        result.add(secondListHead);
        result.add(commonListHead);
        return result;
    }

    private static Node createAndConnectList(int commonSize, boolean hasLoop, boolean sameLoopEntry, Node commonListHead, int[] diffArray) {
        Node listHead;
        if (hasLoop && commonSize == 0) {
            listHead = generateTestDataWithLoop(diffArray, false).get(0);
        } else {
            listHead = generateTestData(diffArray);
            Node cur = listHead;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = (hasLoop && !sameLoopEntry) ? randomNodeFromLoop(commonListHead, commonSize) : commonListHead;
        }
        return listHead;
    }

    private static Node randomNodeFromLoop(Node head, int maxIndex) {
        int count = new Random().nextInt(maxIndex + 1);
        Node cur = head;
        while (count-- > 0) {
            cur = cur.next;
        }
        return cur;
    }
}
