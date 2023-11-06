package level.three;

import java.util.Arrays;

public class Building {
    public static void main(String[] args) {
        Building b = new Building();
        System.out.println(b.solution(new int[][] {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}}, new int[][] {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}}));
        System.out.println(loop);
    }

    public static int loop = 0;

    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        Arrays.sort(skill, (c, n) -> {
            return c[0] - n[0];
        });
        for (int[] item : skill) {
            setDegree(item[0], item[1], item[2], item[3], item[4], item[5], board);
        }
        answer = countBoard(board);
        return answer;
    }

    public void setDegree(int type, int s1, int s2, int e1, int e2, int degree, int[][] board) {
        // row
        for (int i = s1; i <= e1; i++) {
            for (int j = s2; j <= e2; j++) {
                // colum
                if(type == 1) {
                    board[i][j] -= degree;
                } else {
                    board[i][j] += degree;
                }
            }
        }
    }

    public int countBoard(int[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] > 0) {
                    ++count;
                }
            }
        }
        return count;
    }

}
