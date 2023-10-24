package level.etc.dfs.virus;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class VirusObject {
    public static void main(String[] args) throws IOException {
        // 입출력을 빠르게 해줌
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<Node> nodeList = new ArrayList<>();
        int x, y;

        for (int i = 0; i < m; i++) {
            // 띄어쓰기 기준으로 나누어줌
            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            nodeList.add(new Node(x, y, false));
        }

        VirusObject vo = new VirusObject();
        bw.write(String.valueOf(vo.solution(n, m, nodeList)));
        br.close();
        bw.close();
    }

    public int solution(int n, int m, List<Node> nodeList) {
        return dfs(nodeList.get(0), nodeList);
    }

    public int dfs(Node node, List<Node> nodeList) {
        node.setVisited(true);
        int count = 0;
        ++count;
        for (int i = 0; i < nodeList.size(); i++) {
            if(!nodeList.get(i).isVisited()) {
                count += dfs(nodeList.get(i), nodeList);
            }
        }
        return count;
    }
}

class Node {
    private int x; // x축이 기준이 됨
    private int y;
    private boolean visited;
    public Node(int x, int y, boolean visited) {
        this.x = x;
        this.y = y;
        this.visited = visited;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
