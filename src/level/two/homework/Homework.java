package level.two.homework;

import java.util.Arrays;

public class Homework {
    public static void main(String[] args) {
        Homework hw = new Homework();
        System.out.println(Arrays.toString(hw.solution(new String[][]{{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}})));

    }
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];

        // 1) 시작시간이 가장 빠른 순으로 정렬
        Arrays.sort(plans, (c, n) -> {
            String[] curSplit = c[1].split(":");
            String[] nextSplit = n[1].split(":");

            int cur = Integer.parseInt(curSplit[0]) * 60 + Integer.parseInt(curSplit[1]);
            int next = Integer.parseInt(nextSplit[0]) * 60 + Integer.parseInt(nextSplit[1]);

            int result = cur - next;

            if( result == 0) {
                return c[1].compareTo(n[1]);
            }  else {
                return result;
            }
        });

        int [] stop = new int[plans.length];

        int idx = 0;

        for (int i = 0; i < plans.length; i++) {
            if(i + 1 < plans.length) {
                String[] timeSplit = plans[i][1].split(":");
                int curTime = Integer.parseInt(timeSplit[0]) * 60 + Integer.parseInt(timeSplit[1]);

                timeSplit = plans[i + 1][1].split(":");
                int nextTime = Integer.parseInt(timeSplit[0]) * 60 + Integer.parseInt(timeSplit[1]);

                int play = curTime + Integer.parseInt(plans[i][2]);

                // 1-1) 다음시작시간보다 크면 stop에 저장
                if(play > nextTime) {
                    stop[i] = play - nextTime;
                // 1-2) 없으면 해당 과제를 저장
                } else {
                    stop[i] = 0;
                    answer[idx++] = plans[i][0];

                    // 1-3) 끝나는 시간이랑 다음 시작시간 사이 텀이 있으면 멈춰놨던거 플레이
                    int time = nextTime - play;
                    if(0 < time) {
                        for (int j = stop.length - 1; j >= 0; j--) {
                            if(stop[j] != 0) {
                                int diff = time - stop[j];

                                // diff가 음수이면 남은 시간 텀 시간보다 크다는 것
                                if(diff < 0) {
                                    stop[j] = Math.abs(diff);   // 시간 텀 동안 플레이한만큼 빼주고 다시 저장
                                    break;                      // 남은시간을 다 쓴거랑 같기 때문에 더 돌필요 없음
                                } else {
                                    answer[idx++] = plans[j][0];    // diff가 0이거나 0보다 크면 다 플레이한 것 임으로 완료에 저장
                                    stop[j] = 0;                    // stop 에도 다음 체크 때 걸러지기 위해 0으로 저장
                                    if(diff == 0) {                 // diff가 0이면 더 돌필요 없음
                                        break;
                                    } else {                        // diff가 0보다 크면 다음꺼 체크해야 함 : 단, ime을 diff만큼 빼야 함
                                        time = diff;
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                stop[i] = 0;
                answer[idx++] = plans[i][0];
            }
        }

        // 멈춰둔게 있으면 역순으로 저장
        for (int i = stop.length - 1; i >= 0; i--) {
            if (stop[i] != 0) {
                answer[idx++] = plans[i][0];
            }
        }

        return answer;
    }
}
