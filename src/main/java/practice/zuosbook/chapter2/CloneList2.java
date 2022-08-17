package practice.zuosbook.chapter2;

import java.util.HashMap;
import java.util.Map;

import static practice.zuosbook.chapter2.RandNode.generateTestDataRandNode;
import static practice.zuosbook.chapter2.RandNode.print;

public class CloneList2 {
    public static RandNode solve(RandNode head) {
        RandNode cur = head;
        while (cur != null) {
            RandNode newNode = new RandNode();
            newNode.name = "new_" + cur.name;
            newNode.value = cur.value;
            newNode.next = cur.next;
            cur.next = newNode;
            cur = newNode.next;
        }

        int i = 0;
        cur = head;
        while (cur != null) {
            if (i % 2 == 0) {
                cur.next.rand = cur.rand == null ? null : cur.rand.next;
            }
            cur = cur.next;
            i++;
        }

        cur = head;
        RandNode clonedHead = null;
        RandNode clonedCur = null;
        while (cur != null) {
            if (clonedHead == null) {
                clonedHead = cur.next;
                clonedCur = clonedHead;
            } else {
                clonedCur.next = cur.next;
                clonedCur = clonedCur.next;
            }
            cur.next = cur.next.next;
            cur = cur.next;
        }

        return clonedHead;
    }

    public static void main(String[] args) {
        RandNode head = generateTestDataRandNode(10, 0, 100);
        print(head);
        RandNode cloned = solve(head);
        print(cloned);
        print(head);
    }
}
