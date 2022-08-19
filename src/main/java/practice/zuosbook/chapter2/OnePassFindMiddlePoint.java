package practice.zuosbook.chapter2;

import helper.ListNode;
import helper.ListUtils;

public class OnePassFindMiddlePoint {
    public static ListNode solve(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // if len is 4
        // fast goes: 1st, 3rd
        // slow goes: 1st, 2nd
        // correct

        // if len is 5
        // fast goes: 1st, 3rd, 5th
        // slow goes: 1st, 2nd, 3rd
        // correct

        return slow;
    }

    public static void main(String[] args) {
        ListUtils.print(solve(ListUtils.create(new int[]{1, 2, 3, 4, 5})));
//        ListUtils.print(solve(ListUtils.create(new int[]{1,2,3,4,})));
    }
}
