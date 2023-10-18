package level.two.electricity;

import java.util.ArrayList;
import java.util.List;

public class Electro {
    private static int wiresCount=0;
    private static int tmpCnt = 0;
    public static void main(String[] args) throws Exception {
        int[][] wires = {{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}};
        //int[][] wires = {{1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}};

        Electro el = new Electro();
        System.out.println(el.solution(1, wires));
        //System.out.println(el.solution(1, new  int[][] {{1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}}));
        
    }

    public int solution(int n, int[][] wires) {
        int min = Integer.MAX_VALUE;

        for(int[] wire : wires) {
            WireTreeNode root1, root2;
            root1 = createWireTree(wire, wires);
            root2 = root1.children.get(0);

            root1.children.remove(0);
            root2.parent = null;

            int root1Count = countChildren(root1);
            int root2Count = countChildren(root2);

            System.out.println(root1.id + "," + root2.id + ": [" + root1Count + "-" + root2Count + "=" + Math.abs(root2Count-root1Count) + "]");

            if(Math.abs(root2Count-root1Count) < min) {
                min = Math.abs(root2Count-root1Count);
            }
        }
        return min;
    }

    private int countChildren(WireTreeNode node) {
        if(0<node.children.size()) {
            int count=1;
            for(WireTreeNode child : node.children) {
                count += countChildren(child);
            }
            return count;
        } else {
            return 1;
        }
    }

    private WireTreeNode createWireTree(int[] root, int[][] wires) {
        int [][] tempWires = new int[wires.length-1][];
        int i=0;

        for(int[] wire : wires) {
            if(wire!=root) {
                tempWires[i++] = wire;
            }
        }

        wiresCount = tempWires.length;

        WireTreeNode rootNode = new WireTreeNode(root[0]);
        rootNode.add(new WireTreeNode(root[1]));

        linkTree(rootNode, tempWires);
        return rootNode;
    }

    private void linkTree(WireTreeNode node, int[][] wires) {
        for(int i=0;i<wiresCount;) {
            int[] wire = wires[i];

            if(wire[0] ==  node.id) {
                //System.out.println("wire [0] : " + wire[0] + " / " + wire[1]);
                node.add(new WireTreeNode(wire[1]));
                wires[i] = wires[--wiresCount];
                wires[wiresCount] = null;

            } else if (wire[1] == node.id) {
                //System.out.println("wire [1] : " + wire[0] + " / " + wire[1]);
                node.add(new WireTreeNode(wire[0]));
                wires[i] = wires[--wiresCount];
                wires[wiresCount] = null;
            } else {
                ++i;
            }
        }

        for(WireTreeNode child : node.children) {
            linkTree(child, wires);
        }
    }
}


class WireTreeNode {
    public WireTreeNode parent;
    public int id;
    public List<WireTreeNode> children;

    public WireTreeNode(int id) {
        this.parent = null;
        this.id = id;
        this.children = new ArrayList<>();
    }

    public void add(WireTreeNode child) {
        children.add(child);
        child.parent = this;
    }

    @Override
    public String toString() {
        return "id=" + id + ", child = " + children.get(0).id;
    }
}