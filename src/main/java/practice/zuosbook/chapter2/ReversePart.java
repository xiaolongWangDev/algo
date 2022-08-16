package practice.zuosbook.chapter2;

import helper.ListNode;

import static helper.ListUtils.*;

public class ReversePart {
    public static ListNode solve(ListNode head, int from, int to) {
        int len = len(head);
        if (from < 0 || from > len - 1 || to < 0 || to > len - 1 || from > to) {
            throw new IllegalArgumentException("invalid from or to");
        }
        ListNode pseudoHead = new ListNode();
        pseudoHead.next = head;
        int i = -1;
        ListNode outerLeft = null;
        ListNode innerTail = null;
        ListNode prev = null;
        ListNode cur = pseudoHead;

        while(cur != null && i <= to) {
            if(i >= from) {
                if(i == from) {
                    outerLeft = prev;
                    innerTail = cur;
                }
                ListNode temp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = temp;
            } else {
                prev = cur;
                cur = cur.next;
            }
            i++;
        }

        outerLeft.next = prev;
        innerTail.next = cur;

        return pseudoHead.next;
    }

    public static void main(String[] args) {
        print(solve(create(new int[]{1,2,3,4,5}), 1, 3));
        print(solve(create(new int[]{1,2,3,4,5}), 0, 4));
        print(solve(create(new int[]{1,2,3,4,5}), 3, 3));
    }
}
