package level.two.arrow;

import java.util.*;

public class ReArrow {
    public static void main(String[] args) {
        ReArrow re = new ReArrow();
        System.out.println(Arrays.toString(re.solution(5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0})));
        //System.out.println(Arrays.toString(re.solution(3, new int[]{0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0})));
        //System.out.println(Arrays.toString(re.solution(0, new int[]{0,0,0,0,0,0,0,0,0,0,0})));
        //System.out.println(Arrays.toString(re.solution(9, new int[]{0,0,1,2,0,1,1,1,1,1,1})));
    }
    public int[] solution(int n, int[] info) {
        Score score = new Score(n, info);
        for (int i = 0; i < info.length; i++) {
            findMaxScore(i, i+1, n, score);
        }
        if(score.getDiffScore() == 0) {
            return new int[] {-1};
        }
        return score.getBest();
    }

    public void findMaxScore (int idx, int next, int n, Score score) {
        // 재귀마다 새로운 라이언의 점수 배열 생성
        score.setRyan(new int[score.getApeachLength()]);
        // 화살 갯수
        int arrow = 0;
        int leng = score.getApeachLength();
        for (int i = idx; i < leng; i++) {
            // 화살 다 떨어지면 돌 필요 없음
            if(n <= arrow) {
                break;
            }
            if(i == next) {
                continue;
            }
            if(0 < score.getApeachIdx(i)) {
                int apcArrow = score.getApeachIdx(i) + 1;
                int tempCnt = arrow + apcArrow;
                if(tempCnt <= n) {
                    arrow = tempCnt;
                    score.setRyanIdx(i, apcArrow);
                }
                continue;
            }
            score.setRyanIdx(i, 1);
            ++arrow;
        }
        if(arrow < n) {
            score.setRyanIdx(leng - 1, n - arrow);
        }
        int diff = score.getRyanScore() - score.getApeachScore();
        if(score.getDiffScore() < diff) {
            score.setBest(score.getRyan());
            score.setDiffScore(diff);
            score.setBestScore(score.getRyanScore());
        } else if(score.getDiffScore() == diff && 0 < score.getDiffScore()) {
            for (int i = score.getRyan().length - 1; i >= 0; i--) {
                if(score.getBest()[i] < score.getRyanIdx(i)) {
                    score.setBest(score.getRyan());
                    score.setBestScore(score.getRyanScore());
                    break;
                } else if(score.getRyanIdx(i) < score.getBest()[i]) {
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(score.getRyan()) + " / " + score.getRyanScore() + " / " + score.getApeachScore() + " / " + diff + " / " + next);
        if(next < leng) {
            findMaxScore(idx, ++next, n, score);
        }
    }
}

class Score {
    private int n;
    private final int[] apeach;
    private int[] ryan;
    private final int[] scores;
    private int[] best;
    private int diffScore;
    private int bestScore;

    public Score(int n, int[] apeach) {
        this.n = n;
        this.apeach = apeach;
        this.scores = new int[] {10,9,8,7,6,5,4,3,2,1,0};
        this.best = null;
        this.diffScore = 0;
        this.bestScore = 0;
        this.ryan = new int[this.apeach.length];
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

    public int[] getBest() {
        return best;
    }

    public void setBest(int[] best) {
        this.best = best;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getDiffScore() {
        return diffScore;
    }

    public void setDiffScore(int diffScore) {
        this.diffScore = diffScore;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
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
}

