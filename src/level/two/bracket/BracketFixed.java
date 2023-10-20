package level.two.bracket;

public class BracketFixed {

    public static void main(String[] args) {
        BracketFixed bf = new BracketFixed();
        // (()()) ()
        // ))(())
        System.out.println( bf.solution("))(())(("));
        //System.out.println( bf.solution(")("));
        //System.out.println( bf.solution(")(((())))("));
        //System.out.println(bf.solution("()))((()"));
        //System.out.println(bf.solution("()))()((()"));
    }

    public String solution(String p) {
        if (p.isEmpty()) {
            return "";
        }
        int open = 0;
        int close = 0;
        int i = 0;

        for (; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c == '(') {
                open++;
            } else {
                close++;
            }

            if (open == close) {
                break;
            }
        }

        String u = p.substring(0, i + 1);
        String v = p.substring(i + 1);

        if (isBalanced(u)) {
            return u + solution(v);
        } else {
            StringBuilder result = new StringBuilder();
            result.append('(');
            result.append(solution(v));
            result.append(')');
            u = u.substring(1, u.length() - 1);

            for (i = 0; i < u.length(); i++) {
                result.append(u.charAt(i) == '(' ? ')' : '(');
            }
            return result.toString();
        }
    }

    private boolean isBalanced(String str) {
        int balance = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') {
                balance++;
            } else {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }
}
