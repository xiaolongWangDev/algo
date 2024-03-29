package helper;

public class ListUtils {
    public static ListNode create(int[] vals) {
        ListNode head = null;
        ListNode cur = new ListNode();
        for (int i = 0; i < vals.length; i++) {
            cur.val = vals[i];
            cur.next = i == vals.length - 1 ? null : new ListNode();
            if (head == null) {
                head = cur;
            }
            cur = cur.next;
        }

        return head;
    }

    public static DListNode createDList(int[] vals) {
        DListNode head = null;
        DListNode prev = null;
        DListNode cur = new DListNode();
        for (int i = 0; i < vals.length; i++) {
            cur.prev = prev;
            cur.val = vals[i];
            cur.next = i == vals.length - 1 ? null : new DListNode();
            if (head == null) {
                head = cur;
            }
            prev = cur;
            cur = cur.next;
        }

        return head;
    }

    public static int len(ListNode head) {
        int res = 0;
        while (head != null) {
            head = head.next;
            res++;
        }
        return res;
    }

    public static int lenLoop(ListNode head) {
        int res = 1;
        ListNode cur = head.next;
        while (cur != head) {
            cur = cur.next;
            res++;
        }
        return res;
    }

    public static int len(DListNode head) {
        int res = 0;
        while (head != null) {
            head = head.next;
            res++;
        }
        return res;
    }

    public static void print(ListNode head) {
        int count = 0;
        while (head != null) {
            System.out.printf("%d: %2d%n", count++, head.val);
            head = head.next;
        }
    }

    public static void printDList(DListNode head) {
        int count = 0;
        while (head != null) {
            System.out.printf("%d: va: %2d, prev val: %2d%n", count++, head.val, head.prev == null ? -1 : head.prev.val);
            head = head.next;
        }
    }

    public static void printFirstN(ListNode head, int n) {
        ListNode current = head;
        int count = 0;
        while (current != null && count < n) {
            System.out.printf("(v: %d, c: %s, n: %s)", current.val, current, current.next == null ? null : current.next);
            System.out.println();
            current = current.next;
            count++;
        }
    }

    public static ListNode nodeAt(ListNode head, int index) {
        ListNode cur = head;
        while (cur != null && index-- != 0) {
            cur = cur.next;
        }
        return cur;
    }
}
