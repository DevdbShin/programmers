package level.two.colorbook;

import java.util.ArrayList;
import java.util.Arrays;

public class ColorBook {
    public static void main(String[] args) {
        ColorBook cb = new ColorBook();
        System.out.println(Arrays.toString(cb.solution(13, 16, new int[][]
                {{0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0}
                ,{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}
                ,{0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}
                ,{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}
                ,{0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0}
                ,{0, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 0}
                ,{0, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 0}
                ,{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}
                ,{0, 1, 3, 3, 3, 1, 1, 1, 1, 1, 1, 3, 3, 3, 1, 0}
                ,{0, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0}
                ,{0, 0, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 0, 0}
                ,{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0}
                ,{0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}})));
    }
    public static int numberOfArea;
    public static int maxSizeOfOneArea;
    public static int color;
    public static ArrayList<Integer> list;
    public static int loop = 1;
    public int[] solution(int m, int n, int[][] picture) {
        numberOfArea = 0;
        maxSizeOfOneArea = Integer.MIN_VALUE;
        color = 0;
        list = new ArrayList<>();
        int[][] copyArr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                copyArr[i][j] = picture[i][j];
            }
        }
        for (int i = 0; i < copyArr.length; i++) {
            for (int j = 0; j < copyArr[i].length; j++) {
                if(0 < copyArr[i][j]) {
                    findSibling(i,j,copyArr,m,n);
                }
            }
        }
        // 숫자를 발견하면 상하좌우로 연결되어 있는지 확인 : o은 false
        // 그림에 영역 총 갯수 구해야 함
        // 가장 큰 영역이 가지고 있는 칸수를 구해야 함
        for (int cnt : list) {
            if(maxSizeOfOneArea < cnt) {
                maxSizeOfOneArea = cnt;
            }
        }
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = list.isEmpty() ? 0 : maxSizeOfOneArea;
        return answer;
    }

    // 형제 찾는 메서드
    public void findSibling(int row, int col, int[][] picture, int m, int n) {
        ++numberOfArea;
        color = picture[row][col];
        picture[row][col] = 0;
        recursiveOneDirection(row + 1, col, color, picture, row, col, m, n);    // 상
        recursiveOneDirection(row - 1, col, color, picture, row, col, m, n);    // 하
        recursiveOneDirection(row, col - 1, color, picture, row, col, m, n);    // 좌
        recursiveOneDirection(row, col + 1, color, picture, row, col, m, n);    // 우
        list.add(loop);
        loop = 1;
    }

    // 전달 받은 방향으로 재귀하며 탐색
    public boolean recursiveOneDirection(int row, int col, int target, int[][] picture, int orgRow, int orgCol, int m, int n) {
        if(row == orgRow && col == orgCol) {
            return false;
        }
        if(row < 0 || col < 0 || m <= row || n <= col || picture[row][col] != target) {
            return false;
        }
        if(picture[row][col] == target) {
            picture[row][col] = 0;
            ++loop;
            recursiveOneDirection(row + 1, col, target, picture, orgRow, orgCol, m, n);
            recursiveOneDirection(row - 1, col, target, picture, orgRow, orgCol, m, n);
            recursiveOneDirection(row, col - 1, target, picture, orgRow, orgCol, m, n);
            recursiveOneDirection(row, col + 1, target, picture, orgRow, orgCol, m, n);
        }
        return true;
    }
}