package level.two.covid;

import java.util.ArrayList;
import java.util.List;

public class CovidTry {

    public static void main(String[] args) {
        String [][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        CovidTry solution = new CovidTry();
        int[] answer = solution.solution(places);
        for(int i : answer) {
            System.out.println(i);
        }
    }

    public final int ROW = 5;   // 최대 행
    public final int COL = 5;   // 최대 열

    public final int LIMIT = 2; // 거리 제한

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        int idx = 0;
        for (String[] room : places) {
            int count = 0;
            for (int i = 0; i < this.ROW; i++) {
                for (int j = 0; j < this.COL; j++) {
                    count += getMoveCount(i, j, room);
                }
            }

            // count가 1이상이면 false라는 의미여서 요구 조건대로 0으로 출력해주어야 함
            answer[idx++] = 0 < count ? 0 : 1;
        }
        return answer;
    }

    private int getMoveCount (int row, int col, String[] room) {
        int count = 0;
        // 현재 위치 (x,y)에 P가 들어갔을 때 탐색 수행
        if(room[row].charAt(col)=='P') {
            // Place 객체 위치 정보를 전달해서 4방 좌표를 만듬 (row-1, row+1, col-1, col+1)
            Place place = new Place(row, col, 1);
            place.setPlace();
            
            // 방향마다 이동하여 P를 만나면 카운트 : 이때 기준 위치정보도 넘겨주어야 함
            for (Place move : place.getPlace()) {
                count += moveRoom(move, room, place);
            }
        }
        return count;
    }
    public int moveRoom(Place current, String[] room, Place parent) {

        // 기준위치와 이동된 위치정보가 같아지면 무효처리
        if((parent.getRow() == current.getRow() && parent.getCol() == current.getCol())) {
            return 0;
        }
        // 상/하 (row) 위치가 0 보다 작거나 4보다 크면 벽을 만난것임
        if(current.getRow() < 0 || 4 < current.getRow()) {
            return 0;
        }
        // 좌/우 (col) 위치가 0 보다 작거나 4보다 크면 벽을 만난것임
        if(current.getCol() < 0 || 4 < current.getCol()) {
            return 0;
        }
        // 거리가 2보다 커지면 알필요가 없음
        if(this.LIMIT < current.getDistance()) {
            return 0;
        }
        
        // 여기까지 조건에 안걸렸으면 이동 가능하다는 뜻

        // 이동된 좌표(즉, 2차원 배열의 인덱스가 좌표가 됨)가  빈테이블 'O'을 만나면 더 이동 해야 함
        if(room[current.getRow()].charAt(current.getCol())=='O') {
            
            // 이곳이 최종 반환의 기준이 됨
            int count = 0;
            
            // 이동된 좌표 기준으로 한번 더 4방으로 위치를 생성
            current.setPlace();
            for (Place move : current.getPlace()) {
                // 이미 1번 이동된 위치에서 한번 더 이동하는거라 거리값을 1 증가 시켜 줌
                move.setDistance(current.getDistance() + 1);
                
                // 이동된 위치에 따라 아래 P 또는 X를 만나면 누적이 됨
                count += moveRoom(move, room, parent);
            }
            return count;

        // 이동된 좌표(즉, 2차원 배열의 인덱스가 좌표가 됨)가 'P'을 만나면 거리 2 안에 사람이 있다는 뜻
        } else if(room[current.getRow()].charAt(current.getCol())=='P') {
            return 1;

        // 이동된 좌표(즉, 2차원 배열의 인덱스가 좌표가 됨)가 파티션 'X'를 만나면 무효처리
        } else {
            // 만약 N 방향으로 1만큼 이동했는데 그게 X면 다음 위치가 P여도 어차피 상관없음
            return 0;
        }
    }
}

class Place {
    private int row;        // 행
    private int col;        // 열
    private int distance;   // 거리

    private final int left;     // 좌
    private final int right;    // 우
    private final int top;      // 상
    private final int bottom;   // 하
    private final List<Place> moveList;

    public Place(int row, int col, int distance) {
        this.row = row;
        this.col = col;
        this.distance = distance;
        this.moveList = new ArrayList<>();
        this.top = row + 1;
        this.bottom = row - 1;
        this.left = col - 1;
        this.right = col + 1;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
    public void setPlace() {
        this.moveList.add(new Place(this.top, this.col, this.distance));
        this.moveList.add(new Place(this.bottom, this.col, this.distance));
        this.moveList.add(new Place(this.row, this.left, this.distance));
        this.moveList.add(new Place(this.row, this.right, this.distance));
    }
    public List<Place> getPlace() {
        return this.moveList;
    }
}
