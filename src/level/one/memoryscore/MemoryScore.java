package level.one.memoryscore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MemoryScore {
    public static void main(String[] args) {
        MemoryScore ms = new MemoryScore();
        int[] answer = ms.solution(new String [] {"may", "kein", "kain", "radi"}
                    ,new int [] {5, 10, 1, 3}
                    ,new String[][] { {"may", "kein", "kain", "radi"}
                        ,{"may", "kein", "brin", "deny"}
                        ,{"kon", "kain", "may", "coni"} });

        System.out.println(Arrays.toString(answer));
        // [19, 15, 6]
    }
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int [photo.length];

        Map<String, Integer> memoryScore = new HashMap<>();

        int idx = 0;
        for (String nm : name) {
            memoryScore.put(nm, yearning[idx]);
            idx++;
        }

        idx = 0;
        int sum = 0;
        for (String[] nm : photo) {
            for (String str : nm) {
                sum += memoryScore.get(str) == null ? 0 : memoryScore.get(str);
            }
            answer[idx++] = sum;
            sum = 0;


        }

        return answer;
    }
}
