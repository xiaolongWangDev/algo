package tobeorganized.linkedlist;

import java.util.List;

public class FindCommonNode {

    public LinkedListUtils.Node find(LinkedListUtils.Node first, LinkedListUtils.Node second) {
        LinkedListUtils.Node firstLoopEntry = new FindLoop().findEnterPoint(first);
        LinkedListUtils.Node secondLoopEntry = new FindLoop().findEnterPoint(second);
        if ((firstLoopEntry == null && secondLoopEntry != null) || (firstLoopEntry != null && secondLoopEntry == null)) {
            return null;
        } else {
            if (firstLoopEntry == null /* && secondLoopEntry == null // not necessary */) {
                return findCommonStraight(first, second, null);
            } else {
                if (firstLoopEntry == secondLoopEntry) {
                    return findCommonStraight(first, second, firstLoopEntry);
                } else {
                    LinkedListUtils.Node cur = firstLoopEntry.next;
                    while (cur != firstLoopEntry) {
                        if (cur == secondLoopEntry) {
                            return firstLoopEntry; // or secondLoopEntry. they are both answers
                        }
                        cur = cur.next;
                    }
                    return null; // secondLoopEntry is not in first's loop. so there's nothing in common
                }
            }
        }
    }

    private LinkedListUtils.Node findCommonStraight(LinkedListUtils.Node first, LinkedListUtils.Node second, LinkedListUtils.Node end) {
        int firstLen = 0;
        LinkedListUtils.Node firstCursor = first;
        while (firstCursor.next != end) {
            firstLen++;
            firstCursor = firstCursor.next;
        }
        firstLen++;
        int secondLen = 0;
        LinkedListUtils.Node secondCursor = second;
        while (secondCursor.next != end) {
            secondLen++;
            secondCursor = secondCursor.next;
        }
        secondLen++;
        if (end == null && firstCursor != secondCursor) {
            return null;
        } else {
            firstCursor = first;
            secondCursor = second;
            if (firstLen > secondLen) {
                for (int i = 0; i < firstLen - secondLen; i++) {
                    firstCursor = firstCursor.next;
                }
            } else {
                for (int i = 0; i < secondLen - firstLen; i++) {
                    secondCursor = secondCursor.next;
                }
            }
            while (firstCursor != null && secondCursor != null) {
                if (firstCursor == secondCursor) {
                    return firstCursor;
                }
                firstCursor = firstCursor.next;
                secondCursor = secondCursor.next;
            }
            return null;
        }
    }

    public static void main(String[] args) {

        // my awesome test data
        int firstSize = 100;
        int secondSize = 50;
        int commonSize = 30;
        // no loop, no common
        List<LinkedListUtils.Node> testDataBundle1 = LinkedListUtils.generateTestDataForTheCommonNodeProblem(firstSize, secondSize, 0, 0, 100, false, false);
        assert new FindCommonNode().find(testDataBundle1.get(0), testDataBundle1.get(1)) == null;
        // has loop, no common
        List<LinkedListUtils.Node> testDataBundle2 = LinkedListUtils.generateTestDataForTheCommonNodeProblem(firstSize, secondSize, 0, 0, 100, true, false);
        assert new FindCommonNode().find(testDataBundle2.get(0), testDataBundle2.get(1)) == null;
        // no loop, has common
        List<LinkedListUtils.Node> testDataBundle3 = LinkedListUtils.generateTestDataForTheCommonNodeProblem(firstSize, secondSize, commonSize, 0, 100, false, false);
        assert new FindCommonNode().find(testDataBundle3.get(0), testDataBundle3.get(1)) == testDataBundle3.get(2);
        // has loop, has common, common entry point
        List<LinkedListUtils.Node> testDataBundle4 = LinkedListUtils.generateTestDataForTheCommonNodeProblem(firstSize, secondSize, commonSize, 0, 100, true, true);
        assert new FindCommonNode().find(testDataBundle4.get(0), testDataBundle4.get(1)) == testDataBundle4.get(2);
        // has loop, has common, different entry point
        List<LinkedListUtils.Node> testDataBundle5 = LinkedListUtils.generateTestDataForTheCommonNodeProblem(firstSize, secondSize, commonSize, 0, 100, true, false);
        assert new FindCommonNode().find(testDataBundle5.get(0), testDataBundle5.get(1)) == LinkedListUtils.nodeAt(testDataBundle5.get(0), firstSize - commonSize);

    }

    private static void printTrio(List<LinkedListUtils.Node> testDataBundle, int firstSize, int secondSize, int commonSize) {
        System.out.println("");
        System.out.println("");
        System.out.println("___________");
        LinkedListUtils.printFirstN(testDataBundle.get(0), firstSize);
        System.out.println("___________");
        LinkedListUtils.printFirstN(testDataBundle.get(1), secondSize);
        System.out.println("___________");
        LinkedListUtils.printFirstN(testDataBundle.get(2), commonSize);
    }
}
