package level.etc.mission.menuhtml;

import java.util.LinkedHashMap;

public class MenuHtml {
    public static void main(String[] args) {
        MenuHtml m = new MenuHtml();
        System.out.println(m.solution(new String[][] {
                {"1", "메뉴1", null},
                {"2", "메뉴2", "1"},
                {"3", "메뉴3", "6"},
                {"4", "메뉴4", "6"},
                {"5", "메뉴5", "6"},
                {"6", "메뉴6", "2"},
                {"7", "메뉴7", "3"},
                {"8", "메뉴8", "7"},
                {"9", "메뉴9", "10"},
                {"10", "메뉴10", null},
                }));

        /*<ul>
          <li>
            <span>메뉴명</span>
            <ul>
              <li><span>메뉴명</span></li>
              <li><span>메뉴명</span></li>
              <li>
                <span>메뉴명</span>
                <ul>
                    <li><span>메뉴명</span></li>
                    <li><span>메뉴명</span></li>
                    <li><span>메뉴명</span></li>
                </ul>
            </li>
            </ul>
          </li>
        </ul>*/

    }

    public String solution (String[][] list) {

        StringBuilder sb = new StringBuilder();
        String blank = "  ";

        sb.append("<ul>\n");
        LinkedHashMap<String, String> map = findMenu(null, list);
        for (String key : map.keySet()) {
            String name = "메뉴" + key;
            sb.append(blank).append("<li>\n");
            sb.append(blank).append(blank).append("<span>").append(name).append("</span>\n");
            map = findMenu(key, list);
            sb.append(createChild(map, list, blank + blank));
            sb.append(blank).append("</ll>\n");
        }
        sb.append("</ul>");
        return sb.toString();
    }

    public LinkedHashMap<String, String> findMenu(String target, String[][] list) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        for (int i = 0; i < list.length; i++) {
            if(target == null) {
                if(list[i][2] == null) {
                    map.put(list[i][0], list[i][2]);
                }
            } else {
                if(list[i][2] != null) {
                    if(list[i][2].equals(target)) {
                        map.put(list[i][0], list[i][2]);
                    }
                }
            }
        }
        return map;
    }

    public String createChild (LinkedHashMap<String, String> map, String[][] list, String param) {

        LinkedHashMap<String, String> next = null;

        StringBuilder sb = new StringBuilder();
        String blank = "  ";

        if(0 < map.size()) {
            sb.append(param).append("<ul>\n");
            for (String key : map.keySet()) {
                String name = "메뉴" + key;
                sb.append(param).append(blank).append("<li>\n");
                sb.append(param).append(blank).append(blank).append("<span>").append(name).append("</span>\n");
                next = findMenu(key, list);
                if(next != null) {
                    sb.append(createChild(next, list, param + blank + blank));
                }
                sb.append(param).append(blank).append("</ll>\n");
            }
            sb.append(param).append("</ul>\n");
        }
        return sb.toString();
    }
}
