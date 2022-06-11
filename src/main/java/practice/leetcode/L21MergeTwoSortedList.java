package practice.leetcode;

import helper.ListNode;

public class L21MergeTwoSortedList {
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode start = new ListNode();
        var cur = start;
        var p1 = list1;
        var p2 = list2;

        while (p1 != null || p2 != null) {
            if (p1 != null && p2 != null) {
                if (p1.val < p2.val) {
                    cur.next = p1;
                    p1 = p1.next;
                } else {
                    cur.next = p2;
                    p2 = p2.next;
                }
            } else if (p1 != null) {
                cur.next = p1;
                p1 = p1.next;
            } else {
                cur.next = p2;
                p2 = p2.next;
            }
            cur = cur.next;
        }

        return start.next;

    }
}
