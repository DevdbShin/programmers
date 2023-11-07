package level.three.building;

import java.util.Arrays;

public class ReBuilding {
    public static void main(String[] args) {
        ReBuilding b = new ReBuilding();
        System.out.println(b.solution(new int[][] {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}}, new int[][] {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}}));
    }

    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] sum = new int[board.length][board[0].length];

        for (int[] item : skill) {
            // 중첩영역 합산과 동시에 비중첩영역도 저장 하려면?
            int startY = item[1];   // roop : 0 / 2 / 1 / 0
            int startX = item[2];   // roop : 0 / 0 / 0 / 1
            int endY = item[3];     // roop : 3 / 2 / 3 / 3
            int endX = item[4];     // roop : 4 / 3 / 1 / 3

            int degree = item[5];
            if(item[0] == 1) {
                degree = -degree;
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sum[i][j] += sum[i][j];
            }
        }
        //answer = countBoard(board, sum);
        return answer;
    }

    public int countBoard(int[][] board, int[][] sum) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(0 < j) {
                    sum[i][j] += sum[i][j-1];
                }
                if(0 < board[i][j] + sum[i][j]){
                    count++;
                }
            }
        }
        return count;
    }
}

