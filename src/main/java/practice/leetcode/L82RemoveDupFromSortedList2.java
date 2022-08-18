package practice.leetcode;

import helper.ListNode;
import helper.ListUtils;

public class L82RemoveDupFromSortedList2 {
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode pseudoHead = new ListNode();
        ListNode cur = head;
        ListNode prevNumber = pseudoHead;
        int count = 1;
        while (cur != null) {
            if (cur.next == null || cur.val != cur.next.val) {
                if (count == 1) {
                    prevNumber.next = cur;
                    prevNumber = cur;
                }
                count = 0;
            }
            cur = cur.next;
            count++;
        }
        prevNumber.next = null;

        return pseudoHead.next;
    }

    public static void main(String[] args) {
        ListUtils.print(deleteDuplicates(ListUtils.create(new int[]{1, 2, 3, 3, 4, 4, 5})));
//        ListUtils.print(deleteDuplicates(ListUtils.create(new int[]{1, 2, 2})));
    }
}
