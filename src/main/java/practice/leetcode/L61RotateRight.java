package practice.leetcode;

import helper.ListNode;

public class L61RotateRight {
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        int l = len(head);
        k = k % l;
        if (k == 0) return head;
        int stop = l - k - 1;
        ListNode cur = head;
        for (int i = 0; i < stop; i++) {
            cur = cur.next;
        }
        ListNode newHead = cur.next;
        cur.next = null;
        cur = newHead;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = head;
        return newHead;
    }

    private static int len(ListNode head) {
        int i = 0;
        while (head != null) {
            head = head.next;
            i++;
        }
        return i;
    }
}
