package level.three;

public class Palindrome {
    public static void main(String[] args) {
        Palindrome p = new Palindrome();
        System.out.println(p.solution("abcdcba"));
        System.out.println(p.solution("abacde"));
    }

    public int solution(String s) {
        int answer = 0;
        char[] arr = s.toCharArray();
        palindrome : for (int i = s.length(); i > 2; i--) {
                        for (int j = 0; j + i <= s.length(); j++) {
                            boolean bool = true;
                            for (int k = 0; k < i / 2; k++) {
                                if(s.charAt(j + k) != s.charAt(i + j - k - 1)) {
                                    bool = false;
                                    break;
                                }
                            }
                            if(bool) {
                                answer = i;
                                break palindrome;
                            }
                        }
                    }
        return answer > 0 ? answer : 1;
    }
}
