package level.one.reportresults;

import java.util.*;

public class ReportResults {
    public static void main(String[] args) {
        ReportResults rr = new ReportResults();

        System.out.println(Arrays.toString(rr.solution(new String[]{"muzi", "frodo", "apeach", "neo"}
                                                        , new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"}
                                                        , 2)));
    }

    public int[] solution(String[] id_list, String[] report, int k) {

        // 중복제거
        report =  Arrays.stream(report).distinct().toArray(String[]::new);

        Map<String, Integer> reports = new HashMap<>();

        int[] answer = new int[id_list.length];

        // 신고횟수 저장하기
        for (String str : report) {
            String[] split = str.split(" ");

            int val = reports.get(split[1]) == null ? 0 : reports.get(split[1]);
            if(val > 0) {
                reports.replace(split[1], val + 1);
            } else {
                reports.put(split[1], 1);
            }
        }

        for (int i = 0; i < id_list.length; i++) {
            for (String str : report) {
                String[] split = str.split(" ");
                if(id_list[i].equals(split[0])) {
                    int cnt = reports.get(split[1]);
                    if(cnt >= k) {
                        answer[i] += 1;
                    }
                }
            }
        }
        return answer;
    }
}
