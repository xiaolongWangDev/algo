package linkedlist;

import java.util.List;

import static linkedlist.LinkedListUtils.*;

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
