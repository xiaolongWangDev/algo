package practice.leetcode;

public class L19RemoveNthNodeFromEndOfList {
    public class ListNode {
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

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode host = new ListNode();
        host.next = head;
        if (head == null) return null;
        var fast = host;
        var slow = host;
        int count = 0;
        // give fast a head start
        while (count < n) {
            fast = fast.next;
            count++;
        }

        // move fast and slow at the same speed toward the end, stop before fast hits end
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return host.next;
    }

    public ListNode removeNthFromEndNaive(ListNode head, int n) {
        if (head == null) return null;

        var cur = head;
        int count = 0;
        while (cur != null) {
            cur = cur.next;
            count++;
        }

        int iToRemove = count - n;
        ListNode prev = null;
        cur = head;
        count = 0;
        while (count < iToRemove) {
            prev = cur;
            cur = cur.next;
            count++;
        }
        if (prev == null) {
            return head.next;
        } else {
            prev.next = cur.next;
        }

        return head;
    }
}
