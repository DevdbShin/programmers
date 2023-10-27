package level.two.colorbook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = Integer.MIN_VALUE;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[i].length; j++) {
                if(0 < picture[i][j]) {
                    ++numberOfArea;
                    findSibling(picture[i][j], i, j, m, n, list, picture);
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
    public void findSibling(int target, int row, int col, int m, int n, List<Integer> list, int[][] picture) {
        picture[row][col] = 0;
        int count = 1;
        count += recursiveOneDirection(row + 1, col, target, picture, row, col, m, n);    // 상
        count += recursiveOneDirection(row - 1, col, target, picture, row, col, m, n);    // 하
        count += recursiveOneDirection(row, col - 1, target, picture, row, col, m, n);    // 좌
        count += recursiveOneDirection(row, col + 1, target, picture, row, col, m, n);    // 우
        list.add(count);
    }

    // 전달 받은 방향으로 재귀하며 탐색
    public int recursiveOneDirection(int row, int col, int target, int[][] picture, int orgRow, int orgCol, int m, int n) {
        if(row == orgRow && col == orgCol) {
            return 0;
        }
        if(row < 0 || col < 0 || m <= row || n <= col || picture[row][col] != target) {
            return 0;
        }
        int count = 1;
        if(picture[row][col] == target) {
            picture[row][col] = 0;
            count += recursiveOneDirection(row + 1, col, target, picture, orgRow, orgCol, m, n);
            count += recursiveOneDirection(row - 1, col, target, picture, orgRow, orgCol, m, n);
            count += recursiveOneDirection(row, col - 1, target, picture, orgRow, orgCol, m, n);
            count += recursiveOneDirection(row, col + 1, target, picture, orgRow, orgCol, m, n);
        }
        return count;
    }
}