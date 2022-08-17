package practice.zuosbook.chapter2;

import helper.ListNode;
import helper.ListUtils;

public class AddNumbersFromTwoLists {
    public static ListNode solve(ListNode a, ListNode b) {
        ListNode aCur = Reverse.solve(a);
        ListNode bCur = Reverse.solve(b);
        ListNode sumHead = null;
        ListNode sumCur = null;

        int carry = 0;
        while (aCur != null || bCur != null) {
            ListNode newNode = new ListNode();
            int sum = (aCur == null ? 0 : aCur.val) + (bCur == null ? 0 : bCur.val) + carry;
            newNode.val = sum % 10;
            carry = sum / 10;

            if (sumCur == null) {
                sumCur = newNode;
            } else {
                sumCur.next = newNode;
                sumCur = sumCur.next;
            }
            if (sumHead == null) {
                sumHead = sumCur;
            }

            if (aCur != null) aCur = aCur.next;
            if (bCur != null) bCur = bCur.next;
        }
        if (carry != 0) {
            ListNode newNode = new ListNode();
            newNode.val = carry;
            sumCur.next = newNode;
        }
        return Reverse.solve(sumHead);
    }

    public static void main(String[] args) {
//        ListUtils.print(solve(ListUtils.create(new int[]{9, 3, 7}), ListUtils.create(new int[]{6, 3})));
        ListUtils.print(solve(ListUtils.create(new int[]{9, 9, 8}), ListUtils.create(new int[]{6, 3})));
    }
}
