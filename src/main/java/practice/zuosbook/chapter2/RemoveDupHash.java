package practice.zuosbook.chapter2;

import helper.ListNode;
import helper.ListUtils;

import java.util.HashSet;
import java.util.Set;

public class RemoveDupHash {
    public static ListNode solve(ListNode head) {
        Set<Integer> met = new HashSet<>();
        ListNode cur = head;
        ListNode prev = null;
        while (cur != null) {
            if (met.contains(cur.val)) {
                prev.next = cur.next;
            } else {
                met.add(cur.val);
                prev = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListUtils.print(solve(ListUtils.create(new int[]{1,2,3,3,4,4,2,2,1,1})));
    }
}
