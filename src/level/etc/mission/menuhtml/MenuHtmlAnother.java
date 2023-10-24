package level.etc.mission.menuhtml;

import java.util.*;

public class MenuHtmlAnother {

    public static void main(String[] args) throws Exception {
        MenuHtmlAnother solution = new MenuHtmlAnother();
        String result = solution.solution(new String[][]{
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
        });

        System.out.println(result);
    }

    public String solution(String[][] menuInfos) {
        Menu root = createMenuTree(menuInfos);
        String answer = createMenuHtml(root);
        return answer;
    }

    private Menu createMenuTree(String[][] menuInfos) {
        Menu root = new Menu("#", "root", null);
        Map<String, Menu> menuMap = new HashMap<>();

        for(String[] menuInfo : menuInfos) {
            menuMap.put(menuInfo[0], new Menu(menuInfo[0], menuInfo[1], menuInfo[2]));
        }

        for(Menu menu : menuMap.values()) {
            if(menu.parentId==null) {
                root.addChild(menu);
            } else {
                menuMap.get(menu.parentId).addChild(menu);
            }
        }

        return root;
    }

    private String createMenuHtml(Menu root) {
        String result = "";

        result += "<ul>\n";

        for(Menu child : root.children) {
            result += recursiveMenuHtml(child, "  ");
        }

        result += "</ul>\n";

        return result;
    }

    private String recursiveMenuHtml(Menu parent, String spaceString) {
        String menuHtml = "";

        menuHtml += spaceString + "<li>\n";
        menuHtml += spaceString + "  <span>" + parent.name + "</span>\n";

        if(0<parent.children.size()) {
            menuHtml += spaceString + "  <ul>\n";

            for(Menu child : parent.children) {
                menuHtml += recursiveMenuHtml(child, spaceString + "    ");
            }

            menuHtml += spaceString + "  </ul>\n";
        }

        menuHtml += spaceString + "</li>\n";

        return menuHtml;
    }
}

class Menu {
    public String id;
    public String name;
    public String parentId;
    public List<Menu> children;
    public Menu parent;

    public Menu(String id, String name, String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.children = new ArrayList<>();
    }

    public void addChild(Menu menu) {
        this.children.add(menu);
        menu.parent = this;
        menu.parentId = this.id;
    }
}
