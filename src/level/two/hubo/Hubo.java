package level.two.hubo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Hubo {
    public static void main(String[] args) {
        Hubo hubo = new Hubo();
        System.out.println(hubo.solution(new String[][] {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}}));
    }

    public int solution(String[][] relation) {
        List<HashSet<Integer>> list = new ArrayList<>();

        // 릴레이션 정보를 하나씩 전달하여 후보키 체크
        for (int i = 0; i < relation.length; i++) {
            createHubo(i, i + 1, 0, relation.length, relation[0].length, new HashSet<>(), relation, list);
        }
        return list.size();
    }

    private void createHubo(int idx, int size, int depth, int length, int max, HashSet<Integer> set, String[][] relation, List<HashSet<Integer>> list) {

        // depth가 size랑 같아지면 끝까지 비교를 했다는 의미
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
            
            // 유일, 최소 통과하면 list에 저장하고 answer 증가
            list.add(set);
            return;
        }

        // 3) 유일하지 않다면 다음 속성과 묶어서 체크
        for (int i = idx; i < max; i++) {
            set.add(i);
            ++idx;
            // 재귀
            createHubo(idx, size, ++depth, length, max, set, relation, list);
        }
    }

    
    // 1) 주어진 속성별로 유일한지 체크
    private boolean checkHubo(int length, HashSet<Integer> set, String[][] relation) {
        List<String> list = new ArrayList<>();
        // 만들어진 조합으로 중복되는지 검사
        for (int i = 0; i < length; i++) {
            StringBuilder sb = new StringBuilder();

            // set에 저장된 인덱스로 존재하는 속성있으면 append
            for (int idx : set) {
                sb.append(relation[i][idx]);
            }

            // list에 해당 속성이 없으면 add
            if(!list.contains(sb.toString())) {
                list.add(sb.toString());

            // 있으면 이미 존재함으로 false 리턴
            } else {
                return false;
            }
        }
        return true;
    }
}
