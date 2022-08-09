package practice.zuosbook.chapter2;

import helper.ListNode;
import helper.ListUtils;

public class PrintCommonPartOfOrderedLists {
    static void solve(ListNode h1, ListNode h2) {
        ListNode cur1 = h1;
        ListNode cur2 = h2;
        while (cur1 != cur2 && cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                cur1 = cur1.next;
            } else {
                cur2 = cur2.next;
            }
        }
        if (cur1 == cur2) {
            while (cur1 != null) {
                System.out.println(cur1.val);
                cur1 = cur1.next;
            }
        }
    }

    public static void main(String[] args) {
        ListNode h1 = ListUtils.create(new int[]{1,3});
        ListNode h2 = ListUtils.create(new int[]{1,2,4});
        ListNode common = ListUtils.create(new int[]{5,6,7});
        h1.next.next = common;
        h2.next.next.next = common;

        solve(h1, h2);
    }
}
