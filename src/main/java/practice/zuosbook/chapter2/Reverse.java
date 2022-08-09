package practice.zuosbook.chapter2;

import helper.DListNode;
import helper.ListNode;

import static helper.ListUtils.*;

public class Reverse {
    static ListNode solve(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }

    static DListNode solve(DListNode head) {
        DListNode prev = null;
        DListNode cur = head;
        while (cur != null) {
            cur.prev = cur.next;
            cur.next = prev;
            prev = cur;
            cur = cur.prev;
        }
        return prev;
    }

    public static void main(String[] args) {
        print(solve(create(new int[]{1, 2, 3, 4, 5})));
        print(solve(create(new int[]{})));
        printDList(solve(createDList(new int[]{1, 2, 3, 4, 5})));
        printDList(solve(createDList(new int[]{})));
    }
}
