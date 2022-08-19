package practice.zuosbook.chapter2;

import helper.ListNode;
import helper.ListUtils;

public class LeftRightInterleave {
    public static ListNode solve(ListNode head) {
        int len = ListUtils.len(head);
        ListNode left = head;
        ListNode cur = head;
        for (int i = 0; i < len / 2 - 1; i++) {
            cur = cur.next;
        }
        ListNode right = cur.next;
        cur.next = null;

        while (left.next != null) {
            ListNode temp = right.next;
            right.next = left.next;
            left.next = right;
            left = right.next;
            right = temp;
        }

        left.next = right;

        return head;
    }

    public static void main(String[] args) {
//        ListUtils.print(solve(ListUtils.create(new int[]{1, 2, 3, 4, 5})));
        ListUtils.print(solve(ListUtils.create(new int[]{1, 2, 3, 4, 5, 6})));
    }
}
