package practice.leetcode;

import helper.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class L23MergeKSortedLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        int step = 1;
        while (step < lists.length) {
            for (int i = 0; i < lists.length; i += 2 * step) {
                if (i + step < lists.length) {
                    lists[i] = L21MergeTwoSortedList.mergeTwoLists(lists[i], lists[i + step]);
                }
            }
            step *= 2;
        }
        return lists[0];
    }

    public static ListNode mergeKListsUsingHeap(ListNode[] lists) {
        ListNode start = new ListNode();
        ListNode cur = start;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(Comparator.comparing(o -> o.val));
        for (var list : lists) {
            if (list != null) {
                minHeap.add(list);
            }
        }
        while (!minHeap.isEmpty()) {
            cur.next = minHeap.poll();
            cur = cur.next;
            if (cur.next != null) {
                minHeap.add(cur.next);
            }
        }

        return start.next;
    }

    public static void main(String[] args) {

    }
}
