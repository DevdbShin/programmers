package level.two.stringZip;

import java.util.ArrayList;
import java.util.Map;

public class StringZip {
    public static void main(String[] args) {
        StringZip zip = new StringZip();
        //zip.solution("aabbaccc");
        zip.solution("ababcdcdababcdcd");
    }

    public void solution(String s) {
        int answer = 0;

        String[] result = new String[s.length()];

        for (int i = 1; i < s.length(); i++) {
            result[i] = nextStep(i, s);
        }

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }

       
        // return answer;
    } 

    public String[] replace(String str, String target, int idx) {

        int count = 0;

        // 비교 문자가 1개 문자일 때
        if(target.length() < 2) {
            for (int i = idx; i < str.length(); i++) {
                if(str.charAt(i) == target.charAt(0)) {
                    ++count;
                } else {
                    break;
                }
            }

            if(1 < count) {
                return new String[] {String.valueOf(count), count + target};
            }
        
        // 2개 이상 문자일 때    
        } else {
            for (int i = idx, p = 0; i < str.length(); i++) {
                if(str.charAt(i) == target.charAt(p)) {
                    ++p;
                    if(p == target.length()) {
                        ++count;
                        p = 0;
                    }
                }
            }

            if(1 < count) {
                return new String[] {String.valueOf(count), count + target};
            } 
        }

        return null;
    }


    public String nextStep (int idx, String s) {
        
        String str = "";
        String result = "";

        for (int i = 0; i < s.length(); i++) {

            str = String.valueOf(s.charAt(i));

            String [] tmp = replace(s, str, i);

            if(tmp != null) {
                result += tmp[1];
                i += Integer.parseInt(tmp[0]) - 1;
            } else {
                result += str;
            }
        }
        System.out.println(result);
        return result;
    }

}
