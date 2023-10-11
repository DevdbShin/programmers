package level.one.parkwalk;

import java.util.Arrays;

public class ParkWalk {
    public static void main(String[] args) {

        ParkWalk park = new ParkWalk();

        //int [] park1 = park.solution(new String [] {"SOO","OOO","OOO"}, new String [] {"E 2","S 2","W 1"});
        //int [] park2 = park.solution(new String [] {"SOO","OXX","OOO"}, new String [] {"E 2","S 2","W 1"});
        int [] park3 = park.solution(new String [] {"OSO","OOO","OXO","OOO"}, new String [] {"E 2","S 3","W 1"});

        //System.out.println(Arrays.toString(park1));
       // System.out.println(Arrays.toString(park2));
        System.out.println(Arrays.toString(park3));
    }

    /*
    - S : 시작 지점
    - O : 이동 가능한 통로
    - X : 장애물

    - N : 북쪽으로 주어진 칸만큼 이동합니다.
    - S : 남쪽으로 주어진 칸만큼 이동합니다.
    - W : 서쪽으로 주어진 칸만큼 이동합니다.
    - E : 동쪽으로 주어진 칸만큼 이동합니다.
    */
    public int[] solution(String[] park, String[] routes) {
        int[] answer = new int [2];

        int idx = 0;
        for (String s : park) {
            boolean flag = true;

            // 장애물이 있으면 스킵
            if (s.contains("X")) {
                flag = false;
            }

            // 시작점 체크 (공원을 벗어나는지)
            if (s.contains("S")) {
                // 시작점에서 서쪽이랑 북쪽은 한칸이라도 가면 공원을 벗어남
                if (routes[idx].contains("N") || routes[idx].contains("W")) {
                    flag = false;
                }
                // S가 첫번째 위치가 아닌경우 초기 시작위치값 수정
                if (s.indexOf("S") != 0) {
                    answer[1] = s.indexOf("S");
                    continue;
                }
            }

            if (flag) {
                String str = routes[idx].substring(2);
                int num = Integer.parseInt(str);

                // 남
                if (routes[idx].contains("S")) {
                    if (answer[0] < 6) answer[0] += num;
                }

                // 북
                if (routes[idx].contains("N")) {
                    if (answer[0] > 0) answer[0] -= num;
                }

                // 동
                if (routes[idx].contains("E")) {
                    if (answer[1] < 7) answer[1] += num;
                }

                // 서
                if (routes[idx].contains("W")) {
                    if (answer[1] > 0) {
                        answer[1] -= num;
                    }
                }
            }
            idx++;
        }

        if(answer[0] == 7 && answer[1] == 6) {
            answer[0] = -1;
            answer[1] = -1;
        }

        return answer;
    }
}
