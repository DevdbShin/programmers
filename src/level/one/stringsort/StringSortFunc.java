package level.one.stringsort;

import java.util.Arrays;

public class StringSortFunc {
    public static void main(String[] args) {
        StringSortFunc sf = new StringSortFunc();
        System.out.println(Arrays.toString(sf.solution(new String[]{"sun", "san"}, 1)));
    }
    public String[] solution(String[] strings, int n) {
        Arrays.sort(strings, (s,t) -> {
            int result = s.charAt(n) - t.charAt(n);
            result = result == 0 ? s.compareTo(t) : result;
            return result;
        });
        return strings;
    }

    private int compare(String str1, String str2) {
        int leng = Math.max(str1.length(), str2.length());
        int result = 0;
        for (int i = 0; i < leng; i++) {
            result = str1.charAt(i) - str2.charAt(i);
            if(result != 0) {
                return result;
            }
        }
        return result;
    }
}
