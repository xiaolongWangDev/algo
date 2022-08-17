package practice.zuosbook.chapter2;

import helper.ListNode;
import helper.ListUtils;

public class ListPartition {
    public static ListNode solve(ListNode head, int ref) {
        ListNode leftHead = new ListNode();
        ListNode leftTail = leftHead;
        ListNode middleHead = new ListNode();
        ListNode middleTail = middleHead;
        ListNode rightHead = new ListNode();
        ListNode rightTail = rightHead;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < ref) {
                leftTail.next = cur;
                leftTail = cur;
            } else if (cur.val == ref) {
                middleTail.next = cur;
                middleTail = cur;
            } else {
                rightTail.next = cur;
                rightTail = cur;
            }
            cur = cur.next;
        }

        leftTail.next = null;
        middleTail.next = null;
        rightTail.next = null;

        if (leftHead != leftTail && middleHead != middleTail && rightHead != rightTail) {
            leftTail.next = middleHead.next;
            middleTail.next = rightHead.next;
            return leftHead.next;
        } else if (leftHead != leftTail && middleHead != middleTail) {
            leftTail.next = middleHead.next;
            return leftHead.next;
        } else if (leftHead != leftTail && rightHead != rightTail) {
            leftTail.next = rightHead.next;
            return leftHead.next;
        } else if (middleHead != middleTail && rightHead != rightTail) {
            middleTail.next = rightHead.next;
            return middleHead.next;
        } else if (leftHead != leftTail) {
            return leftHead.next;
        } else if (middleHead != middleTail) {
            return middleHead.next;
        } else {
            return rightHead.next;
        }
    }

    public static void main(String[] args) {
        ListUtils.print(solve(ListUtils.create(new int[]{9, 0, 4, 5, 1}), 3));
        ListUtils.print(solve(ListUtils.create(new int[]{9, 0, 3, 4, 5, 2, 3, 1}), 3));
    }
}
