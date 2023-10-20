package level.two.mineral;

import java.util.*;

public class Mineral {
    public static void main(String[] args) {
        Mineral m = new Mineral();
        System.out.println(m.solution(new int[] {1, 3, 2}, new String [] {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"}));
        System.out.println(m.solution(new int[] {0, 1, 1}, new String [] {"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"}));
    }

    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int sum = 0;

        for (int i = 0; i < picks.length; i++) {
            sum += picks[i];
        }

        // 한번돌 때 5개씩 짤라서 봐야함
        for(int i = 0; i < minerals.length;) {
            Minerals mineral = runDig(minerals, i);
            answer += selectPick(picks, mineral);
            --sum;
            // 곡괭이 다쓰면 멈춰야 됨
            if(sum == 0) {
                break;
            }
            i = i + 5;
        }
        return answer;
    }

    // 광물 발견할 때 마다 곡괭이별로 피로도 미리 누적
    public Minerals runDig(String[] mineral, int idx) {
        int[][] table = new int[][]{{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};

        Minerals minerals = new Minerals(0,0,0);
        for(int i = idx; i < idx + 5; i++) {
            if(i == mineral.length) {
                break;
            }
            // 광물에 따라 table에 인덱스 설정 -> 열 인덱스
            int col = 0;
            if(mineral[i].equals("diamond")) {
                col = 0;
            } else if(mineral[i].equals("iron")) {
                col = 1;
            } else {
                col = 2;
            }

            // 곡괭이별 위치에 / 현재 광물 위치로 피로도 총 누적 : 5개씩
            // 1 roop 5/17/80  ... 1/5/25   2/10/50 ...
            // 2 roop 3/7/31
            //System.out.println(table[0][col] + "/" + table[1][col] + "/" + table[2][col]);

            minerals.setDiamond(table[0][col]);
            minerals.setIron(table[1][col]);
            minerals.setStone(table[2][col]);
        }
        return minerals;
    }

    // 곡괭이가 있으면 곡괭이 소진 후 피로도 리턴
    public int selectPick(int[] picks, Minerals mineral) {
        if(picks[0] > 0) {
            --picks[0];
            return mineral.getDiamond();
        }
        if(picks[1] > 0) {
            --picks[1];
            return mineral.getIron();
        }
        if(picks[2] > 0) {
            --picks[2];
            return mineral.getStone();
        }
        return 0;
    }
}

class Minerals {
    private int diamond;
    private int iron;
    private int stone;

    Minerals(int diamond, int iron, int stone) {
        this.diamond = diamond;
        this.iron = iron;
        this.stone = stone;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond += diamond;
    }

    public int getIron() {
        return iron;
    }

    public void setIron(int iron) {
        this.iron += iron;
    }

    public int getStone() {
        return stone;
    }

    public void setStone(int stone) {
        this.stone += stone;
    }

    @Override
    public String toString() {
        return "Minerals{" +
                "diamond=" + diamond +
                ", iron=" + iron +
                ", stone=" + stone +
                '}';
    }
}
