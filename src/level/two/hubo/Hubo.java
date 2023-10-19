package level.two.hubo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Hubo {
    public static void main(String[] args) {

    }
    private static List<HashSet<Integer>> list;
    private int answer;

    public int solution(String[][] relation) {
        int answer = 0;

        for (int i = 0; i < relation.length; i++) {
            createHubo(i, i+1, 0, relation.length, relation[0].length, new HashSet<>(), relation);
        }

        //  - 유일하다면 후보키로 저장
        //  - 유일하지 않다면 1번부터 재실행


        return answer;
    }

    private void createHubo(int idx, int size, int depth, int length, int max, HashSet<Integer> set, String[][] relation) {
        if(depth == size) {
            // 1) 주어진 속성별로 유일한지 체크
            if(!checkHubo(length, set, relation)) {
                return;
            }

            // 최소성 검사
            for (HashSet<Integer> key : list) {
                if(set.containsAll(key)) {
                    return;
                }
            }
            
            // 유일, 최소 통과하면 answer 증가
            list.add(set);
            answer++;
            return;
        }

        // 3) 유일하지 않다면 다음 속성과 묶어서 체크
        for (int i = idx; i < max; i++) {
            HashSet<Integer> newSet = new HashSet<>(set);
            newSet.add(i);
            idx++;
            
            // 재귀
            createHubo(idx, size, depth + 1, length, max, newSet, relation);
        }
    }

    
    // 1) 주어진 속성별로 유일한지 체크
    private boolean checkHubo(int length, HashSet<Integer> set, String[][] relation) {
        List<String> list = new ArrayList<>();
        // 만들어진 조합으로 중복되는지 검사
        for (int i = 0; i < length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int idx : set) {
                sb.append(relation[i][idx]);
            }
            if(!list.contains(sb.toString())) {
                list.add(sb.toString());
            } else {
                return false;
            }
        }
        return true;
    }
}
