package code.forcerecursive;

public class Problem_Hanoi {

    // a valid hanoi tower of height is sitting on "from"
    // all block pieces have continuous id
    // so the bottom one has the same id as the block height
    public void move(int blockHeight, String from, String to, String buffer) {
        if (blockHeight == 1) {
            System.out.printf("move %d from %s to %s \n", blockHeight, from, to);
        } else {
            move(blockHeight - 1, from, buffer, to);
            System.out.printf("move %d from %s to %s \n", blockHeight, from, to);
            move(blockHeight - 1, buffer, to, from);
        }

    }

    public static void main(String[] args) {
        Problem_Hanoi algo = new Problem_Hanoi();
        algo.move(5, "L", "R", "M");
    }
}
