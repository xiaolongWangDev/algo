package practice.leetcode;

import helper.ListNode;

public class L25ReverseNodesInKGroup {
    public static ListNode reverseKGroup(ListNode head, int k) {
        // 3
        // v 0 1 2 3 4 5 6
        // v 2 1 0 5 4 3 6
        ListNode host = new ListNode();
        int len = getLen(head);
        host.next = head;
        var prevSectionEnd = host;
        var prev = host;
        var cur = head;
        while (len >= k) {
            int count = 0;
            ListNode newSectionEnd = null;
            while (count < k) {
                var going = cur.next;
                if (count == 0) {
                    newSectionEnd = cur;
                    newSectionEnd.next = null;
                } else {
                    cur.next = prev;
                }
                prev = cur;
                cur = going;
                count++;
            }
            prevSectionEnd.next = prev;
            prevSectionEnd = newSectionEnd;
            len -= k;
        }
        if (len > 0) {
            prevSectionEnd.next = cur;
        }
        return host.next;
    }

    public static ListNode reverseKGroupRec(ListNode head, int k) {
        var cur = head;
        int count = 0;
        // see if there's enough elements
        while (cur != null && count < k) {
            cur = cur.next;
            count++;
        }
        // if not, do nothing
        if (count < k) {
            return head;
        } else {
            cur = head;
            ListNode prev = null;
            count = 0;

            // reverse the list
            while(count < k) {
                var temp = cur.next;
                cur.next = prev;
                prev = cur;
                cur = temp;
                count ++;
            }
            // head id now the tail
            // cur is the head of remaining list
            head.next = reverseKGroupRec(cur, k);
            // prev is the new head
            return prev;
        }
    }


    private static int getLen(ListNode head) {
        int c = 0;
        while (head != null) {
            head = head.next;
            c++;
        }
        return c;
    }


//    private static ListNode[] reverse(ListNode head, int k) {
//        if (k == 1) return new ListNode[]{head, head};
//        if (head == null) {
//            return null;
//        }
//
//        var headAndTail = reverse(head.next, k - 1);
//        if (headAndTail == null) {
//            return new ListNode[]{head, head};
//        }
//        head.next = null;
//        headAndTail[1].next = head;
//        return new ListNode[]{headAndTail[0], head};
//    }

    public static void main(String[] args) {
        var n1 = new ListNode(1);
        var n2 = new ListNode(2);
        var n3 = new ListNode(3);
        var n4 = new ListNode(4);
        var n5 = new ListNode(5);
        var n6 = new ListNode(6);
        var n7 = new ListNode(7);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        var cur = reverseKGroupRec(n1, 3);
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}


