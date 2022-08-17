package practice.zuosbook.chapter2;

import java.util.HashMap;
import java.util.Map;

import static practice.zuosbook.chapter2.RandNode.generateTestDataRandNode;
import static practice.zuosbook.chapter2.RandNode.print;

public class CloneList1 {
    public static RandNode solve(RandNode head) {
        Map<RandNode, RandNode> mapping = new HashMap<>();
        RandNode cur = head;
        while(cur != null) {
            RandNode newNode = new RandNode();
            newNode.name = "new_" + cur.name;
            newNode.value = cur.value;
            mapping.put(cur, newNode);
            cur = cur.next;
        }

        cur = head;
        while(cur != null) {
            mapping.get(cur).next = mapping.get(cur.next);
            mapping.get(cur).rand = mapping.get(cur.rand);
            cur = cur.next;
        }

        return mapping.get(head);
    }

    public static void main(String[] args) {
        RandNode head = generateTestDataRandNode(10, 0, 100);
        print(head);
        RandNode cloned = solve(head);
        print(cloned);
    }
}
