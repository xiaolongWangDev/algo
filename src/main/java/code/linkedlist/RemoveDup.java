package code.linkedlist;

// leetcode 82
public class RemoveDup {

    public LinkedListUtils.Node run(LinkedListUtils.Node head) {
        LinkedListUtils.Node cur = head;
        LinkedListUtils.Node newHead = null;
        LinkedListUtils.Node tail = null;
        int count = 1;
        while (cur != null) {
            if (cur.next != null && cur.value == cur.next.value) {
                count++;
            } else {
                if (count == 1) {
                    if (tail == null) {
                        tail = cur;
                        newHead = cur;
                    } else {
                        tail.next = cur;
                        tail = cur;
                    }
                }
                count = 1;
            }
            cur = cur.next;
        }
        if (tail != null) tail.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        LinkedListUtils.Node head = LinkedListUtils.generateTestData(new int[]{1, 2, 3, 3, 3, 4});
        LinkedListUtils.print(head);
        RemoveDup algo = new RemoveDup();
        LinkedListUtils.print(algo.run(head));
    }
}
