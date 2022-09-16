package practice.leetcode;

import helper.ListNode;
import helper.ListUtils;

public class L83RemoveDuplicatesFromSortedList {
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while(cur != null) {
            if(prev != null && prev.val == cur.val) {
                prev.next = cur.next;
            } else {
                prev = cur;
            }
            cur= cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode data = ListUtils.create(new int[]{1,1,2});
        ListUtils.print(deleteDuplicates(data));
    }
}
