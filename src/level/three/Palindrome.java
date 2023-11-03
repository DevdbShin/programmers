package level.three;

public class Palindrome {
    public static void main(String[] args) {
        Palindrome p = new Palindrome();
        System.out.println(p.solution("abcdcba"));
        System.out.println(p.solution("abacde"));
    }

    public static int loop = 0;
    public static boolean bool = true;

    public int solution(String s) {
        int answer = 0;

        /*char[] reverse = new char[s.length()];

        int idx = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            reverse[idx++] = s.charAt(i);
        }

        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == reverse[i]) {
                ++answer;
            }
        }

        if(answer == 0) {
            int mem = 0;
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                for (int j = i + 1; j < s.length() - 1; j++) {
                    if(reverse[j] == s.charAt(i)) {
                        mem = j;
                        break;
                    }
                    mem = 0;
                }
                if(mem > 0) {
                    ++count;
                }
            }

            if(count > 0 ) {
                answer = count;
            }
        }
*/
        int mid = s.length() / 2;
        boolean mp = s.length() % 2 == 0;
        answer = binarySearch(mp, mid, s.length()-1, s);
        return answer > 0 ? answer : 1;
    }


    public int binarySearch(boolean mp, int mid, int end, String s) {
        ++loop;
        if(mid <= 0) {
            return 0;
        }
        int idx = end;
        int count = 0;

        for (int i = 0; i < mid; i++) {
            if(s.charAt(i) != s.charAt(idx--)) {
                count = 0;
                mp = mid % 2 == 0;
                end = mid - 1;
                mid = mid / 2;
                count += binarySearch(mp, mid, end, s);

                if(0 < count) {
                    count = mp ? count * 2 : (count * 2) + 1;
                }
                bool = false;
                break;
            }
            ++count;
        }
        return count;
    }
}
