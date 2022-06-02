package practice.leetcode;

public class L2TwoNumbers {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        var p1 = l1;
        var p2 = l2;

        ListNode res = null;
        ListNode prev = null;
        int inc = 0;

        //  || inc != 0 is easily missed. we need it when there's a new highest bit
        while (p1 != null || p2 != null || inc != 0) {
            int sum = inc;
            if (p1 != null) {
                sum = p1.val;
                p1 = p1.next;
            }
            if (p2 != null) {
                sum = p2.val;
                p2 = p2.next;
            }
            inc = sum / 10;
            var cur = new ListNode(sum % 10, null);
            if (prev != null) {
                prev.next = cur;
            }
            if(res == null) {
                res = cur;
            }
            prev = cur;
        }

        return res;
    }
}
