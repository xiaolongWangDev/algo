package practice.zuosbook.chapter2;

import helper.ListNode;

import static helper.ListUtils.*;

// Leetcode 25
// definitely easier to understand than my previous submission in L25ReverseNodesInKGroup
public class ReverseEveryK {
    public static ListNode solve(ListNode head, int k) {
        if (k < 2) {
            return head;
        }
        // how long is it
        int len = len(head);
        ListNode pseudoHead = new ListNode();

        // if there are 10 elements (len = 10), k = 3,
        // then the stop index is 9,
        // therefore we will reverse group are 0~2, 3~5, 6~8
        int stopIndex = len / k * k;

        ListNode prev = null;
        ListNode cur = head;
        ListNode previousSectionOldHead = pseudoHead;
        ListNode currentSectionOldHead = null;

        int i = 0;
        while (cur != null && i < stopIndex) {
            // in the beginning of each section, record the first element as currentSectionOldHead
            if (i % k == 0) {
                currentSectionOldHead = cur;
            }
            // at the end of each section, wire the previous section's old head to current
            // then currentSectionOldHead becomes previousSectionOldHead because we are going to the next section
            if (i % k == k - 1) {
                previousSectionOldHead.next = cur;
                previousSectionOldHead = currentSectionOldHead;
            }

            // in each iteration, we'll do normal linklist reversal.
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
            i++;
        }

        // wire the first element after the whole reversal chunk so that the list is connected
        previousSectionOldHead.next = cur;

        return pseudoHead.next;
    }

    public static void main(String[] args) {
        print(solve(create(new int[]{1, 2, 3, 4, 5}), 2));
        print(solve(create(new int[]{1, 2, 3, 4, 5, 6}), 3));
        print(solve(create(new int[]{1, 2, 3, 4, 5, 6, 7, 8}), 3));
        print(solve(create(new int[]{1, 2}), 3));
    }
}
