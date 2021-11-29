package linkedlist;

import static linkedlist.LinkedListUtils.*;

public class Partition {

    public Node partition(Node head, int pivot) {
        Node lessThanHead = null;
        Node equalHead = null;
        Node greaterThanHead = null;
        Node lessThanTail = null;
        Node equalTail = null;
        Node greaterThanTail = null;
        Node current = head;
        while (current != null) {
            if (current.value < pivot) {
                if (lessThanHead == null) {
                    lessThanHead = current;
                } else {
                    lessThanTail.next = current;
                }
                lessThanTail = current;
            } else if (current.value == pivot) {
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

    private Node concatThreeLists(Node firstHead, Node firstTail, Node secondHead, Node secondTail, Node thirdHead) {
        Node result;
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
        Node testData = generateTestData(300, 0, 10);
        print(testData);
        Partition algo = new Partition();
        Node partitioned = algo.partition(testData, 5);
        print(partitioned);
    }
}
