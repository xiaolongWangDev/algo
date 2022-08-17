package practice.zuosbook.chapter2;

import helper.ListNode;
import helper.ListUtils;

public class JosephRingOrderNodeRemoval2 {

    public static ListNode solve(ListNode head, int m) {
        int len = ListUtils.lenLoop(head);
        int survivorIndex = survivor(1, m, len);
        for (int i = 0; i < survivorIndex; i++) {
            head = head.next;
        }
        head.next = head;
        return head;
    }

    /**
     * @param round    which round of the game it is. 1 based
     * @param m        how many every people to kill. 1 based
     * @param N_PEOPLE constant, the total number of people in the beginning
     * @return the relative index of the person in the round
     */
    public static int survivor(int round, int m, int N_PEOPLE) {
        int remaining = N_PEOPLE - round + 1;
        if (remaining == 1) {
            return 0;
        }
        int nextRoundSurvivorIndex = survivor(round + 1, m, N_PEOPLE);
        int indexOfThisRoundKilled = (m - 1) % remaining;
        return indexShift(indexOfThisRoundKilled + 1, nextRoundSurvivorIndex, remaining);
    }

    /**
     * The most valuable part of this algorithm. It's a coordinate transformation.
     * The next round's relative index is X.
     * We know it's origin is the point after the "killed" this round. Say O
     * So the transformed index is X + O,
     * finally, we need to take into account the loop, so we need to get the modulo.
     */
    private static int indexShift(int indexOfTheOrigin, int offset, int size) {
        return (indexOfTheOrigin + offset) % size;
    }

    public static void main(String[] args) {
//        System.out.println(survivor(1, 3, 14));
        ListNode head = ListUtils.create(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13});
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = head;
        System.out.println("survivor " + solve(head, 3).val);
    }
}
