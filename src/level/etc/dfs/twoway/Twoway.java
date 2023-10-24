package level.etc.dfs.twoway;

import java.io.*;
import java.util.StringTokenizer;

public class Twoway {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        boolean[][] graph = new boolean[n + 1][n + 1];

        int x,y;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            graph[x][y] = true;
            graph[y][x] = true;
        }

        Twoway twoway = new Twoway();
        bw.write(twoway.solution(n, m, graph));

        bw.close();
        br.close();
    }

    public int solution (int n, int m, boolean[][] graph) {
        boolean[] visited = new boolean[n + 1];

        int count = 0;
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            if(!visited[i]) {
                count += dfs(n, i, graph, visited);
                if(0 < count) {
                    ++answer;
                }
            }
        }
        return answer;
    }

    public int dfs(int n, int idx, boolean[][] graph, boolean[] visited) {
        visited[idx] = true;
        int count = 0;
        ++count;
        
        // 0으로 시작하는 노드는 없어서 1부터 시작함
        for (int i = 1; i <= n; i++) {
            // idx 연결된 노드가 아직 방문처리가 안되었을 때 수행
            if(!visited[i] && graph[idx][i]) {
                count += dfs(n, i, graph, visited);
            }
        }
        return count;
    }
}