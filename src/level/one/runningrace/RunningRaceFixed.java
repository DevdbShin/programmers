package level.one.runningrace;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RunningRaceFixed {

    public static void main(String[] args) {
        Runningrace race = new Runningrace();
        race.solution(new String[] {"ppp", "nnn", "mmm", "ooo", "qqq"}, new String[] {"nnn", "ooo"});
    }
    public void solution(String[] players, String[] callings) {

        // 플레이어 수
        int playerLeng = players.length;

        // 랭크 해시맵
        Map<String, Integer> rank = new HashMap<>();

        // 맵에 플레이어 이름과 랭크를 저장(배열의 인덱스가 곧 랭크)
        for (int i = 0; i < playerLeng; i++) {
            rank.put(players[i], i);
        }


        /* 
          기존코드는 이중 for문을 사용한 이유가
          callings의 정보를 players에서 바로 비교하여 찾아서
          추월자와 선두자의 랭크를 갱신하기 위함 이었는데

          이중 for문을 피하려면
          해시맵에 기존 플레이어 정보를 복사하여 저장한 뒤
          추월자, 선두자의 정보를 갱신할 때
          players와 동일한 정보인 해시맵(rank)에
          callings의 플레이여명을 key로 하여 value인 랭크(인덱스) 방식을 활용해
          players 배열을 순회할 필요없이 players 정보 참조가 가능하다.
        */
        for (String player : callings) {
            //1) player의 이름에 해당하는 value를 저장한다.
            int ranking = rank.get(player);

            //2) player보다 앞에 있는 사람을 발견하여 저장
            String front = players[ranking - 1];

            //3) 해시맵에 key = 선두 플레이어명, value = 랭킹(인덱스)으로 변경
            rank.replace(front, ranking);

            //4) 플레이어 배열에서 추월한 플레이어의 랭킹(인덱스)으로 접근하여 해당 랭킹의 플레이명을 선두 플레이어명으로 변경해줌
            players[ranking] = front;

            //4) 마찬가지로 해시맵에 해당 플레이어 랭킹을 바로앞 선두와 교체 (인덱스 - 1)
            rank.replace(player, ranking - 1);

            //5) 플레이어 배열에서 선두자의 랭킹에는 해당 플레이어(추월한 플레이어)의 명으로 변경해줌
            players[ranking - 1] = player;
        }

        System.out.println(Arrays.toString(players));
    }
}
