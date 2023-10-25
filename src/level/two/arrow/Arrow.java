package level.two.arrow;

import java.util.Arrays;

public class Arrow {

    public static void main(String[] args) {
        Arrow a = new Arrow();
        System.out.println(Arrays.toString(a.solution(5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0})));
        //System.out.println(Arrays.toString(a.solution(1, new int[]{1,0,0,0,0,0,0,0,0,0,0})));
        //System.out.println(Arrays.toString(a.solution(10, new int[]{0,0,0,0,0,0,0,0,3,4,3})));
        //System.out.println(Arrays.toString(a.solution(5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0})));
    }

    public static int publicScore = 0;
    public static int[] scores2 = {10,9,8,7,6,5,4,3,2,1,0};
    public static int[] result = null;

    public int[] solution(int n, int[] info) {
        int[] scores = {10,9,8,7,6,5,4,3,2,1,0};
        int[] answer = new int[info.length];
        result = new int[info.length];
        int[] score = new int[info.length];
        int count = 0;
        
        // 어피치 점수 확인

        // 맨앞이 비어있으면 1을 채워줌

        // 맨앞이 1이라도 있으면 +1 해줌

        // + 해줄때마다 n - count 해줌

        // 만약 count가 n보다 커졌으면 이전으로 돌려보내서 + 해주어야 함

        /*for (int i = 0; i < info.length; i++) {
            if(n <= count) {
                break;
            }
            if(0 < info[i]) {
                if(checkCount(n, count, info[i] + 1)) {
                    count += info[i] + 1;
                    answer[i] = info[i] + 1;
                } else {
                    int sum = count + info[i] + 1;
                    answer = replaceAnswer(sum - n, answer, i);
                    break;
                }
            } else {
                answer[i] = 1;
                ++count;
            }
        }*/

        dfs(0, n, info);

        //int ryanScore = getScore(n, answer, scores);

        //System.out.println(apcScore + "/" + ryanScore);

        int [] tmp = minusApc(info);

        int apcScore =  getScore(n, tmp, scores);
        if(publicScore <= apcScore) {
            return new int [] {-1};
        }
        return result;
    }

    public int getScore(int n, int[] info, int[] scores) {
        int score = 0;
        for (int i = 0; i < info.length; i++) {
            if(n == 0) {
                break;
            }
            if(0 < info[i]) {
                score += scores[i];
                n = n - info[i];
            }
        }
        return score;
    }

    public boolean checkCount (int n, int count, int cur) {
        if(n <= count) {
            return false;
        }
        // count + info[i] + 1 이 n보다 작으면 가능
        return (count + cur) <= n;
    }

    public int[] replaceAnswer(int diff, int[] answer, int idx) {
        int memory = diff;
        for (int i = answer.length -1; 0 <= i; i--) {
            if(diff == 0) {
                break;
            }
            if(memory == diff) {
                if(i == (idx - 1)) {
                    ++answer[i];
                    --diff;
                }
            } else {
                ++answer[i];
                --diff;
            }
        }
        return answer;
    }

    public void dfs(int idx, int n, int[] info) {
        int [] answer = new int[info.length];
        int count = 0;
        for (int i = idx; i < info.length; i++) {
            if(n <= count) {
                break;
            }
            if(0 < info[i]) {
                if(checkCount(n, count, info[i] + 1)) {
                    count += info[i] + 1;
                    answer[i] = info[i] + 1;
                } else {
                    //int sum = count + info[i] + 1;
                    //answer = replaceAnswer(sum - n, answer, i);
                    //answer[i] = info[i];
                    //break;
                    int tmp = count + info[i] + 1;
                    if(n - tmp == 1) {
                        continue;
                    }
                }
            } else {
                answer[i] = 1;
                ++count;
            }
        }

        int curScore = getScore(n, answer, scores2);
        if(publicScore < curScore) {
            publicScore = curScore;
            result = answer;
        }
        System.out.println(Arrays.toString(answer) + " / " + curScore);
        if(idx < info.length) {
            dfs(++idx, n, info);
        }
    }

    public int[] minusApc(int[] info) {
        int[] tmp = info;
        for (int i = 0; i < tmp.length; i++) {
            if(tmp[i] < result[i]) {
                tmp[i] = 0;
            }
        }
        return tmp;
    }
}
