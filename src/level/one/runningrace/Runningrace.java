package level.one.runningrace;

import java.util.Arrays;

public class Runningrace {

    public static void main(String[] args) {
        Runningrace race = new Runningrace();
        race.solution(new String[] {"ppp", "nnn", "mmm", "ooo", "qqq"}, new String[] {"nnn", "ooo"});
    }
    public void solution(String[] players, String[] callings) {

        int idx = 0;
        // callings 수 만큼 반복
        for (String calling : callings) {
            for (int j = 0; j < players.length; j++) {
                // players와 동일하면 해당 플레이어의 인덱스를 임시저장
                if (calling.equals(players[j])) {
                    idx = j;
                }
            }

            // 임시 변수에 플레이어의 이전 인덱스의 값을 저장(즉, 선두 플레이어를 저장)
            String tmp = players[idx - 1];

            // 선두 플레이어 인덱스에 추월한 플레이어로 교체(이때 위에서 찾은 플레이어의 인덱스를 활용)
            players[idx - 1] = players[idx];

            // 추월한 플레이어의 기존 인덱스에는 추월당한(선두였던) 플레이어로 교체
            players[idx] = tmp;
        }

        System.out.println(Arrays.toString(players));
        // 출력 결과
        // [nnn, ppp, ooo, mmm, qqq]
    }
}
