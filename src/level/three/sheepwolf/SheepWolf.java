package level.three.sheepwolf;

public class SheepWolf {

    public static void main(String[] args) {
        SheepWolf sw = new SheepWolf();
        System.out.println(sw.solution(new int[] {0,0,1,1,1,0,1,0,1,0,1,1}, new int[][] {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}}));
    }

    public int solution(int[] info, int[][] edges) {
        Tree tree = new Tree();
        tree.createTree(info, edges);
        tree.printTreePreorder(tree.getRoot());
        int answer = 0;
        return answer;
    }
}

class Node {
    int number;
    int kind;
    Node left;
    Node right;

    Node(int number, int kind) {
        this.number = number;
        this.kind = kind;
        this.left = null;
        this.right = null;
    }
}

class Tree {
    Node root;

    public Node getRoot() {
        return root;
    }

    public void createTree(int[] info, int[][] edges) {
        Node[] nodes = new Node[edges.length];

        int idx = 0;
        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];

            if (nodes[parent] == null) {
                nodes[parent] = new Node(parent, info[idx]);
            }

            if (nodes[child] == null) {
                nodes[child] = new Node(child, info[idx]);
            }

            Node parentNode = nodes[parent];
            Node childNode = nodes[child];

            if (parentNode.left == null) {
                parentNode.left = childNode;
            } else {
                parentNode.right = childNode;
            }
            ++idx;
        }

        this.root = nodes[0];
    }

    public void printTreePreorder(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node.number + " / " + node.kind);
        printTreePreorder(node.left);
        printTreePreorder(node.right);
    }
}
