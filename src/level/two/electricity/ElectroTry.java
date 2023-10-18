package level.two.electricity;

import java.util.HashMap;
import java.util.Map;

public class ElectroTry {
    public static void main(String[] args) {

    }

    public int solution (int n, int[][] wires) {

        for (int i = 0; i < wires.length; i++) {
           int [] wire = wires[i];
            TopNode root = createNode(wire, wires);
        }

        return 1;
    }

    public TopNode createNode(int[] parent, int[][] wires) {

        for (int i = 0; i < wires.length; i++) {
            TopNode  top = new TopNode(null, parent[i]);
        }

        return null;
    }
}

class TopNode {
    private TopNode parent;
    private int wire;
    private Map<Integer, TopNode> child;

    public TopNode(TopNode parent, int wire) {
        this.parent = parent;
        this.wire = wire;
        this.child = new HashMap<>();
    }

    public void put(int top, TopNode child) {
        this.child.put(top, child);
        this.parent = this;
    }
}
