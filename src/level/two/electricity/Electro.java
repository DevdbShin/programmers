package level.two.electricity;

import java.util.ArrayList;
import java.util.List;

public class Electro {

    public static int arrCnt = 0;
    public static void main(String[] args) {
        Electro el = new Electro();
        int [][] tmp = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
        System.out.println(el.solution(9, tmp));

        tmp = new int[][]{{1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}};
        System.out.println(el.solution(7, tmp));
    }

    public int solution(int n, int[][] wires) {

        for (int [] wire : wires) {
            WireNode root1, root2;

            root1 = createWireNode(wire, wires);
            root2 = root1.children.get(0);

            root1.children.remove(0);
            root2.parent = null;

            int roo1Cnt = countChildren(root1);
            int roo2Cnt = countChildren(root2);

            break;
        }

        return 1;
    }

    private  int countChildren(WireNode node) {
        if(0 < node.children.size()) {
            int count = 1;
            for (WireNode wire : node.children) {
                count += countChildren(wire);
            }

            return count;
        } else {
            return 1;
        }
    }

    private WireNode createWireNode(int[] root, int[][] wires) {
        int [][] arr = new int[wires.length - 1][];
        int i = 0;

        for (int [] item : wires) {
            if(item != root) {
                arr[i++] = item;
            }
        }

        arrCnt = arr.length;

        WireNode rootNode = new WireNode(root[0]);
        rootNode.add(new WireNode(root[1]));

        linkTree(rootNode, arr);

        return rootNode;
    }

    private void linkTree(WireNode node, int[][] wires) {
        for (int i = 0; i < wires.length;) {
            int [] item = wires[i];
            if(item[0] == node.top) {
                node.add(new WireNode(item[1]));
                wires[i] = wires[--arrCnt];
            } else if(item[1] == node.top) {
                node.add(new WireNode(item[0]));
                wires[i] = wires[--arrCnt];
            } else {
                ++i;
            }
        }

        for (WireNode wire : node.children) {
            linkTree(wire, wires);
        }
    }
}

class WireNode {
    WireNode parent;
    int top;
    List<WireNode> children = new ArrayList<>();

    WireNode (int top) {
        this.parent = null;
        this.top = top;
    }

    public void add(WireNode child) {
        children.add(child);
        child.parent = this;
    }
}
