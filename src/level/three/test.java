package level.three;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        test t = new test();
        //System.out.println(t.solution(2, 50, 10, 10));
        //System.out.println(t.solution(10000, 10000));
        System.out.println(Arrays.toString(t.solution3(new int[]{1, 1, 1, 1, 1, 1, 1, 1}, new int[][]{{1, 7, 1}, {1, 6, 3}, {1, 5, 2}, {1, 2, 1}, {5, 5, 1}, {1, 1, 1}})));
    }

    public static int loop = 0;

    public long solution2(int x, int y, int iVal, int jVal) {
        long answer = 0L;

        int val = x;
        int[] arr = new int[x == y ? 1 : y - 1];
        for (int i = 0; val <= y; i++) {
            arr[i] = val++;
        }

        for (int i = 0, start = 0; start < iVal; i = (i + 1) % arr.length, start++) {
            for (int j = 0, end = start; end < jVal; j = (j + 1) % arr.length, end++) {
                answer += arr[i] + arr[j];
            }
        }
        return answer;
    }

    public int[] solution(int[] board, int[][] arr) {

        int[] tmp = new int[board.length];
        for (int i = 0; i < arr.length; i++) {
            int start = arr[i][0];
            int end = arr[i][1];
            int val = arr[i][2];

            tmp[start] += val;
            tmp[end] += val;
        }

        System.out.println(Arrays.toString(tmp));

        for (int i = 0; i < tmp.length; i++) {
            tmp[i] += board[i];
        }

        return tmp;
    }

    public int[] solution3(int[] values, int[][] variance) {
        int[] varianceValues = new int[values.length+1];

        for(int i=0;i<variance.length;++i) {
            varianceValues[variance[i][0]] += variance[i][2];
            varianceValues[variance[i][1]+1] += -variance[i][2];
        }

        for(int i=1;i<varianceValues.length;++i) {
            varianceValues[i] += varianceValues[i-1];
            values[i-1]+=varianceValues[i-1];
        }

        return values;
    }

}
