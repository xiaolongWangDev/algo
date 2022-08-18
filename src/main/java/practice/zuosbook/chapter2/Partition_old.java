package practice.zuosbook.chapter2;

import helper.ListNode;
import helper.ListUtils;

import java.util.Random;

import static helper.ListUtils.print;

public class Partition_old {

    public ListNode partition(ListNode head, int pivot) {
        ListNode lessThanHead = null;
        ListNode equalHead = null;
        ListNode greaterThanHead = null;
        ListNode lessThanTail = null;
        ListNode equalTail = null;
        ListNode greaterThanTail = null;
        ListNode current = head;
        while (current != null) {
            if (current.val < pivot) {
                if (lessThanHead == null) {
                    lessThanHead = current;
                } else {
                    lessThanTail.next = current;
                }
                lessThanTail = current;
            } else if (current.val == pivot) {
                if (equalHead == null) {
                    equalHead = current;
                } else {
                    equalTail.next = current;
                }
                equalTail = current;
            } else {
                if (greaterThanHead == null) {
                    greaterThanHead = current;
                } else {
                    greaterThanTail.next = current;
                }
                greaterThanTail = current;
            }
            current = current.next;
        }

        if(lessThanTail != null) {
            lessThanTail.next = null;
        }
        if(equalTail != null) {
            equalTail.next = null;
        }
        if(greaterThanTail != null) {
            greaterThanTail.next = null;
        }

        return concatThreeLists(lessThanHead, lessThanTail, equalHead, equalTail, greaterThanHead);
    }

    private ListNode concatThreeLists(ListNode firstHead, ListNode firstTail, ListNode secondHead, ListNode secondTail, ListNode thirdHead) {
        ListNode result;
        if (firstHead != null) {
            firstTail.next = secondHead == null ? thirdHead : secondHead;
            if (secondHead != null) {
                secondTail.next = thirdHead;
            }
            result = firstHead;
        } else {
            if (secondHead == null) {
                result = thirdHead;
            } else {
                secondTail.next = thirdHead;
                result = secondHead;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode testData = generateTestData(300, 0, 10);
        print(testData);
        Partition_old algo = new Partition_old();
        ListNode partitioned = algo.partition(testData, 5);
        print(partitioned);
    }

    public static ListNode generateTestData(int size, int min, int max) {
        Random random = new Random();
        int[] temp = random.ints(size, min, max).toArray();
        return ListUtils.create(temp);
    }

}
