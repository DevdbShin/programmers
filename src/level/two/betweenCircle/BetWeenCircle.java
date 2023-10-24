package level.two.betweenCircle;

public class BetWeenCircle {
    public static void main(String[] args) {
        BetWeenCircle bc = new BetWeenCircle();
        System.out.println(bc.solution(2, 5));
    }
    public long solution(int r1, int r2) {
        int max = r2 - 1;
        int min = -r2 + 1;

        int xCnt = 1;
        int yCnt = 0;
        long xSum = 0L;
        long ySum = 0L;
        for (int i = r1; i < r2; i++) {
            for (int j = min; j < max; j++) {
                ++xCnt;
                if(max - 1 >= j && j != 0) {
                    ++yCnt;
                }
            }
            xSum = (xCnt * 2L) + xSum;
            ySum = (yCnt * 2L) + ySum;
            xCnt = 1;
            yCnt = 0;
            ++min;
            --max;
        }
        return xSum + ySum + 4L;
    }

    public long solution2(int r1, int r2) {
        long answer = 0;
        // 거듭제곱 구하기
        long ro1 = (long)Math.pow(r1,2);    // r1 == 2 : 4
        long ro2 = (long)Math.pow(r2,2);    // r2 == 5 : 25
        long ans = 0;

        for(long x = 0; x <= r2; x++) {
            long y2 = (long)Math.sqrt(ro2 - x*x);
            long y1 = (long)Math.sqrt(ro1 - x*x);
            if(Math.sqrt(ro1-x*x) % 1 == 0) ans++;

            answer += (y2-y1) * 4;
        }
        answer += ans*4;
        answer -= 4; //x == r2 y == 0 일때 x == 0, y == r2 겹치는값 제거
        return answer;
    }
}
