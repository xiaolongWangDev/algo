package practice.leetcode;

import helper.ListNode;
import helper.ListUtils;

public class L234PalindromeLinkedList {
    public static boolean isPalindrome(ListNode head) {
        ListNode cur = head;
        ListNode middle = getMiddle(head);
        while (cur != middle) {
            cur = cur.next;
        }
        ListNode prev = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }

        cur = head;
        while (cur != middle) {
            if (cur.val != prev.val) return false;
            cur = cur.next;
            prev = prev.next;
        }
        return cur.val == prev.val;
    }

    private static ListNode getMiddle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = ListUtils.create(new int[]{1, 2, 3, 4, 5});
        System.out.println(isPalindrome(head));
        ListUtils.print(head);
        head = ListUtils.create(new int[]{1, 2, 2, 1});
        System.out.println(isPalindrome(head));
        ListUtils.print(head);
        head = ListUtils.create(new int[]{1, 2, 3, 2, 1});
        System.out.println(isPalindrome(head));
        ListUtils.print(head);
        System.out.println(isPalindrome(ListUtils.create(new int[]{1, 2, 3, 4, 2, 1})));
    }
}
