package practice.zuosbook.chapter2;

import helper.ListNode;

import static helper.ListUtils.*;

public class DeleteRatio {
    static ListNode solve(ListNode head, int a, int b) {
        if (head == null) return null;

        ListNode pseudoHead = new ListNode();
        pseudoHead.next = head;
        int len = len(head);
        int iToDelete = Math.floorDiv(len * a, b);
        iToDelete = iToDelete == len ? len - 1 : iToDelete;
        ListNode cur = pseudoHead;
        for (int i = 0; i < iToDelete; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return pseudoHead.next;
    }

    public static void main(String[] args) {
        ListNode head = create(new int[]{1, 2, 3, 4, 5});
//        print(solve(head, 1, 5));
        print(solve(head, 2, 5));
//        print(solve(head, 1, 7));
//        print(solve(head, 6, 7));
//        print(solve(head, 7, 7));
    }
}
