package practice.zuosbook.chapter2;

import helper.ListNode;
import helper.ListUtils;

public class HasLoop {

    /**
     * understanding Floyd's algorithm mathematically
     * let L be loop length
     *     M be the distance from head to entry point
     *     N be the distance between entry point and the point of the 2 cursors first meet
     *     q be the number of full rounds slow cursor travelled before the first meet
     *     p be the number of full rounds fast cursor travelled before the first meet
     * So,
     * full distance traveled by the fast cursor is: M + pL + N
     * full distance traveled by the slow cursor is: M + qL + N
     * because they used the same amount of time, and fast cursor is velocity is 2 times of the slow
     * M + pL + N = 2(M + qL + N)
     * ->
     * (p - 2q) L = M + N            (expression 1)
     *
     * let's now abstract the 2nd part of the algorithm - put 1 cursor back the head, and then they both
     * start walking at the velocity of 1 (let's call them cursor A and B)
     * When A has again travelled M, B has also travelled M in the loop.
     * Remember, B is already N steps away from the loop entry point. So now its distance from the entry point is N + M.
     * Look at expression 1, N + M equals integer multiples of the length of a full loop.
     * This means that cursor B is now right on the entry point.
     * If you remember, cursor A has travelled M, which is the distance from head to the entry point, so it is also
     * right on the entry point.
     * Therefore, cursor A and B will meet at the entry point of the loop!
     *
     */
    public static ListNode solve(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while (slow != null && fast != null) {
            if (slow == fast) {
                fast = head;
                while(fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
            slow = slow.next;
            fast = fast.next == null ? null : fast.next.next;
        }

        return null;
    }

    public static void main(String[] args) {
        ListNode loopfree = ListUtils.create(new int[]{1, 2, 3, 4, 5});
        System.out.println(solve(loopfree));
        ListNode withLoop = ListUtils.create(new int[]{1, 2, 3, 4, 5});
        ListNode cur = withLoop;
        while (cur.next != null) {
            cur = cur.next;
        }
        // set tail's next to the 3rd node, now it has a loop
        cur.next = withLoop.next.next;
        System.out.println(solve(withLoop).val);

    }
}
