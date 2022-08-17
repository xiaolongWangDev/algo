package practice.zuosbook.chapter2;

import helper.ListNode;
import helper.ListUtils;

public class JosephRingOrderNodeRemoval {

    public static ListNode solve(ListNode head, int m) {
        int i = 1;
        ListNode cur = head.next;
        ListNode prev = null;
        while (cur.next != head) {
            cur = cur.next;
        }
        prev = cur;
        cur = cur.next;

        while (cur != cur.next) {
            if (i == m) {
                // remove node
                prev.next = cur.next;
                System.out.println("remove " + cur.val);
                i = 0;
            }

            prev = cur;
            cur = cur.next;
            i++;
        }

        return cur;
    }

    public static void main(String[] args) {
        ListNode head = ListUtils.create(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14});
        ListNode cur = head;
        while(cur.next != null) {
            cur = cur.next;
        }
        cur.next = head;
        System.out.println("survivor " + solve(head, 3).val);
    }
}
