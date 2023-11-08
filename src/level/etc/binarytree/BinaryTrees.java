package level.etc.binarytree;

public class BinaryTrees {

    public static void main(String[] args) {
        int[][] relationships = new int[][] {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}};
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.buildTree(relationships);
        binaryTree.printTree(binaryTree.getRoot());
        System.out.println();
        binaryTree.printTree2(binaryTree.getRoot());
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

class BinaryTree {
    private TreeNode root;

    public void buildTree(int[][] relationships) {
        TreeNode[] nodes = new TreeNode[relationships.length + 1];

        for (int[] relation : relationships) {
            int parentVal = relation[0];
            int childVal = relation[1];

            if (nodes[parentVal] == null) {
                nodes[parentVal] = new TreeNode(parentVal);
            }

            if (nodes[childVal] == null) {
                nodes[childVal] = new TreeNode(childVal);
            }

            TreeNode parent = nodes[parentVal];
            TreeNode child = nodes[childVal];

            if (parent.left == null) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }

        root = nodes[0];
    }

    public TreeNode getRoot() {
        return root;
    }

    public void printTreePreorder(TreeNode node) {
        if (node == null) {
            return;
        }

        System.out.print(node.val + " ");
        printTreePreorder(node.left);
        printTreePreorder(node.right);
    }

    // Utility function to print the binary tree (preorder traversal)
    public void printTree(TreeNode node) {
        if (node == null) {
            return;
        }

        int left = node.left == null ? 0 : node.left.val;
        int right = node.right == null ? 0 : node.right.val;
        System.out.println(node.val + " / " +  left  + " / " + right);
        printTree(node.left);
        printTree(node.right);
    }

    public void printTree2(TreeNode node) {
        if (node == null) {
            return;
        }

        printTree(node.left);
        System.out.print(node.val + " ");
        printTree(node.right);
    }
}
