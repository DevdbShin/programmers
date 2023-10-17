package level.two.bracket;

import java.util.Arrays;

public class Bracket {
    public static void main(String[] args) {

        Bracket bc = new Bracket();
        System.out.println( bc.solution("))(())(("));
        System.out.println( bc.solution(")("));
        System.out.println( bc.solution(")(((())))("));
        System.out.println(bc.solution("()))((()"));

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

                if(i != 0) {
                    if(left == right && left == 1) {
                        // 일반 케이스
                        this.u = tmp;
                        if(this.u.charAt(0) == ')') {
                            this.u = this.balance(this.u);
                        }
                        this.r += this.u;
                        if(i + 1 < param.length()) {
                            this.v = param.substring(i + 1);
                            sortBracket(this.v);
                        }
                    } else {
                        // 방향만 바꿔주면 되는 케이스
                        this.r = this.replaceArrow();
                    }
                    break;
                }
            }
        }
    }

    // 온전한 괄호 만들기
    private String balance (String param) {

        String[] str = param.split("");
        String result = "";

        // 순서 뒤집기
        result = str[str.length - 1];

        boolean bool = false;
        for (int i = 1; i < str.length - 1; i++) {
            if(i == 1 && str[i].charAt(0) == '(') {
                bool = true;
            }

            if(bool) {
                result += str[i];
            } else {
                if (str[i].charAt(0) == '(') {
                    result += ")";
                } else {
                    result += "(";
                }
            }
        }
        result += str[0];
        return result;
    }

    private String replaceArrow() {

        char[] arr = this.w.toCharArray();

        char right = this.w.charAt(arr.length / 2);
        char left = this.w.charAt( (arr.length / 2) - 1);

        for (int i = arr.length / 2, j = i - 1; i < arr.length && j >= 0 ; ++i, --j) {

            if(arr[i] != right) {
                arr[i] = right;
            }

            if(arr[j] != left) {
                arr[j] = left;
            }
        }
       return String.valueOf(arr);
    }
}
