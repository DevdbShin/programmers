package level.two.menu;

import java.util.*;

public class MenuFixed {

    public static void main(String[] args) {
        MenuFixed mn = new MenuFixed();

        System.out.println(Arrays.toString(mn.solution(new String[]{"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"}, new int[]{2, 3, 5})));
    }

    public String[] solution(String[] orders, int[] course) {

        // 1) 배열안에 문자열들을 알파벳 순으로 정렬 : 노드 생성 시 알파벳 순서으로 트리구조를 만들기 위함
        for (int i = 0; i < orders.length; i++) {
            char[] charArr = orders[i].toCharArray();
            Arrays.sort(charArr);
            orders[i] = new String(charArr);
        }

        // 2) 배열안에 문자열 노드를 생성하고 트리구조를 만듦 :
        Node root = new Node("root", null, 0); // 노드의 root가 될 root 객체 생성
        for (String order : orders) {
            countOrder(root, order, 1);                     // 첫 시작은 최상위 노드가 될 root객체와 주문정보를 넘긴다.
        }

        // 3) 메뉴 조합을 생성 : 주문횟수가 2 이상인 메뉴의 조합을 생성한다.
        List<SortOrder> orderList = new ArrayList<>();
        arrangeOrder(root, orderList);

        // 4) 요구조건인 코스요리의 조합 수와 일치하는 메뉴조합을 필터링
        orderList = filterOrder(course, orderList);

        // 5) 필터링된 메뉴조합을 오름차순(메뉴명인 알파벳 기준)으로 정렬
        orderList.sort(Comparator.comparing(a -> a.order));

        // 5) 처리결과를 배열로 리턴해야 하기 때문에 List를 배열로 변환한다.
        String[] answer = new String[orderList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = orderList.get(i).order;
        }
        return answer;
    }

    // 요구된 코스요리 조합 수에 맞는 메뉴를 필터링 해야한다.
    private List<SortOrder> filterOrder (int[] course, List<SortOrder> orderList) {

        // 필터링된 메뉴를 저장할 List
        List<SortOrder> list = new ArrayList<>();

        // 요구된 코스요리 수 만큼 반복
        for (int cnt : course) {
            
            // 필터링된 메뉴 조합에서 가장많은 주문횟수를 가진 메뉴의 주문횟수를 저장하기 위한 변수
            int max = 0;

            /*
                필터링 1
                - 코스요리의 조합 수 (메뉴의 조합 수를 뜻함) == Tree의 깊이와 같다 : 깊이가 가장 깊을 수록 조건에 부합하는 가장 많은 메뉴 조합을 갖고 있기 때문 
                - 동시에 가장 많은 주문횟수를 가진 메뉴의 주문횟수를 max 변수에 저장
             */
            for (SortOrder sortOrder : orderList) {
                if (cnt == sortOrder.depth && max < sortOrder.count) {
                    max = sortOrder.count;
                }
            }

            /*
                필터링 2
                - 필터 1과 같은 코스요리의 조합 수 와 일치하는 조건이 필요
                - 동시에 max의 주문횟수와 동일한 메뉴 조합만 추출하여 List에 저장
             */
            for (SortOrder sortOrder : orderList) {
                if (cnt == sortOrder.depth && max == sortOrder.count) {
                    list.add(sortOrder);
                }
            }
        }
        return list;
    }

    // 주문 조합 생성 후 List에 저장
    private void arrangeOrder(Node parent, List<SortOrder> orderlist) {

        // root 노드 부터 시작하기 때문에 자식 노드를 순회한다.
        for (Node node : parent.child.values()) {

            // 주문횟수가 2이상인 메뉴들로 조합생성
            if(2 <= node.count) {
                Node curNode = node;
                String order = "";
                
                // 부모가 존재하지 않을때까지만 동작
                while (curNode.parent != null) {
                    order = curNode.menu + order;   // 현재노드의 메뉴(알파벳)명과 부모노드의 메뉴명을 조합 (알파벳순으로 조합됨)
                    curNode = curNode.parent;       // 메뉴조합이 끝나면 curNode에 부모노드를 대입하여 반복
                }

                // 재구성된 메뉴를 새로운 객체로 재생성 (메뉴명, 주문횟수, 노드깊이)
                orderlist.add(new SortOrder(order, node.count, node.depth));
            }

            // 모든 노드 탐색을 위해 재귀를 사용하여 반복 (현재 노드의 모든 자식을 탐색하기 위함)
            arrangeOrder(node, orderlist);
        }
    }

    // 주문의 메뉴(알파벳) 노드를 생성(기본 COUNT : 1)하고, 메뉴(알파벳) 노드가 이미 존재하면 COUNT를 올려서 주문횟수를 만든다.
    private void countOrder(Node parent, String order, int depth) {

        // 재귀호출 시 더이상 주문(order) 문자열이 존재하지 않을 때 방지하는 조건문 (아래 *** 설명 참조)
        if(order.length() > 0) {

            // 주문 문자열을 하나씩 자른 문자열 배열을 만듦
            String[] str = order.split("");

            for (int i = 0; i < str.length; i++) {
                Node curNode;

                // 해당 문자로 저장된 노드가 존재하면 count를 올리기 위해 노드객체를 불러옴
                if(parent.child.containsKey(str[i])) {
                    curNode = parent.child.get(str[i]);

                // 해당 문자로 저장된 노드가 존재하지 않으면 count를 올리기 위해 노드객체를 불러옴
                } else {
                    curNode = new Node(str[i], parent, depth); // 새로 생성한 노드
                    parent.child.put(str[i], curNode);         // 생성한 노드를 부모 노드의 자식으로 저장
                }

                // 신규일경우 count를 기본 1로 해주기 위함 : 그래야지 동일한 문자가 한번더 들어올 때 증가 되어 요구조건(주문횟수가 2 이상)을 달성 할 수 있음
                // 기존일경우 count를 증가 시킴
                ++curNode.count;

                /*
                    ***
                    문자열 길이만큼 반복해야 하기 때문에 
                    현재노드, 다음 문자열, 주문횟수, 노드깊이를 넘겨서 재귀시킴
                 */
                countOrder(curNode, order.substring(i+1), depth+1);
            }
        }
    }

    // 주문 정렬용 클래스
    public class SortOrder {
        public String order;
        public int count;
        public int depth;

        public SortOrder(String order, int count, int depth) {
            this.order = order;
            this.count = count;
            this.depth = depth;
        }

        public String toString() {
            String str = "/" + order + "/" + count + "/" + depth;
            return str;
        }
    }

    // 메뉴 노드 클래스
    public class Node {
        public String menu;
        public Node parent;
        public Map<String, Node> child;
        public int count;
        public int depth;
        public Node (String menu, Node parent, int depth) {
            this.menu = menu;
            this.parent = parent;
            this.child = new HashMap<>();
            this.count = 0;
            this.depth = depth;
        }
    }
}
