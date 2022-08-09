package practice.zuosbook.chapter2;

import helper.ListNode;

import static helper.ListUtils.*;

public class DeleteMid {
    static ListNode solve(ListNode head) {
        if(head == null) return null;
        ListNode pseudoHead = new ListNode();
        pseudoHead.next = head;
        int len = len(head);
        int iToDelete = (len - 1) / 2; // 1: 0, 2: 0, 3: 1, 4: 1
        ListNode cur = pseudoHead;
        for(int i = 0; i < iToDelete; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return pseudoHead.next;
    }

    public static void main(String[] args) {
        ListNode head= create(new int[]{1,2,3,4,5});
        print(solve(head));
        ListNode head2= create(new int[]{1,2,3,4});
        print(solve(head2));
        ListNode head3= create(new int[]{1,2});
        print(solve(head3));
        ListNode head4= create(new int[]{1});
        print(solve(head4));
        ListNode head5= create(new int[]{});
        print(solve(head5));
    }
}
