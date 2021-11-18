package util;

public class Utils {
    public static void preOrderPrintTreeNodeAndChildren(int index, int[] tree) {
        if (index == 0) {
            System.out.println(tree[index]);
        } else {
            int padding = (int) Math.floor(Math.log(index + 1) / Math.log(2)) * 4;
            System.out.printf("%" + padding + "d%n", tree[index]);
        }

        if(2 * index + 1 < tree.length) {
            preOrderPrintTreeNodeAndChildren(2 * index + 1, tree);
        }

        if(2 * index + 2 < tree.length) {
            preOrderPrintTreeNodeAndChildren(2 * index + 2, tree);
        }
    }

    public static void midOrderPrintTreeNodeAndChildren(int index, int[] tree) {
        if(2 * index + 1 < tree.length) {
            midOrderPrintTreeNodeAndChildren(2 * index + 1, tree);
        }

        if (index == 0) {
            System.out.println(tree[index]);
        } else {
            int padding = (int) Math.floor(Math.log(index + 1) / Math.log(2)) * 4;
            System.out.printf("%" + padding + "d%n", tree[index]);
        }

        if(2 * index + 2 < tree.length) {
            midOrderPrintTreeNodeAndChildren(2 * index + 2, tree);
        }
    }
}
