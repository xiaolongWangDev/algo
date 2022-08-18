package practice.zuosbook.chapter2;

import helper.ListNode;
import helper.ListUtils;

import java.util.Stack;
import java.util.function.Function;

public class Palindrome_old {

    public Boolean isPalindromeUseStack(ListNode head) {
        int length = length(head);
        if (length == 1) {
            return true;
        }

        ListNode median = findMedian(head);
        ListNode current = head;
        Stack<ListNode> stack = new Stack<>();
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
            if (current.val != stack.pop().val) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    public Boolean isPalindromeUseReverse(ListNode head) {
//        print(head);
        int length = length(head);
        if (length == 1) {
            return true;
        }

        ListNode median = findMedian(head);
        ListNode otherEnd = length % 2 == 0 ? reverse(median.next) : reverse(median);
        ListNode current = head;
        ListNode otherCurrent = otherEnd;
//        print(head);
//        print(otherEnd);

        boolean result = true;
        while (current != median) {
            if (current.val != otherCurrent.val) {
                result = false;
                break;
            }
            current = current.next;
            otherCurrent = otherCurrent.next;
        }
        if(result && length % 2 == 0) {
            result = current.val == otherCurrent.val;
        }
        reverse(otherEnd);
//        print(head);
//        print(otherEnd);
//        System.out.println("______");
        return result;
    }

    public static ListNode reverse(ListNode head) {
        ListNode current = head;
        ListNode prev = null;
        while (current != null) {
            ListNode temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }

        return prev;
    }

    public static int length(ListNode head) {
        ListNode current = head;
        int length = 0;
        while (current != null) {
            current = current.next;
            length++;
        }
        return length;
    }
    public static void main(String[] args) {
        ListNode testData = ListUtils.create(new int[]{1, 2, 3, 4, 5});
        Palindrome_old algo = new Palindrome_old();
        Function<ListNode, Boolean> method = algo::isPalindromeUseReverse;

        testData = reverse(testData);

        if (method.apply(testData)) {
            throw new RuntimeException("wrong");
        }

        testData = ListUtils.create(new int[]{1, 2, 3, 2, 2});
        if (method.apply(testData)) {
            throw new RuntimeException("wrong");
        }

        testData = ListUtils.create(new int[]{1, 2, 2, 1});
        if (!method.apply(testData)) {
            throw new RuntimeException("wrong");
        }

        testData = ListUtils.create(new int[]{1, 3, 1});
        if (!method.apply(testData)) {
            throw new RuntimeException("wrong");
        }

        testData = ListUtils.create(new int[]{2, 2});
        if (!method.apply(testData)) {
            throw new RuntimeException("wrong");
        }

        testData = ListUtils.create(new int[]{4});
        if (!method.apply(testData)) {
            throw new RuntimeException("wrong");
        }

    }

    public static ListNode findMedian(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            if (fast != null) {
                slow = slow.next;
            }
        }

        return slow;
    }
}
