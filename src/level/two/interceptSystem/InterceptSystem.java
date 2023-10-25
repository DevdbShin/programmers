package level.two.interceptSystem;

import java.util.Arrays;

public class InterceptSystem {
    public static void main(String[] args) {
        InterceptSystem is = new InterceptSystem();
        System.out.println(is.solution(new int[][] {{4,5},{4,8},{10,14},{11,13},{5,12},{3,7},{1,4}}));
    }
    public int solution(int[][] targets) {
        int answer = 0;
        Arrays.sort(targets, (c, n) -> {
            return c[1] - n[1];
        });

        int end = -1;

        for(int[] target : targets){
            if(end == -1){
                answer++;
                end = target[1];
            }
            // 10 < 12 && 12 < 14
            if(target[0] < end && end <= target[1]) {
                continue;
            }
            answer++;
            end = target[1];
        }
        return answer;
    }
}