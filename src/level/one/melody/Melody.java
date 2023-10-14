package level.one.melody;

public class Melody {
    public static void main(String[] args) {
        Melody m = new Melody();

        System.out.println(m.solution("ABCDEFG", new String[] {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
        System.out.println(m.solution("ABC", new String[] {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"}));

    }

    public String solution(String m, String[] musicinfos) {
        String answer = "";

        for (int i = 0; i < musicinfos.length; i++) {
            String[] info = musicinfos[i].split(",");

            // 음악의 길이를 구해야 한다.
            String music =  info[3];

            // 나열한 음악정보에 기억된 m의 음정이 들어가 있는지 체크한다...
            // 나열한 음악정보에 음정 ABC가 포함되어도 기본 음악정보에 ABC가 연속적으로 없으면 안된다.
            boolean flag = true;

            // 일단, m이 음악정보에 모두 포함되어 있는지 체크
            String[] mm = m.split("");
            int cnt = 0;
            for (int j = 0; j < mm.length; j++) {
                if(!music.contains(mm[j])) {
                    // 일단 최소한개가 일치하지 않는다는 뜻
                    flag = false;
                    break;
                }
            }

            // 포함되어 있지 않다면 두번째 검증 : 반대로 음악정보가 m에 포함되어있는지 체크
            if(!m.contains(music)) {
                if(!music.contains(m)) {
                    cnt = 2; // 음정안에 music이 포함되어 있지 않다는 뜻
                } else {
                    if(!flag) {
                        cnt = 2;
                    } else {
                        cnt = 1;
                    }
                }
            }

            // cnt가 2이상이면 완전 불일치
            if (cnt > 1) {
                answer = "(None)";
            } else {
                int musicLeng = music.length(); // 얘가 음악길이

                // 음악 재생시간을 구한다. 12:00 , 12:14
                int start = Integer.parseInt(info[0].replace(":", ""));
                int end = Integer.parseInt(info[1].replace(":", ""));

                // 재생시간 / 음악길이를 구한다.
                int playTime = end - start;

                // 길이만큼 재생된 음악의 음을 나열한다. CDEFGABCDEFGAB
                if(playTime > musicLeng) {
                    int x = playTime / musicLeng;
                    for (int j = 0; j < x; j++) {
                        music = music + music;
                    }
                }

                answer = info[2];
                break;
            }
        }
        return answer;
    }
}
