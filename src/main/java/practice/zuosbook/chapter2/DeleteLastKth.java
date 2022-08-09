package practice.zuosbook.chapter2;

import helper.DListNode;
import helper.ListNode;

import static helper.ListUtils.*;

public class DeleteLastKth {
    // k is 0 based
    static ListNode solve(ListNode head, int k) {
        ListNode pseudoHead = new ListNode();
        pseudoHead.next = head;
        int len = len(pseudoHead);
        if (k >= len - 1) return null;
        int steps = len - k - 2;
        ListNode cur = pseudoHead;
        for (int i = 0; i < steps; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return pseudoHead.next;
    }

    static DListNode solve(DListNode head, int k) {
        DListNode pseudoHead = new DListNode();
        pseudoHead.next = head;
        int len = len(pseudoHead);
        if (k >= len - 1) return null;
        int steps = len - k - 2;
        DListNode cur = pseudoHead;
        for (int i = 0; i < steps; i++) {
            cur = cur.next;
        }
        if(cur.next.next != null) {
            cur.next.next.prev = cur == pseudoHead ? null : cur;
        }
        cur.next = cur.next.next;
        return pseudoHead.next;
    }

    public static void main(String[] args) {
//        ListNode head = create(new int[]{1,2,3,4,5});
//        print(head);
//        print(solve(head, 0));
//        print(solve(head, 4));

        DListNode dhead = createDList(new int[]{1,2,3,4,5});
        printDList(dhead);
//        printDList(solve(dhead, 0));
        printDList(solve(dhead, 3));
//        printDList(solve(dhead, 4));
    }
}
