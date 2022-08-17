package practice.zuosbook.chapter2;

import helper.ListNode;
import helper.ListUtils;

public class IsPalindrome {
    public static boolean solve(ListNode head) {
        int len = ListUtils.len(head);
        int toSkip = len % 2 == 0 ? len / 2 : len / 2 + 1;
        ListNode cur = head;
        for (int i = 0; i < toSkip; i++) {
            cur = cur.next;
        }

        ListNode prev = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }

        for (int i = 0; i < len / 2; i++) {
            if (head.val != prev.val) {
                return false;
            }
            head = head.next;
            prev = prev.next;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(solve(ListUtils.create(new int[]{1, 2, 3, 4, 5})));
        System.out.println(solve(ListUtils.create(new int[]{1, 2, 2, 1})));
        System.out.println(solve(ListUtils.create(new int[]{1, 2, 3, 2, 1})));
        System.out.println(solve(ListUtils.create(new int[]{1, 2, 3, 4, 2, 1})));
    }
}
