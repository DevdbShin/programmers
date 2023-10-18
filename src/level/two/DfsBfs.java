package level.two;

import java.util.HashMap;
import java.util.Map;

public class DfsBfs {

    public static void main(String[] args) {

    }

    public void solution (int[][] input) {

        int idx = 0;

        GraphNode root = new GraphNode();

        root.setDot(input[0][2]);
        root.setParent(null);

        for (int [] item : input) {
            if(idx > 0) {
                createNode(root, item[0],item[1]);
            }
            ++idx;
        }
    }

    public void createNode (GraphNode root, int cur, int next) {

        GraphNode curNode = null;

        if(root.getChild(cur) != null) {
            curNode = root.getChild(cur);
            curNode.setChild(cur, );
        } else {
            curNode = new GraphNode();
            curNode.setParent(root);
            curNode.setDot(cur);
        }
    }
}

class GraphNode {
    private int dot;
    private GraphNode parent;

    private Map<Integer, GraphNode> child;

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
}
