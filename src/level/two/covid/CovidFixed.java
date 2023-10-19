package level.two.covid;

public class CovidFixed {
    public static void main(String[] args) throws Exception {
        String [][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
        CovidFixed solution = new CovidFixed();

        int[] answer = solution.solution(places);

        for(int i : answer) {
            System.out.println(i);
        }
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for(int r=0;r<places.length;++r) {
            int count=0;

            for(int i=0;i<5;++i) {
                for(int j=0;j<5;++j) {
                    count += solve(i, j, places[r]);
                }
            }

            // count가 0보다 크면 false이고 0보다 작거나 같으면 true임
            answer[r] = 0 < count ? 0 : 1;
        }

        return answer;
    }

    private int solve(int i, int j, String[] room) {
        int count=0;
        // 1 roop : -1, 1, 0, 0, /  0, 0 , 0, 0
        // 2 roop : -1, 1, 1, 1  /  0, 0, 1, 1
        if(room[i].charAt(j)=='P') {
            count+=recursiveSolve(i-1, j, room, 1, i, j);
            count+=recursiveSolve(i+1, j, room, 1, i, j);
            count+=recursiveSolve(i, j+1, room, 1, i, j);
            count+=recursiveSolve(i, j-1, room, 1, i, j);
        }
        return count;
    }

    // 재귀
    private int recursiveSolve(int i, int j, String[] room, int distance, int oi, int oj) {
        if ((i == oi && j == oj) || i < 0 || j < 0 || 4 < i || 4 < j || 2 < distance) {
            return 0;
        }

        if (room[i].charAt(j) == 'O') {
            int count = 0;
            count += recursiveSolve(i - 1, j, room, distance + 1, oi, oj);
            count += recursiveSolve(i + 1, j, room, distance + 1, oi, oj);
            count += recursiveSolve(i, j - 1, room, distance + 1, oi, oj);
            count += recursiveSolve(i, j + 1, room, distance + 1, oi, oj);
            return count;
        } else if (room[i].charAt(j) == 'P') {
            return 1;
        } else {
            return 0;
        }
    }
}
