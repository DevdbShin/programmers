package level.two.covid;

public class Covid {

    public static void main(String[] args) {
        Covid corona = new Covid();
        int [] tmp = corona.solution(new String[][] {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}
                , {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}
                , {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}
                , {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}
                , {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
                });
        for (int i = 0; i < tmp.length; i++) {
            System.out.println(tmp[i]);
        }
    }

    public String pre = "";

    public int[] solution(String[][] places) {
        int[] answer = {};

//        5 x 5
//
//        p = 응시자가 앉아있는 거리
//        o = 빈 테이블
//                x = 파티션
//
//        10001
//        0xx0x
//        01x1x
//        00x0x
//        10xx1
        // 1을 만났으면 좌/우 상/하 2를 확인

        // 2를 만났으면 x가 포함되어있는지 확인

        int [] result = new int [places.length];

        boolean bool = true;

        for (int i = 0; i < places.length; i++) {
            for (int j = 0; j < places[i].length; j++) {
                String tmp = places[i][j];

                if(j == 0) {
                    pre = tmp;
                }

                // 동일한 라인에 1 1이 붙어있는지 확인
                if(!checkLine(tmp)) {
                    bool = false;
                    break;
                }

                // 안붙어 있으면 다음 로직 진행 : 다음라인 비교 하기
                int num = j + 1 < places[i].length ? j + 1 : j;
                bool = compareLine(tmp, places[i][num], pre, j);

                if(!bool) {
                    break;
                }
            }
            if(bool) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }
        }
        return result;
    }

    // 다음 라인 확인 : 같은 위치에 p == 1, X == 2, o == 0
    public boolean compareLine(String line1, String line2, String before, int num) {
        for (int i = 0; i < line1.length(); i++) {
            if(line1.charAt(i) == 'P') {
                if(line2.charAt(i) == 'P' && num != 4) {
                    //System.out.println(line1 + " / " + line2 + " / " + pre + " / " + num);
                    return false;
                }
                if(before.charAt(i) == 'P' && num >= 1) {
                    if(line2.charAt(i) != 'X') {
                        //System.out.println(line1 + " / " + line2 + " / " + pre + " / " + num);
                        return false;
                    }
                }
            }
        }

        if(num >= 1) {
            pre = line1;
        }
        return true;
    }

    // 현재 라인 검증 : PXPXP 일 때 잘못됨
    public boolean checkLine(String str) {
        // 같은 라인에 1 1이 붙어있는지 확인
        for (int k = 0; k < str.length(); k++) {
            if(str.charAt(k) == 'P') {
                int tmp = k + 2 < str.length() ? k + 2 : 0;

                if(str.charAt(tmp) == 'P' && tmp != 0) {
                    if(str.charAt(k+1) != 'X' && (k + 1) < str.length()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
