package practice.zuosbook.chapter1;

import java.util.Stack;

/**
 * Hint
 * The heuristic is to really think the call stack as a side stack you can use.
 * Think what order that should hold the elements when the origin stack is empty. (that's when you start to insert back)
 * See my notebook drawing for a visual explanation.
 */
public class ReverseAStack<T> {
    public void reverse(Stack<T> s){
        if(s.isEmpty()) return;
        var bottom = popBottom(s);
        reverse(s);
        s.push(bottom);
    }

    public T popBottom(Stack<T> s){
        if(s.size() == 1) {
            return s.pop();
        }
        var top = s.pop();
        var bottom = popBottom(s);
        s.push(top);
        return bottom;
    }

    public static void main(String[] args) {
        ReverseAStack<Integer> algorithm = new ReverseAStack<>();
        Stack<Integer> testStack = new Stack<>();
        testStack.push(1);
        testStack.push(2);
        testStack.push(3);
        testStack.push(4);
        testStack.push(5);
//        printStack(testStack);
        algorithm.reverse(testStack);
        printStack(testStack);
    }

    private static void printStack(Stack<Integer> stack){
        while(!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
