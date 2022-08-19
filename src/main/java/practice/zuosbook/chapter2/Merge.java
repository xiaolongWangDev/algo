package practice.zuosbook.chapter2;

import helper.ListNode;
import helper.ListUtils;

public class Merge {
    public static ListNode solve(ListNode aHead, ListNode bHead) {
        ListNode pseudoHead = new ListNode();
        ListNode pseudoTail = pseudoHead;
        while (aHead != null || bHead != null) {
            if (aHead == null) {
                pseudoTail.next = bHead;
                break;
            } else if (bHead == null) {
                pseudoTail.next = aHead;
                break;
            } else {
                if (aHead.val < bHead.val) {
                    pseudoTail.next = aHead;
                    aHead = aHead.next;
                } else {
                    pseudoTail.next = bHead;
                    bHead = bHead.next;
                }
                pseudoTail = pseudoTail.next;
            }
        }
        return pseudoHead.next;
    }

    public static void main(String[] args) {
        ListNode headA = ListUtils.create(new int[]{0, 2, 3, 7});
        ListNode headB = ListUtils.create(new int[]{1, 3, 5, 7, 9});
        ListUtils.print(solve(headA, headB));
    }
}
