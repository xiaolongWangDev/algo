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

    public static List<Node> generateTestDataWithLoop(int[] input, boolean fullLoop) {
        Node head = generateTestData(input);
        return createLoop(head, input.length, fullLoop);
    }

    public static List<Node> generateTestDataWithLoop(int size, int min, int max) {
        Node head = generateTestData(size, min, max);
        return createLoop(head, size, false);
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
