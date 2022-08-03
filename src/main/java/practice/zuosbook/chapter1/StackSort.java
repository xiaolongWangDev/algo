package practice.zuosbook.chapter1;

import java.util.Stack;

/**
 * Problem desc:
 * allow to use a 2nd stack.
 * Make the elements in the origin stack in ascending order from stack top to bottom.
 * Hint:
 * Whichever order the target needs to be in,
 * in the opposite order the elements need to be put in the helper stack before the final step.
 * Then, the task becomes how to build up the elements in the helper stack monotonically.
 * Here's how:
 * 1. pick an element T from the origin
 * 2. if it breaks the monotonicity, move the broken elements back to the origin stack
 * 3. then add T into the helper stack
 * 4. if origin is empty then exit otherwise go back to step 1
 *
 * See notebook drawing
 */
public class StackSort {
    public void sort(Stack<Integer> origin) {
        Stack<Integer> helper = new Stack<>();
        while (!origin.isEmpty()) {
            var popped = origin.pop();
            while (!helper.isEmpty() && helper.peek() < popped) {
                origin.push(helper.pop());
            }
            helper.push(popped);
        }

        while (!helper.isEmpty()) {
            origin.push(helper.pop());
        }
    }

    public static void main(String[] args) {
        StackSort p = new StackSort();
        Stack<Integer> stack = new Stack<>();
        stack.push(4);
        stack.push(2);
        stack.push(3);
        stack.push(6);
        stack.push(5);
        p.sort(stack);
        stack.forEach(System.out::println);
    }
}
