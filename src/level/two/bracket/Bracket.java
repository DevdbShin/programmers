package level.two.bracket;

public class Bracket {
    public static void main(String[] args) {

        Bracket bc = new Bracket();
        System.out.println( bc.solution(")()("));
    }
    public String solution(String p) {
        BracketInfo bracket = new BracketInfo(p);
        bracket.sortBracket(bracket.w);
        return bracket.r;
    }
}
class BracketInfo {
    public String w = "";   // input
    public String r = "";   // ouput
    private String u = "";   // 균형잡힌 괄호 ()의 개수가 맞아야함
    private String v = "";   // 나머지 문자열을 담을 변수

    public BracketInfo(String w) {
        this.w = w;
    }

    // 문자열 w를 u, v로 분리
    public void sortBracket(String param) {

        int left = 0;
        int right = 0;
        if(param.length() > 0 && this.r.length() < this.w.length()) {
            String tmp = "";
            String[] str = param.split("");

            for (int i = 0; i < str.length; i++) {
                tmp += str[i];
                if(str[i].charAt(0) == '(') {
                    ++left;
                } else {
                    ++right;
                }
                if(left == right && i != 0) {
                    this.u = tmp;
                    if(this.u.charAt(0) == ')') {
                        this.u = this.balance();
                    }
                    this.r += this.u;
                    if(i + 1 < param.length()) {
                        this.v = param.substring(i + 1);
                        sortBracket(this.v);
                    }
                    break;
                }
            }
        }
    }

    // 온전한 괄호 만들기
    private String balance () {

        String[] str = this.u.split("");
        String result = "";

        if(str.length > 2) {
            str[0] = str[str.length - 1];
            // 순서 뒤집기
            result = str[0];
            for (int i = 1; i < str.length; i++) {
                if (str[i].charAt(0) == '(') {
                    result += ")";
                } else {
                    result += "(";
                }
            }
        } else {
            result = str[1] + str[0];
        }
        return result;
    }
}
