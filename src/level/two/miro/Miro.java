package level.two.miro;

import java.util.ArrayList;
import java.util.List;

public class Miro {
    public static void main(String[] args) {
        Miro m = new Miro();
        System.out.println(m.solution(new String[] {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"}));
    }
    private int [] targetPosition;

    public int solution(String[] maps) {
        int answer = 0;
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < 5; j++) {
                 if(maps[i].charAt(j) == 'S') {
                     answer = moveMiro(i,j, maps);
                 }
            }
        }
        return answer;
    }
    public int moveMiro(int row, int col, String[] maps) {
        Postion start = new Postion(row, col, 0);
        start.setDistanceList();
        for (Postion target : start.getDistanceList()) {
            findTarget(target, maps, 'L');
        }
        if(targetPosition != null) {
            Postion end = new Postion(targetPosition[0], targetPosition[1], targetPosition[2]);
            targetPosition = null;
            end.setDistanceList();
            for (Postion target : end.getDistanceList()) {
                findTarget(target, maps, 'E');
            }
            if(targetPosition != null) {
                return targetPosition[2];
            }
        }
        return -1;
    }

    public void findTarget(Postion current, String[] maps, char target) {
        if(current.getRow() < 0 || 4 < current.getRow()) {
            return;
        }
        if(current.getCol() < 0 || 4 < current.getCol())        {
            return;
        }
        if(maps[current.getRow()].charAt(current.getCol()) == 'X') {
            return;
        }

        if(maps[current.getRow()].charAt(current.getCol()) == target) {
            targetPosition = new int[] { current.getRow(), current.getCol(), current.getDistance() };
            return;
        }

        if(maps[current.getRow()].charAt(current.getCol()) == 'O') {
            current.setDistanceList();
            for (Postion pos : current.getDistanceList()) {
                pos.setDistance(current.getDistance() + 1);
                findTarget(pos, maps, target);
            }
        }
    }
}

class Postion {
    private int row;
    private int col;
    private int distance;
    private int count;
    private final int left;
    private final int right;
    private final int top;
    private final int bottom;

    private final List<Postion> distanceList;
    public Postion(int row, int col, int distance) {
        this.row = row;
        this.col = col;
        this.distance = distance;
        this.distanceList = new ArrayList<>();
        this.top = row + 1;
        this.bottom = row - 1;
        this.left = col - 1;
        this.right = col + 1;
        this.count = 0;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    public void setDistanceList() {

        if(this.top > 0 && 4 > this.top) {
            this.distanceList.add(new Postion(this.top, this.col, this.distance));
        }

        if(this.bottom > 0 && 4 > this.bottom) {
            this.distanceList.add(new Postion(this.bottom, this.col, this.distance));
        }

        if(this.left > 0 && 4 > this.left) {
            this.distanceList.add(new Postion(this.row, this.left, this.distance));
        }

        if(this.right > 0 && 4 > this.right) {
            this.distanceList.add(new Postion(this.row, this.right, this.distance));
        }
    }

    public List<Postion> getDistanceList() {
        return this.distanceList;
    }
}
