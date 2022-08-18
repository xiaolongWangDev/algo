package practice.zuosbook.chapter2;

import helper.ListNode;
import helper.ListUtils;

public class RemoveCertainValue {
    public static ListNode solve(ListNode head, int val) {
        ListNode pseudoHead = new ListNode();
        pseudoHead.next = head;
        ListNode cur = head;
        ListNode prev = pseudoHead;

        while (cur != null) {
            if(cur.val == val) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur = cur.next;
        }
        return pseudoHead.next;
    }

    public static void main(String[] args) {
        ListUtils.print(solve(ListUtils.create(new int[]{1, 2, 3, 3, 4, 4, 2, 2, 1, 1}), 2));
        ListUtils.print(solve(ListUtils.create(new int[]{1, 2, 3, 3, 4, 4, 2, 2, 1, 1}), 1));
    }
}
