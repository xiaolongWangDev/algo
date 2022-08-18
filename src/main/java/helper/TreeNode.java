package helper;

public class TreeNode {
    public int value;
    public TreeNode left;
    public TreeNode right;
    /* not a standard tree node attribute, only used for ParentAwareTree algorithm */
    public TreeNode parent;
    /* following fields are for balanced tree structure */
    /* AVL */
    public int height = -1;
    /* SBT */
    public int size;
    /* RBT */
    public int color;
}
