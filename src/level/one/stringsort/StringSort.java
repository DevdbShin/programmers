package level.one.stringsort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringSort {
    public static void main(String[] args) {
        StringSort ss = new StringSort();
        System.out.println(Arrays.toString(ss.solution(new String[]{"sun", "san"}, 1)));
    }

    public String[] solution(String[] strings, int n) {

        for (int i = 0; i < strings.length-1; i++) {
            for (int j = i + 1; j < strings.length; j++) {
                int result = strings[i].charAt(n) - strings[j].charAt(n);
                result = result == 0 ? compare(strings[i], strings[j]) : result;
                String tmp;
                if(result > 0) {
                    tmp = strings[j];
                    strings[j] = strings[i];
                    strings[i] = tmp;
                }
            }
        }
        return strings;
    }

    private int compare(String str1, String str2) {
        int leng = str1.length() > str2.length() ? str1.length() : str2.length();
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
