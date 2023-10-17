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

        Top top = new Top(null, 0, 1, 0);

        for (int i = 0; i < wires.length; i++) {
            countWires(top, wire[i], 0);
        }

        /*for (Top item : top.child.values()) {
            System.out.println(item.toString());
        }*/

        List<MaxTop> list = new ArrayList<>();
        dfs(top, list);

        System.out.println(list.size());
        for (MaxTop max : list) {
            System.out.println(max.toString());
        }


        return result;
    }

    private void dfs(Top parent, List<MaxTop> list) {

        // root 노드 부터 시작하기 때문에 자식 노드를 순회한다.
        for (Top top : parent.child.values()) {

            if(cnt <= top.count) {
                cnt = top.count;
                Top cur = top;
                list.add(new MaxTop(top.name, top.next, top.count, top.depth));
            }

            // 모든 노드 탐색을 위해 재귀를 사용하여 반복 (현재 노드의 모든 자식을 탐색하기 위함)
            dfs(top, list);
        }
    }

    // 노드 생성
    public void countWires(Top top, int[] idx, int depth) {

        if(idx != null || idx[0] != 0) {
            int cur = idx[0];
            int next = idx[1];

            Top curTop;

            if(top.child.containsKey(cur)) {
                curTop = top.child.get(cur);

            } else {
                curTop = new Top(top, cur, next, depth);     // 새로 생성한 노드
                top.child.put(cur, curTop);         // 생성한 노드를 부모 노드의 자식으로 저장
            }

            ++curTop.count;

            ++cnt;
            if(cnt < wire.length) {
                countWires(curTop, wire[cnt], depth+1);
            }
        }
    }

    // 노드 탐색

    // 최적의 노드로 재구성 (전선을 최대한 나누어줌)
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
    public int name;
    public int next;
    public int count;

    public Top parent;
    public Map<Integer, Top> child;

    public int depth;

    Top(Top parent, int name, int next, int depth) {
        this.parent = parent;
        this.name = name;
        this.next = next;
        this.child = new HashMap<>();
        this.count = 0;
        this.depth = depth;
    }

    public String toString() {
        return "name = " + name + ", next = " + next + ", count = " + count;
    }
}
