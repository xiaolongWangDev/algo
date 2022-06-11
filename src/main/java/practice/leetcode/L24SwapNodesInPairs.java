package practice.leetcode;

import helper.ListNode;

public class L24SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode start = new ListNode();
        start.next = head;
        var cur = start;
        while(cur.next != null && cur.next.next != null) {
            ListNode first = cur.next;
            ListNode second = cur.next.next;

            first.next = second.next;
            second.next = first;
            cur.next = second;

            cur = second;
        }

        return start.next;
    }


}
