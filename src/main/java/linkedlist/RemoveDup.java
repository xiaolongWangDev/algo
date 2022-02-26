package linkedlist;

import static linkedlist.LinkedListUtils.*;

public class RemoveDup {

    public Node run(Node head) {
        Node cur = head;
        Node newHead = null;
        Node tail = null;
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
        Node head = generateTestData(new int[]{1, 2, 3, 3, 3, 4});
        print(head);
        RemoveDup algo = new RemoveDup();
        print(algo.run(head));
    }
}
