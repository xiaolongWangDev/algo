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

        cur = prev;
        boolean result = true;

        for (int i = 0; i < len / 2; i++) {
            if (head.val != cur.val) {
                result = false;
                break;
            }
            head = head.next;
            cur = cur.next;
        }

        // recover the list
        cur = prev;
        prev = null;
        while(cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }

        return result;
    }

    public static void main(String[] args) {
        ListNode head = ListUtils.create(new int[]{1, 2, 3, 4, 5});
        System.out.println(solve(head));
        ListUtils.print(head);
        head = ListUtils.create(new int[]{1, 2, 2, 1});
        System.out.println(solve(head));
        ListUtils.print(head);
        head = ListUtils.create(new int[]{1, 2, 3, 2, 1});
        System.out.println(solve(head));
        ListUtils.print(head);
        System.out.println(solve(ListUtils.create(new int[]{1, 2, 3, 4, 2, 1})));
    }
}
