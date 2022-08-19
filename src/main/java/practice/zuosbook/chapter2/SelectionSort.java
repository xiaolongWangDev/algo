package practice.zuosbook.chapter2;

import helper.ListNode;
import helper.ListUtils;

public class SelectionSort {
    public static ListNode solve(ListNode head) {
        ListNode sortedPseudoHead = new ListNode();
        ListNode sortedTail = sortedPseudoHead;

        ListNode origPseudoHead = new ListNode();
        origPseudoHead.next = head;

        while (origPseudoHead.next != null) {
            ListNode cur = origPseudoHead.next;
            ListNode minValPrev = null;
            Integer minVal = null;
            ListNode prev = origPseudoHead;

            while (cur != null) {
                if (minVal == null || cur.val < minVal) {
                    minVal = cur.val;
                    minValPrev = prev;
                }
                prev = cur;
                cur = cur.next;
            }

            sortedTail.next = minValPrev.next;
            // minValPrev.next cannot be null because we iterated through that one
            minValPrev.next = minValPrev.next.next;
            sortedTail = sortedTail.next;
            sortedTail.next = null;
        }

        return sortedPseudoHead.next;
    }

    public static void main(String[] args) {
//        ListUtils.print(solve(ListUtils.create(new int[]{2, 1, 4, 5, 3})));
        ListUtils.print(solve(ListUtils.create(new int[]{5, 4, 3, 2, 11, 2, 345, 12, 412, 123, 123, 124151, 5, 4, 3})));
    }
}
