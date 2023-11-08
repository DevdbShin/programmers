package level.three.sheepwolf;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SheepWolf {

    private int idx = 0;

    public static void main(String[] args) {
        SheepWolf sw = new SheepWolf();
        System.out.println(sw.solution(new int[] {0,0,1,1,1,0,1,0,1,0,1,1}, new int[][] {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}}));
    }

    public int solution(int[] info, int[][] edges) {
        Node root = new Node(0, 0);
        Node node = null;
        for (int i = 0; i <edges.length ; i++) {

            Node node = createNode(root, edges, info, i + 1);
        }

        for (Node node : root.child) {
            for (Node child : node.child) {
                System.out.println(node.number + " / " + child.number);
            }
            System.out.println();
        }
        int answer = 0;
        return answer;
    }

    public Node createNode (Node parent, int[][] arr, int[] info, int num) {

        if(num < arr.length) {
            int cur = arr[num][0];
            int next = arr[num][1];
            Node curNode = null;
            if(!parent.child.isEmpty()) {
                for (Node node : parent.child) {
                    if (node.number == cur) {
                        curNode = node;
                    }
                }
            }

            if(curNode == null) {
                curNode = new Node(next, info[num]);
                curNode.parent = parent;
                parent.add(curNode);
                curNode = parent;
            }
            createNode(curNode, arr, info, ++num);
        }
    }
}

class Node {
    int number;
    int kind;
    Node parent;
    List<Node> child = new ArrayList<>();

    Node(int number, int kind) {
        this.number = number;
        this.kind = kind;
    }

    public void add(Node node) {
        this.child.add(node);
    }
}
