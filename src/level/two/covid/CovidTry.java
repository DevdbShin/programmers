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
            answer[idx++] = 0 < count ? 0 : 1;
        }
        return answer;
    }

    private int getMoveCount (int row, int col, String[] room) {
        int count = 0;
        if(room[row].charAt(col)=='P') {
            Place place = new Place(row, col, 1);
            place.setPlace();
            for (Place move : place.getPlace()) {
                count += moveRoom(move, room, place);
            }
        }
        return count;
    }
    public int moveRoom(Place current, String[] room, Place parent) {

        if((parent.getRow() == current.getRow() && parent.getCol() == current.getCol())) {
            return 0;
        }

        if(current.getRow() < 0 || 4 < current.getRow()) {
            return 0;
        }

        if(current.getCol() < 0 || 4 < current.getCol()) {
            return 0;
        }

        if(this.LIMIT < current.getDistance()) {
            return 0;
        }

        if(room[current.getRow()].charAt(current.getCol())=='O') {
            int count = 0;
            current.setPlace();
            for (Place move : current.getPlace()) {
                move.setDistance(current.getDistance() + 1);
                count += moveRoom(move, room, parent);
            }
            return count;
        } else if(room[current.getRow()].charAt(current.getCol())=='P') {
            return 1;
        } else {
            return 0;
        }
    }
}

class Place {
    private int row;        // 행
    private int col;        // 열
    private int distance;   // 거리

    private final int left; // 왼쪼
    private final int right;
    private final int top;
    private final int bottom;
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
