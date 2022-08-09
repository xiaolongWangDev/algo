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
}
