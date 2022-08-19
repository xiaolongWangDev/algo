package practice.zuosbook.chapter2;

import helper.ListNode;
import helper.ListUtils;

public class InsertIntoSortedLoop {
    public static ListNode solve(ListNode head, int num) {
        ListNode cur = head;
        while (cur.next != head) {
            cur = cur.next;
        }
        ListNode prev = cur;
        cur = head;

        // case that the head is changed
        if (head.val > num) {
            ListNode newNode = new ListNode();
            newNode.val = num;
            newNode.next = cur;
            prev.next = newNode;
            return newNode;
        }

        prev = prev.next;
        cur = cur.next;
        while (cur != head && cur.val <= num) {
            prev = cur;
            cur = cur.next;
        }

        ListNode newNode = new ListNode();
        newNode.val = num;
        newNode.next = cur;
        prev.next = newNode;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListUtils.create(new int[]{1, 2, 2, 3, 5, 6, 7});
        head.next.next.next.next.next.next.next = head;

        ListUtils.printFirstN(solve(head, -1), 8);
//        ListUtils.printFirstN(solve(head, 4), 8);
//        ListUtils.printFirstN(solve(head, 11), 8);
    }
}
