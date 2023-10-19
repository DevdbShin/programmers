package level.two;

import level.two.menu.MenuFixed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        createNode(root, input, 1);

        for (GraphNode node : root.getChild()) {
            System.out.println(node.getDot());
        }
    }

    public void createNode (GraphNode parent, int[][] arr, int num) {

        if(num < idx) {
            if (arr[num] != null) {
                int cur = arr[num][0];
                int next = arr[num][1];
                GraphNode curNode = null;
                if(!parent.getChild().isEmpty()) {
                    for (GraphNode node : parent.getChild()) {
                        if (node.getDot() == next) {
                            curNode = node;
                        } else {
                            curNode = new GraphNode();
                            curNode.setDot(next);
                            curNode.setParent(parent);
                            parent.add(curNode);
                            curNode = parent;
                        }
                    }
                } else {
                    curNode = new GraphNode();
                    curNode.setDot(next);
                    curNode.setParent(parent);
                    parent.add(curNode);
                    curNode = parent;
                }
                arr[num] = null;
                ++num;
                createNode(curNode, arr, num);
            }
            ++idx;
        }
    }
}

class GraphNode {
    private int dot;
    private GraphNode parent;

    private final List<GraphNode> child = new ArrayList<>();

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

    public void add(GraphNode node) {
        this.child.add(node);
    }

    public List<GraphNode> getChild() {
        return this.child;
    }
}
