package level.two.arrow;

public class FireArrow {
    public static void main(String[] args) {

    }

    public void findMaxScore(int idx, int next, int n, Score score) {
        if (idx == score.getApeachLength()) {
            // 종료 조건: 모든 과녁을 고려했을 때
            int diff = score.getRyanScore() - score.getApeachScore();
            if (score.getDiffScore() < diff) {
                score.setBest(score.getRyan());
                score.setDiffScore(diff);
                score.setBestScore(score.getRyanScore());
            } else if (score.getDiffScore() == diff) {
                for (int i = score.getRyan().length - 1; i >= 0; i--) {
                    if (score.getBest()[i] < score.getRyanIdx(i)) {
                        score.setBest(score.getRyan());
                        score.setBestScore(score.getRyanScore());
                        break;
                    } else if (score.getRyanIdx(i) < score.getBest()[i]) {
                        break;
                    }
                }
            }
            return;
        }

        // 현재 인덱스에서 라이언이 맞힐 수 있는 최대 화살의 갯수
        /*int maxArrows = Math.min(n, score.getApeachIdx(idx) + 1);

        // 현재 과녁에 화살을 맞히는 경우
        for (int arrow = 1; arrow <= maxArrows && score.getRyanIdx(arrow) <= score.getApeachIdx(arrow); arrow++) {
            score.setRyanIdx(idx, arrow);
            findMaxScore(idx + 1, next, n - arrow, score);
            score.setRyanIdx(idx, score.getApeachIdx(idx) - 1);
        }*/


        // 현재 인덱스에서 라이언이 맞힐 수 있는 화살의 갯수
        for (int arrow = 0; arrow < score.getApeachLength() && score.getRyanIdx(arrow) <= score.getApeachIdx(arrow); arrow++) {
            // 과녁에 화살을 맞힘
            score.setRyanIdx(arrow, score.getRyanIdx(arrow) + 1);
            findMaxScore(idx + 1, next + 1, n, score);
            // 과녁에 화살을 맞히고 다음 과녁으로 넘어갈 때 라이언의 화살 수 하나 감소
            score.setRyanIdx(arrow, score.getRyanIdx(arrow) - 1);
        }
    }
}