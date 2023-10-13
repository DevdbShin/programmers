package level.one.reportresults;

import java.util.*;
import java.util.stream.Collectors;

public class ReportResults {
    public static void main(String[] args) {
        ReportResults rr = new ReportResults();

        System.out.println(Arrays.toString(rr.solution(new String[]{"muzi", "frodo", "apeach", "neo"}
                                                        , new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"}
                                                        , 2)));
    }

    public int[] solution(String[] id_list, String[] report, int k) {

        // 중복제거
        List<String> newList = Arrays.stream(report).distinct().collect(Collectors.toList());

        Map<String, Integer> reports = new HashMap<>();
        int[] answer = new int[id_list.length];

        // 신고횟수 저장하기
        for (String str : newList) {
            String[] split = str.split(" ");
            if(reports.containsKey(split[1])) {
                reports.put(split[1], reports.get(split[1]) + 1);
            } else {
                reports.put(split[1], 1);
            }
        }

        return Arrays.stream(id_list).map(ids -> {
            final String id = ids;
            List<String> result = newList.stream().filter(s -> s.split(" ")[0].equals(id)).collect(Collectors.toList());
            return result.stream().filter(s -> reports.get(s.split(" ")[1]) >= k).count();
        }).mapToInt(Long::intValue).toArray();
    }
}
