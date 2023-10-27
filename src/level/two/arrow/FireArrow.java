package level.two.arrow;

import java.util.Arrays;

public class FireArrow {
    public static void main(String[] args) {
        FireArrow fa = new FireArrow();
        //System.out.println(Arrays.toString(re.solution(5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0})));
        //System.out.println(Arrays.toString(re.solution(3, new int[]{0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0})));
        //System.out.println(Arrays.toString(re.solution(1, new int[]{1,0,0,0,0,0,0,0,0,0,0})));
        System.out.println(Arrays.toString(fa.solution(9, new int[]{0,0,1,2,0,1,1,1,1,1,1})));
    }
    static int maxDiffScore = -1000;
    static int[] bestArrows = new int[11];
    static int[] ryan = new int[11];

    public int[] solution(int n, int[] info) {
        backtrack(n, info, 0);
        if (maxDiffScore <= 0) {
            return new int[]{-1};
        }
        return bestArrows;
    }

    public void backtrack(int n, int[] info, int idx) {
        if (idx == n) {
                int apeachScore = 0;
                int ryanScore = 0;

                for (int i = 0; i < 11; i++) {
                    if(info[i] == 0 && ryan[i] == 0) continue;
                    if (ryan[i] <= info[i]) {
                        apeachScore += 10 - i;
                    } else {
                        ryanScore += 10 - i;
                    }
                }
                int diff = ryanScore - apeachScore;
                if (maxDiffScore <= diff) {
                    maxDiffScore = diff;
                    bestArrows = Arrays.copyOf(ryan, 11);
                }
            return;
        }

        if(idx < 5) {
            System.out.println(Arrays.toString(ryan));
        }

        for(int i = 0; i < info.length && ryan[i]<= info[i]; i++) {
            ryan[i] += 1;
            backtrack(n, info, idx+1);
            ryan[i] -= 1;
        }
    }
}
