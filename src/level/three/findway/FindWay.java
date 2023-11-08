package level.three.findway;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindWay {
    public static void main(String[] args) {
        FindWay fw = new FindWay();
        System.out.println(Arrays.deepToString(fw.solution(new int[][]{{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}})));
    }
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = {};
        // y가 젤 큰게 root가 됨

        int max = Integer.MIN_VALUE;

        List<Node> list = new ArrayList<>();
        Tree tree = new Tree();
        Node root = null;

        for (int i = 0; i < nodeinfo.length; i++) {
            if(max < nodeinfo[i][1]) {
                max = nodeinfo[i][1];
                root = new Node(i+1, null, null);
            }

            Node node = new Node(i+1, null, null);

            if(0 < i) {
                if(nodeinfo[i][1] < nodeinfo[i - 1][1]) {
                    list.get(i-1).setRight(node);
                }
            }
            list.add(node);
        }

        tree.setRoot(root);
        for (Node node : list) {

        }

        return answer;
    }
}

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}

class Tree {
    Node root;

    /*public Node makeNode(Node left, int data, Node right) {
        Node node = new Node();
        node.left = left;
        node.right = right;
        node.data = data;
        return node;
    }*/

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
