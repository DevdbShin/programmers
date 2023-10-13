package level.one.menu;

import java.util.*;

public class Menu {
    public static void main(String[] args) {
        Menu mn = new Menu();
        StringBuilder sb = new StringBuilder();

        System.out.println(Arrays.toString(mn.solution(new String[]{"XYZ", "XWY", "WXA"}, new int[]{2, 3, 4})));
    }
    public String[] solution(String[] orders, int[] course) {
        String[] clone = orders;

        // 주문 종류 중 2개 이상 주문된 것 == 코스 종류

        Map<String, Integer> order = new HashMap<>();

        // 먼저, 단품메뉴별 주문 횟수를 구함
        for (int i = 0; i < orders.length; i++) {
            for (int j = 0; j < orders.length; j++) {
                if(clone[j].contains(orders[i]) && !clone[j].equals(orders[i])) {
                    int cnt = order.get(orders[i]) == null ? 0 : order.get(orders[i]);
                    if(cnt > 0) {
                        order.replace(orders[i], cnt + 1);
                    } else {
                        order.put(orders[i], 1);
                    }
                }
            }
        }

        int idx = 0;
        String[] answer = new String[order.size()];
        // 코스 종류(갯수) 만큼 메뉴조합을 생성
        for(String str : order.keySet()) {
            answer[idx++] = str;
        }

        return answer;
    }
}

