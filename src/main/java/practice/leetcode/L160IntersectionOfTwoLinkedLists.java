package practice.leetcode;

public class L160IntersectionOfTwoLinkedLists {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    private static int len(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = len(headA);
        int lenB = len(headB);
        ListNode longer = lenA > lenB ? headA : headB;
        ListNode shorter = lenA > lenB ? headB : headA;
        int headStart = Math.abs(lenA - lenB);
        while (headStart > 0) {
            longer = longer.next;
            headStart--;
        }
        while (longer != null) {
            if (longer == shorter) {
                return longer;
            }
            longer = longer.next;
            shorter = shorter.next;
        }

        return null;
    }

    public static ListNode specialSauce(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;

        // this first part of the iteration makes up for the delta of their length
        // so the second part are aligned.
        //
        // it's a nerdy way to calculate the length difference and give the longer
        // list a head start.
        // fewer lines of code, but harder to understand. with no improvement in performance
        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }

        return p1;
    }

}
