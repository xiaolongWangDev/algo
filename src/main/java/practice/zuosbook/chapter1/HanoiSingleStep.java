package practice.zuosbook.chapter1;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


// a valid hanoi tower of height is sitting on "from"
// all block pieces have continuous id
// so the bottom one has the same id as the block height
public class HanoiSingleStep {
    static char L = 'L';
    static char M = 'M';
    static char R = 'R';

    private final Map<Character, Stack<Integer>> states = new HashMap<>();
    private final int nLevels;
    private final char from;
    private final char to;

    public HanoiSingleStep(int nLevels, char from, char to) {
        this.nLevels = nLevels;
        this.from = from;
        this.to = to;
        states.put(L, new Stack<>());
        states.put(M, new Stack<>());
        states.put(R, new Stack<>());
        for (int i = nLevels; i > 0; i--) {
            states.get(from).push(i);
        }
    }

    public void start() {
        printState();
        move(nLevels, from, to);
    }

    // core logic is here.
    // additional constraint for this problem: piece can only move to their neighbour. (in another word: 1 step)
    public void move(int nLevels, char from, char to) {
        if (nLevels == 1) {
            if (from == M || to == M) {
                step(from, to);
            } else {
                step(from, M);
                step(M, to);
            }
        } else {
            if (from == M || to == M) {
                char other = (from == L || to == L) ? R : L;
                move(nLevels - 1, from, other);
                step(from, to);
                move(nLevels - 1, other, to);
            } else {
                // because the bottom piece cannot go from "form" to "to" directly, it needs to take 2 steps
                // and the rest of the pieces should not get in the way, so they need to move "vigorously"
                move(nLevels - 1, from, to);
                step(from, M);
                move(nLevels - 1, to, from);
                step(M, to);
                move(nLevels - 1, from, to);
            }
        }
    }


    static class LastMove {
        Character source;
        Character target;
    }
    public void moveNoRecursion() {
        LastMove lastMove = new LastMove();

        while (true) {
            /**
             * add this lines to make it a normal hanoi
             */
//            tryMove(L, R, lastMove);
//            if(states.get(to).size() == nLevels) break;
//            tryMove(R, L, lastMove);
//            if(states.get(to).size() == nLevels) break;
            tryMove(L, M, lastMove);
            if(states.get(to).size() == nLevels) break;
            tryMove(M, L, lastMove);
            if(states.get(to).size() == nLevels) break;
            tryMove(M, R, lastMove);
            if(states.get(to).size() == nLevels) break;
            tryMove(R, M, lastMove);
            if(states.get(to).size() == nLevels) break;
        }
    }

    private void tryMove(Character from, Character to, LastMove lastMove) {
        if(from == lastMove.target && to == lastMove.source) return;
        if(states.get(from).isEmpty()) return;
        if(!states.get(to).isEmpty() && states.get(from).peek() > states.get(to).peek()) return;
        states.get(to).push(states.get(from).pop());
        lastMove.source = from;
        lastMove.target = to;
        printState();
    }

    /* could print move i from A to B, but im printing out the towers sideways */
    void step(char from, char to) {
        states.get(to).push(states.get(from).pop());
        printState();
    }

    void printState() {
        System.out.print("L:");
        printStackBottomUp(states.get(L));
        System.out.println();
        System.out.print("M:");
        printStackBottomUp(states.get(M));
        System.out.println();
        System.out.print("R:");
        printStackBottomUp(states.get(R));
        System.out.println();
        System.out.println("==============");
    }

    void printStackBottomUp(Stack<Integer> s) {
        if (s.isEmpty()) return;
        var value = s.pop();
        printStackBottomUp(s);
        s.push(value);
        System.out.printf("%2d", value);
    }

    public static void main(String[] args) {
        var hanoi = new HanoiSingleStep(3, 'L', 'R');
        hanoi.moveNoRecursion();
    }

}
