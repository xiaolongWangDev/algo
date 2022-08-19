package practice.zuosbook.chapter2;

import helper.ListNode;
import helper.ListUtils;

public class InsertIntoSortedLoop {
    public static ListNode solve(ListNode head, int num) {

        ListNode prev = head;
        ListNode cur = head.next;

        while (cur != head) {
            if (prev.val < num && num <= cur.val) {
                break;
            }
            prev = cur;
            cur = cur.next;
        }

        ListNode newNode = new ListNode();
        newNode.val = num;
        newNode.next = cur;
        prev.next = newNode;
        return head.val > num ? newNode : head;
    }

    public static void main(String[] args) {
        ListNode head = ListUtils.create(new int[]{1, 2, 2, 3, 5, 6, 7});
        head.next.next.next.next.next.next.next = head;

//        ListUtils.printFirstN(solve(head, -1), 8);
//        ListUtils.printFirstN(solve(head, 4), 8);
        ListUtils.printFirstN(solve(head, 5), 8);
//        ListUtils.printFirstN(solve(head, 11), 8);
    }
}
