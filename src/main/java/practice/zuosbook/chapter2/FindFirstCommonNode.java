package practice.zuosbook.chapter2;

import helper.ListNode;
import helper.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FindFirstCommonNode {
    public static ListNode solve(ListNode aHead, ListNode bHead) {
        ListNode aLoopEnter = HasLoop.solve(aHead);
        ListNode bLoopEnter = HasLoop.solve(bHead);

        // if they 2 straight lines check
        if (aLoopEnter == null && bLoopEnter == null) return findStraight(aHead, bHead, null);

        // if one of them is a loop and the other is straight, they won't join
        if (aLoopEnter == null || bLoopEnter == null) return null;

        // at this point, we have 2 loops
        // if they share an entry point, the first common is on the straight section
        if (aLoopEnter == bLoopEnter) return findStraight(aHead, bHead, aLoopEnter);

        // when they enter the loop at different nodes,
        // we want to first check if the loop is the same at all
        if (isSameLoop(aLoopEnter, bLoopEnter)) {
            // there's no clear definition of the first common node
            // return the A entry point in this case
            return aLoopEnter;
        } else {
            return null;
        }
    }

    public static ListNode findStraight(ListNode aHead, ListNode bHead, ListNode end) {
        int lenAOutsideLoop = 0;
        ListNode cur = aHead;
        while (cur != end) {
            lenAOutsideLoop++;
            cur = cur.next;
        }
        int lenBOutsideLoop = 0;
        cur = bHead;
        while (cur != end) {
            lenBOutsideLoop++;
            cur = cur.next;
        }

        ListNode longer = lenAOutsideLoop > lenBOutsideLoop ? aHead : bHead;
        ListNode shorter = longer == aHead ? bHead : aHead;

        // give the longer one a head start
        for (int i = 0; i < Math.abs(lenAOutsideLoop - lenBOutsideLoop); i++) {
            longer = longer.next;
        }

        while (shorter != longer) {
            shorter = shorter.next;
            longer = longer.next;
        }

        return shorter;
    }

    public static boolean isSameLoop(ListNode aLoopEnter, ListNode bLoopEnter) {
        ListNode cur = aLoopEnter;
        do {
            if (cur == bLoopEnter) return true;
            cur = cur.next;
        } while (cur != aLoopEnter);
        return false;
    }

    public static void main(String[] args) {
        int firstSize = 10;
        int secondSize = 5;
        int commonSize = 3;
        // no loop, no common
        List<ListNode> testDataBundle1 = generateTestDataForTheCommonNodeProblem(firstSize, secondSize, 0, 0, 100, false, false);
        assert solve(testDataBundle1.get(0), testDataBundle1.get(1)) == null;
        // has loop, no common
        List<ListNode> testDataBundle2 = generateTestDataForTheCommonNodeProblem(firstSize, secondSize, 0, 0, 100, true, false);
        assert solve(testDataBundle2.get(0), testDataBundle2.get(1)) == null;
        // no loop, has common
        List<ListNode> testDataBundle3 = generateTestDataForTheCommonNodeProblem(firstSize, secondSize, commonSize, 0, 100, false, false);
        assert solve(testDataBundle3.get(0), testDataBundle3.get(1)) == testDataBundle3.get(2);
        // has loop, has common, common entry point
        List<ListNode> testDataBundle4 = generateTestDataForTheCommonNodeProblem(firstSize, secondSize, commonSize, 0, 100, true, true);
        assert solve(testDataBundle4.get(0), testDataBundle4.get(1)) == testDataBundle4.get(2);
        // has loop, has common, different entry point
        List<ListNode> testDataBundle5 = generateTestDataForTheCommonNodeProblem(firstSize, secondSize, commonSize, 0, 100, true, false);
        assert solve(testDataBundle5.get(0), testDataBundle5.get(1)) == ListUtils.nodeAt(testDataBundle5.get(0), firstSize - commonSize);

        // this helps you visualize the test data
        printTrio(testDataBundle4, firstSize, secondSize, commonSize);
    }

    // test helpers
    public static List<ListNode> generateTestDataForTheCommonNodeProblem(int firstSize, int secondSize, int commonSize, int min, int max, boolean hasLoop, boolean sameLoopEntry) {
        Random random = new Random();
        ListNode commonListHead;
        if (commonSize == 0) {
            commonListHead = null;
        } else {
            int[] commonArray = random.ints(commonSize, min, max).toArray();
            commonListHead = hasLoop ? generateTestDataWithLoop(commonArray, !sameLoopEntry).get(0) : ListUtils.create(commonArray);
        }

        int[] firstDiffArray = random.ints(firstSize - commonSize, min, max).toArray();
        ListNode firstListHead = createAndConnectList(commonSize, hasLoop, sameLoopEntry, commonListHead, firstDiffArray);

        int[] secondDiffArray = random.ints(secondSize - commonSize, min, max).toArray();
        ListNode secondListHead = createAndConnectList(commonSize, hasLoop, sameLoopEntry, commonListHead, secondDiffArray);

        List<ListNode> result = new ArrayList<>();
        result.add(firstListHead);
        result.add(secondListHead);
        result.add(commonListHead);
        return result;
    }

    public static List<ListNode> generateTestDataWithLoop(int[] input, boolean fullLoop) {
        ListNode head = ListUtils.create(input);
        return createLoop(head, input.length, fullLoop);
    }

    private static List<ListNode> createLoop(ListNode head, int size, boolean fullLoop) {
        ListNode cur = head;
        ListNode randomNode = null;
        ListNode tail = null;
        int randomIndex = fullLoop ? 0 : new Random().nextInt(size);
        for (int i = 0; i < size; i++) {
            if (i == randomIndex) {
                randomNode = cur;
            }
            if (i == size - 1) {
                tail = cur;
            }
            cur = cur.next;
        }
        assert tail != null;
        tail.next = randomNode;
        return List.of(head, randomNode);
    }

    private static ListNode createAndConnectList(int commonSize, boolean hasLoop, boolean sameLoopEntry, ListNode commonListHead, int[] diffArray) {
        ListNode listHead;
        if (hasLoop && commonSize == 0) {
            listHead = generateTestDataWithLoop(diffArray, false).get(0);
        } else {
            listHead = ListUtils.create(diffArray);
            ListNode cur = listHead;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = (hasLoop && !sameLoopEntry) ? randomNodeFromLoop(commonListHead, commonSize) : commonListHead;
        }
        return listHead;
    }

    private static ListNode randomNodeFromLoop(ListNode head, int maxIndex) {
        int count = new Random().nextInt(maxIndex + 1);
        ListNode cur = head;
        while (count-- > 0) {
            cur = cur.next;
        }
        return cur;
    }

    private static void printTrio(List<ListNode> testDataBundle, int firstSize, int secondSize, int commonSize) {
        System.out.println("");
        System.out.println("");
        System.out.println("___________");
        ListUtils.printFirstN(testDataBundle.get(0), firstSize);
        System.out.println("___________");
        ListUtils.printFirstN(testDataBundle.get(1), secondSize);
        System.out.println("___________");
        ListUtils.printFirstN(testDataBundle.get(2), commonSize);
    }
}
