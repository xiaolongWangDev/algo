package linkedlist;

import java.util.HashMap;
import java.util.Map;

import static linkedlist.LinkedListUtils.*;

public class ListClone {

    public RandNode cloneUsingMap(RandNode orig) {
        RandNode current = orig;
        Map<RandNode, RandNode> mapping = new HashMap<>();
        while(current != null) {
            RandNode newNode = new RandNode();
            newNode.name = "new_" + current.name;
            newNode.value = current.value;
            mapping.put(current, newNode);
            current = current.next;
        }

        current = orig;
        while(current != null) {
            mapping.get(current).next = mapping.get(current.next);
            mapping.get(current).rand = mapping.get(current.rand);
            current = current.next;
        }

        return mapping.get(orig);
    }

    public RandNode cloneInPlace(RandNode orig) {
        RandNode current = orig;
        while(current != null) {
            RandNode newNode = new RandNode();
            newNode.name = "new_" + current.name;
            newNode.value = current.value;
            newNode.next = current.next;
            current.next = newNode;
            current = current.next.next;
        }

        current = orig;
        while(current != null) {
            current.next.rand = current.rand == null ? null : current.rand.next;
            current = current.next.next;
        }

        RandNode cloneHead = null;
        RandNode cloneTail = null;
        current = orig;
        while(current != null) {
            if(cloneHead == null) {
                cloneHead = current.next;
                cloneTail = cloneHead;
            } else {
                cloneTail.next = current.next;
                cloneTail = cloneTail.next;
            }
            current.next = current.next.next;
            current = current.next;
        }

        return cloneHead;
    }

    public static void main(String[] args) {
        RandNode testData = generateTestDataRandNode(10, 0, 5);
        printRand(testData);
        RandNode clone = new ListClone().cloneInPlace(testData);
        printRand(clone);
        printRand(testData);
    }
}
