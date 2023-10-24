package level.two.tictacto;

public class Tictacto {

    public static void main(String[] args) {

        Tictacto t = new Tictacto();
        System.out.println(t.solution(new String[] {"OOO", "...", "XXX"}));
        System.out.println(t.solution(new String[] {"XOO", ".XO", "..X"}));
    }

    public int solution(String[] board) {

        int answer = 0;

        char contraryChar = ' ';
        char findChar = ' ';
        boolean bool = true;

        int find = 0;
        int contrary = 0;

        int find2 = 0;
        int contrary2 = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < 3; j++) {

                if(board[i].charAt(j) != '.' && bool) {
                    if(board[i].charAt(j) == 'O') {
                        findChar = 'O';
                        contraryChar = 'X';
                        if(j == 0 || j == 2) {
                            if(board[1].charAt(1) == findChar && board[2].charAt( j == 0 ? 2 : 0) == findChar) {
                                return answer;
                            }
                        }
                    } else {
                        return answer;
                    }
                    bool = false;
                }

                if(board[i].charAt(j) == contraryChar) {
                    ++contrary;
                    ++contrary2;
                    if(j == 0 || j == 2 && i == 0) {
                        if(board[1].charAt(1) == contraryChar && board[2].charAt( j == 0 ? 2 : 0) == contraryChar) {
                            return answer;
                        }
                    }
                }

                if(board[i].charAt(j) == findChar) {
                    ++find2;
                    ++find;
                }
            }

            if(contrary2 == 3) {
                return answer;
            }

            if(find2 == 3) {
                return answer;
            }
            find2 = 0;
            contrary2 = 0;
        }


        if(find >= 6) {
            return 0;
        }
        if(find > contrary) {
            if(find - contrary == 1) {
                return 1;
            } else {
                return 0;
            }
        }
        if(contrary > find) {
            return 0;
        }
        if(find == contrary) {
            return 1;
        }
        return 0;
    }
}
