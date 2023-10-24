package level.etc.dfs.virus;

import java.io.*;
import java.util.StringTokenizer;

public class Virus {
    public static void main(String[] args) throws IOException {
        // 입출력을 빠르게 해줌
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        boolean [][] graph = new boolean[n + 1][n + 1];
        int x, y;
        for (int i = 0; i < m; i++) {
            // 띄어쓰기 기준으로 나누어줌
            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            graph[x][y] = true;
            graph[y][x] = true;
        }

        Virus v = new Virus();
        bw.write(String.valueOf(v.solution(n, m, graph)));
        br.close();
        bw.close();
    }

    public int solution(int n, int m, boolean[][] graph) {
        boolean [] visited = new boolean[n + 1];
        return dfs(n, 1, visited, graph) - 1;
    }

    public int dfs(int n, int idx, boolean[] visited, boolean[][] graph) {
        visited[idx] = true;
        int count = 0;
        ++count;

        for (int i = 1; i <= n; i++) {
            if(!visited[i] && graph[idx][i]) {
                count += dfs(n, i, visited, graph);
            }
        }
        return count;
    }
}
