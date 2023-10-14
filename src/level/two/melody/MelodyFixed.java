package level.two.melody;

import java.util.ArrayList;
import java.util.List;

public class MelodyFixed {

    public static void main(String[] args) throws Exception {
        String[] inputArray = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        String melody = "ABC#";

        MelodyFixed mf = new MelodyFixed();
        //System.out.println(mf.solution("ABCDEFG", new String[] {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
        //System.out.println(mf.solution("CCB", new String[] {"13:00,13:10,FOO,CCB#CCB"}));
        //System.out.println(mf.solution("CDCDE", new String[] {"12:00,12:06,HELLO,CDCDCDE"}));
        //System.out.println(mf.solution("A#", new String[] {"13:00,13:01,BAR,A#"}));
        System.out.println(mf.solution("A#A#A#", new String[] {"07:10,07:17,FOO,AA#A#A#"}));
    }

    public String solution (String m, String[] musicinfos) {

        String answer = "";

        List<MusicInfo> musicInfoList = new ArrayList<>();
        List<MusicInfo> resultList = new ArrayList<>();

        for(int i=0;i<musicinfos.length;++i) {
            String[] input = musicinfos[i].split(",");
            MusicInfo musicInfo = new MusicInfo(input[0], input[1], input[2], input[3], i);
            musicInfoList.add(musicInfo);
        }

        for(MusicInfo musicInfo : musicInfoList) {
            if(musicInfo.findMelody(m)) {
                resultList.add(musicInfo);
            }
        }

        if(0 < resultList.size()) {
            resultList.sort((a,b)->{
                int result = b.getDuringTime()-a.getDuringTime();

                if(result==0) {
                    //return a.startTime.compareTo(b.startTime);
                    return a.order - b.order;
                } else {
                    return result;
                }
            });

           answer = resultList.get(0).title;
        } else {
            answer = "(None)";
        }

        System.out.println();

        return answer;
    }
}

class MusicInfo {
    String startTime;
    String endTime;
    String title;
    String melody;
    int order;

    public MusicInfo(String startTime, String endTime, String title, String melody, int order) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
        this.melody = melody;
        this.order = order;
    }

    public int getDuringTime() {
        String[] startTimeArray = this.startTime.split(":");
        String[] endTimeArray = this.endTime.split(":");

        int start = Integer.parseInt(startTimeArray[0]) * 60 + Integer.parseInt(startTimeArray[1]);
        int end = Integer.parseInt(endTimeArray[0]) * 60 + Integer.parseInt(endTimeArray[1]);

        return end - start;
    }

    public boolean findMelody(String melody) {
        int duringTime = this.getDuringTime();
        int mp=0;

        for(int i = 0, p = 0, r = 0; i < duringTime && r < duringTime + 1; ++i, p = (p + 1) % this.melody.length()) {
            if(this.melody.charAt(p) == melody.charAt(mp)) {
                ++mp;
                if(this.melody.charAt(p) == '#') {
                    --i;
                }
                if(mp == melody.length()) {
                    if(this.melody.charAt((p + 1) % this.melody.length())!='#') {
                        return true;
                    } else {
                        mp = 0;
                        i = p;
                        p = r;
                        ++r;
                    }
                }
            } else {
                if(0 < mp || 0 < r) {
                    i = 0;
                    p = r;
                    ++r;
                }
                mp = 0;
            }
        }
        return false;
    }
}