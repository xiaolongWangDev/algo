package practice.zuosbook.chapter2;

import tobeorganized.linkedlist.LinkedListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandNode {
    public String name;
    public int value;
    public RandNode next;
    public RandNode rand;

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

    public static void print(RandNode head) {
        RandNode current = head;
        while (current != null) {
            System.out.printf("%s: %d, r: %s", current.name, current.value, current.rand == null ? null : current.rand.name);
            System.out.println();
            current = current.next;
        }
    }
}
