package level.two.arrow;

import java.util.*;

public class ReArrow {
    public static void main(String[] args) {
        ReArrow re = new ReArrow();
        //System.out.println(Arrays.toString(re.solution(5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0})));
        //System.out.println(Arrays.toString(re.solution(3, new int[]{0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0})));
        //System.out.println(Arrays.toString(re.solution(1, new int[]{1,0,0,0,0,0,0,0,0,0,0})));
        System.out.println(Arrays.toString(re.solution(9, new int[]{0,0,1,2,0,1,1,1,1,1,1})));
    }
    public int[] solution(int n, int[] info) {
        Score score = new Score(n, info);
        for (int i = 0; i < info.length; i++) {
            findMaxScore(i, 0, score);
        }
        if(score.getMaxDiffScore() <= 0) {
            return new int[] {-1};
        }
        return score.getAnswer();
    }

    public void findMaxScore (int idx, int next, Score score) {
        // 재귀마다 새로운 라이언의 점수 배열 생성
        score.setRyan(new int[score.getApeachLength()]);
        // 화살 갯수
        int arrow = 0;
        int leng = score.getApeachLength();
        for (int i = idx; i < leng; i++) {
            // 화살 다 떨어지면 돌 필요 없음
            if(score.getN() <= arrow) {
                break;
            }
            if(next == i) {
                continue;
            }
            if(0 < score.getApeachIdx(i)) {
                int apcArrow = score.getApeachIdx(i) + 1;
                int tempCnt = arrow + apcArrow;
                if(tempCnt <= score.getN()) {
                    arrow = tempCnt;
                    score.setRyanIdx(i, apcArrow);
                }
                continue;
            }
            score.setRyanIdx(i, 1);
            ++arrow;
        }

        // 남아 있는 화살 있을 때 0점으로 쏘기
        if(arrow < score.getN()) {
            score.setRyanIdx(leng - 1,score.getN() - arrow);
        }
        int curDiff = score.getRyanScore() - score.getApeachScore();
        if(score.getMaxDiffScore() < curDiff) {
            score.setMaxDiffScore(curDiff);
            score.setAnswer(score.getRyan());
        } else if(score.getMaxDiffScore() == curDiff && 0 < score.getMaxDiffScore()) {
            for (int i = score.getRyan().length - 1; i >= 0; i--) {
                if(score.getAnswer()[i] < score.getRyanIdx(i)) {
                    score.setAnswer(score.getRyan());
                    break;
                } else if(score.getRyanIdx(i) < score.getAnswer()[i]) {
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(score.getRyan()) + " / " + score.getRyanScore() + " / " + score.getApeachScore() + " / " + curDiff + " / " + next);
        if(next < leng) {
            findMaxScore(idx, ++next, score);
        }
    }
}

class Score {
    private int n;
    private final int[] apeach;
    private int[] ryan;
    private final int[] scores;
    private int[] answer;
    private int maxDiffScore;

    public Score(int n, int[] apeach) {
        this.n = n;
        this.apeach = apeach;
        this.ryan = new int[this.apeach.length];
        this.scores = new int[] {10,9,8,7,6,5,4,3,2,1,0};
        this.answer = null;
        this.maxDiffScore = Integer.MIN_VALUE;
    }

    public int getApeachIdx(int idx) {
        return this.apeach[idx];
    }

    public int getApeachLength() {
        return this.apeach.length;
    }

    public int[] getRyan() {
        return this.ryan;
    }

    public void setRyan(int[] ryan) {
        this.ryan = ryan;
    }

    public void setRyanIdx(int idx, int arrow) {
        this.ryan[idx] = arrow;
    }

    public int getRyanIdx(int idx) {
        return this.ryan[idx];
    }

    public int[] getAnswer() {
        return answer;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getMaxDiffScore() {
        return maxDiffScore;
    }

    public void setMaxDiffScore(int diffScore) {
        this.maxDiffScore = diffScore;
    }
    public int getApeachScore() {
        int score = 0;
        for (int i = 0; i < this.apeach.length; i++) {
            if(0 < this.apeach[i]) {
                if(this.ryan[i] <= this.apeach[i]) {
                    score += this.scores[i];
                }
            }
        }
        return score;
    }

    public int getRyanScore() {
        int score = 0;
        for (int i = 0; i < this.ryan.length; i++) {
            if(this.apeach[i] < this.ryan[i]) {
                score += this.scores[i];
            }
        }
        return score;
    }

    public int getDiifScore (int[] ryan) {
        int apcScore = 0;
        int ryanScore = 0;
        for (int i = 0; i < ryan.length; i++) {
            if(0 < ryan[i]) {
                if(ryan[i] <= this.apeach[i]) {
                    apcScore += this.scores[i];
                } else if(this.apeach[i] < ryan[i]) {
                    ryanScore += this.scores[i];
                }
            }
        }
        return ryanScore - apcScore;
    }
}

