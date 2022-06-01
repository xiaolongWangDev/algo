package tobeorganized.linkedlist;

import java.util.List;

import static tobeorganized.linkedlist.LinkedListUtils.*;

public class FindLoop {

    public boolean hasLoop(Node input) {
        if(input == null) return false;
        Node slow = input;
        Node fast = input;
        while (true) {
            if (slow.next == null) return false;
            slow = slow.next;
            if (fast.next == null || fast.next.next == null) return false;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
    }

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
    public Node findEnterPoint(Node input) {
        if(input == null) return null;
        Node slow = input;
        Node fast = input;
        Node firstMeetPoint;
        while (true) {
            if (slow.next == null) return null;
            slow = slow.next;
            if (fast.next == null || fast.next.next == null) return null;
            fast = fast.next.next;
            if (slow == fast) {
                firstMeetPoint = slow;
                break;
            }
        }
        Node one = input;
        Node another = firstMeetPoint;
        while(one != another) {
            one = one.next;
            another = another.next;
        }
        return one;
    }

    public static void main(String[] args) {
        int size = 1000000;
        List<Node> testDataBundle = generateTestDataWithLoop(size, 0, 10);
        Node testDataWithLoop = testDataBundle.get(0);
        Node loopIndex = testDataBundle.get(1);
        Node testDataNoLoop = generateTestData(size, 0, 10);
//        printFirstN(testData, size);
        FindLoop algo = new FindLoop();

        assert algo.hasLoop(testDataWithLoop);
        assert !algo.hasLoop(testDataNoLoop);
        assert loopIndex == algo.findEnterPoint(testDataWithLoop);
    }
}
