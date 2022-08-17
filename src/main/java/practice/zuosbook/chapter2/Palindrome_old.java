package practice.zuosbook.chapter2;

import tobeorganized.linkedlist.LinkedListUtils.Node;

import java.util.Stack;
import java.util.function.Function;

import static tobeorganized.linkedlist.LinkedListUtils.*;

public class Palindrome_old {

    public Boolean isPalindromeUseStack(Node head) {
        int length = length(head);
        if (length == 1) {
            return true;
        }

        Node median = findMedian(head);
        Node current = head;
        Stack<Node> stack = new Stack<>();
        while (current != median) {
            stack.push(current);
            current = current.next;
        }
        // push median in if it's even
        if (length % 2 == 0) {
            stack.push(current);
        }
        current = current.next;
        while (current != null) {
            if (current.value != stack.pop().value) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    public Boolean isPalindromeUseReverse(Node head) {
//        print(head);
        int length = length(head);
        if (length == 1) {
            return true;
        }

        Node median = findMedian(head);
        Node otherEnd = length % 2 == 0 ? reverse(median.next) : reverse(median);
        Node current = head;
        Node otherCurrent = otherEnd;
//        print(head);
//        print(otherEnd);

        boolean result = true;
        while (current != median) {
            if (current.value != otherCurrent.value) {
                result = false;
                break;
            }
            current = current.next;
            otherCurrent = otherCurrent.next;
        }
        if(result && length % 2 == 0) {
            result = current.value == otherCurrent.value;
        }
        reverse(otherEnd);
//        print(head);
//        print(otherEnd);
//        System.out.println("______");
        return result;
    }

    public static void main(String[] args) {
        Node testData = generateTestData(new int[]{1, 2, 3, 4, 5});
        Palindrome_old algo = new Palindrome_old();
        Function<Node, Boolean> method = algo::isPalindromeUseReverse;

        testData = reverse(testData);

        if (method.apply(testData)) {
            throw new RuntimeException("wrong");
        }

        testData = generateTestData(new int[]{1, 2, 3, 2, 2});
        if (method.apply(testData)) {
            throw new RuntimeException("wrong");
        }

        testData = generateTestData(new int[]{1, 2, 2, 1});
        if (!method.apply(testData)) {
            throw new RuntimeException("wrong");
        }

        testData = generateTestData(new int[]{1, 3, 1});
        if (!method.apply(testData)) {
            throw new RuntimeException("wrong");
        }

        testData = generateTestData(new int[]{2, 2});
        if (!method.apply(testData)) {
            throw new RuntimeException("wrong");
        }

        testData = generateTestData(new int[]{4});
        if (!method.apply(testData)) {
            throw new RuntimeException("wrong");
        }

    }
}
