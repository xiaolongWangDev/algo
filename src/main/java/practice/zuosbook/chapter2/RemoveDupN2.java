package practice.zuosbook.chapter2;

import helper.ListNode;
import helper.ListUtils;

import java.util.HashSet;
import java.util.Set;

public class RemoveDupN2 {
    public static ListNode solve(ListNode head) {
        ListNode cur = head;

        while (cur != null) {
            ListNode check = cur.next;
            ListNode prev = cur;
            while (check != null) {
                if (check.val == cur.val) {
                    prev.next = check.next;
                } else {
                    prev = check;
                }
                check = check.next;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListUtils.print(solve(ListUtils.create(new int[]{1, 2, 3, 3, 4, 4, 2, 2, 1, 1})));
    }
}
