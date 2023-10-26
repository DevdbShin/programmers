package level.two.colorbook;

import java.util.ArrayList;
import java.util.Arrays;

public class ColorBook {
    public static void main(String[] args) {
        ColorBook cb = new ColorBook();
        System.out.println(Arrays.toString(cb.solution(6, 4, new int[][]{{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}})));
    }

    public static int numberOfArea = 0;
    public static int maxSizeOfOneArea = Integer.MAX_VALUE;
    public static int color = 0;
    public static ArrayList<Integer> list = new ArrayList<>();

    public int[] solution(int m, int n, int[][] picture) {

        // m은 row
        // n은 col
        int row = m;
        int col = n;
        int idx = 0;
        int count = 0;
        int color = 0;

        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[i].length; j++) {
                if( 0 < picture[i][j]) {
                    if(color == picture[i][j]) {
                        continue;
                    } else {
                        count += findSibling(i,j,picture,row,col);
                    }
                }
            }
        }
        // 숫자를 발견하면 상하좌우로 연결되어 있는지 확인 : o은 false

        // 그림에 영역 총 갯수 구해야 함
        
        // 가장 큰 영역이 가지고 있는 칸수를 구해야 함

        for (int max : list) {
            if(max < maxSizeOfOneArea) {
                maxSizeOfOneArea = max;
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    // 형제 찾는 메서드
    public int findSibling(int row, int col, int[][] picture, int m, int n) {
        int count = 0;
        if(color == 0) {
            ++numberOfArea;
            color = picture[row][col];
            if(picture[row][col] == color) {
                count += recursiveOneDirection(row + 1, col, color, picture, row, col, m, n);    // 상
                count += recursiveOneDirection(row - 1, col, color, picture, row, col, m, n);    // 하
                count += recursiveOneDirection(row, col - 1, color, picture, row, col, m, n);    // 좌
                count += recursiveOneDirection(row, col + 1, color, picture, row, col, m, n);    // 우
                list.add(count);
            }
        }
        return count;
    }

    // 전달 받은 방향으로 재귀하며 탐색
    public int recursiveOneDirection(int row, int col, int target, int[][] picture, int orgRow, int orgCol, int m, int n) {
        if(row == orgRow && col == orgCol) {
            return 0;
        }
        if(row < 0 || col < 0) {
            return 0;
        }
        if((m - 1) < row || (n - 1) < col) {
            return 0;
        }

        if(picture[row][col] == target) {
            return 1;
        } else {
            int count = 0;
            count += recursiveOneDirection(row + 1, col, target, picture, orgRow, orgCol, m, n);    // 상
            count += recursiveOneDirection(row - 1, col, target, picture, orgRow, orgCol, m, n);    // 하
            count += recursiveOneDirection(row, col - 1, target, picture, orgRow, orgCol, m, n);    // 좌
            count += recursiveOneDirection(row, col + 1, target, picture, orgRow, orgCol, m, n);    // 우
            return count;
        }
    }
}
