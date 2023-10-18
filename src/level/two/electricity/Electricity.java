package level.two.electricity;


import level.two.menu.MenuFixed;

import java.util.*;

public class Electricity {

    public static void main(String[] args) {
        Electricity er = new Electricity();

        int [][] tmp = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};
        System.out.println(Arrays.toString(er.solution(0, tmp)));
    }

    static int[][] wire;
    int [] result;

    static int cnt = 0;

    public int[] solution(int n, int[][] wires) {
        int answer = -1; // 두전력망이 가지고 있는 송신탑 개수의 차이

        wire = wires;

        Top top = new Top(null, 0, 0);

        for (int i = 0; i < wires.length; i++) {
            countWires(top, wire[i], 0);
        }

        for (Top item : top.child) {
            System.out.println(item.toString());
        }

        return result;
    }
    // 노드 생성
    public void countWires(Top top, int[] idx, int depth) {

        if(idx != null) {
            int cur = idx[0];
            int next = idx[1];

            Top curTop = null;

            // 자식이 있는지 찾기
            for (int i = 0; i < top.child.size(); i++) {
                if(top.child.get(i) == top) {
                    curTop = top.child.get(i);
                }
            }

            // 자식이 없으면 add
            if(curTop == null) {
                curTop = new Top(top, cur, depth);     // 새로 생성한 노드
                ++curTop.count;
                top.child.add(curTop);         // 생성한 노드를 부모 노드의 자식으로 저장
            }

            ++cnt;

            if(cnt < wire.length) {
                countWires(curTop, wire[cnt], depth+1);
            }
        }
    }
}

class MaxTop {
    public int name;
    public int next;
    public int count;
    public int depth;

    public MaxTop(int name, int next, int count, int depth) {
        this.name = name;
        this.next = next;
        this.count = count;
        this.depth = depth;
    }

    public String toString() {
        String str = "/" + name + "/" + count + "/" + depth;
        return str;
    }
}

class Top {
    public int top;
    public int count;
    public Top parent;
    public int depth;
    public List<Top> child;

    Top(Top parent, int top, int depth) {
        this.parent = parent;
        this.top = top;
        this.child = new ArrayList<>();
        this.count = 0;
        this.depth = depth;
    }

    public String toString() {
        return "top = " + top + ", count = " + count + ", depth = " + depth;
    }
}
