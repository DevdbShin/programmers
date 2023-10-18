package level.two;

import java.util.HashMap;
import java.util.Map;

public class DfsBfs {

    private int idx = 0;

    public static void main(String[] args) {
        DfsBfs db = new DfsBfs();
        db.solution(new int[][] {{4,5,1},{1,2},{1,3},{1,4},{2,4},{3,4}});
    }

    public void solution (int[][] input) {

        GraphNode root = new GraphNode();
        root.setDot(input[0][2]);
        root.setParent(null);
        idx = input.length;
        createNode(root, input);

        Map<Integer, GraphNode> map = root.getChildValues();
        System.out.println(map.size());
        for (GraphNode node : map.values()) {
            if(node != null) {
                System.out.println(node.getDot());
            }
        }
    }

    public void createNode (GraphNode parent, int[][] arr) {

        if (idx != 0) {
            for (int i = 0; i < idx; ) {
                if (idx != 0 && arr[i] != null) {
                    int cur = arr[i][0];
                    int next = arr[i][1];

                    GraphNode curNode = null;
                    if (parent.getChild(cur) != null) {
                        curNode = new GraphNode();
                        curNode.setParent(parent);
                        curNode.setDot(cur);
                    } else {
                        parent.setChild(next, curNode);
                        curNode = parent;
                    }
                    arr[i] = null;
                    createNode(curNode, arr);
                }
                --idx;
            }
        }
    }
}

class GraphNode {
    private int dot;
    private GraphNode parent;

    private Map<Integer, GraphNode> child = new HashMap<>();

    public int getDot() {
        return dot;
    }

    public void setDot(int dot) {
        this.dot = dot;
    }

    public GraphNode getParent() {
        return parent;
    }

    public void setParent(GraphNode parent) {
        this.parent = parent;
    }

    public GraphNode getChild(int dot) {
        return this.child.get(dot);
    }
    public void setChild(int dot, GraphNode node) {
        this.child.put(dot, node);
    }

    public Map<Integer, GraphNode> getChildValues() {
        return this.child;
    }
}
