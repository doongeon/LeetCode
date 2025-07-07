class Solution {
    int[] dx = {1, 1, 1, 0, 0, -1, -1, -1};
    int[] dy = {1, 0, -1, 1, -1, 1, 0, -1};
    int R = 0;
    int C = 0;


    public char[][] updateBoard(char[][] board, int[] click) {
        R = board.length;
        C = board[0].length;
        int r = click[0], c = click[1];

        if(board[r][c] == 'M') {
            board[r][c] = 'X';
        } else if(board[r][c] == 'E') {
            int adjM = 0;

            for(int d = 0; d < 8; d++) {
                int adjR = r + dy[d];
                int adjC = c + dx[d];

                if(adjR < 0 || adjR >= R) continue;
                if(adjC < 0 || adjC >= C) continue;
                if(board[adjR][adjC] == 'M' || board[adjR][adjC] == 'X') adjM++;
            }

            if(adjM > 0) {
                // reveal only one;
                board[r][c] = (char) (adjM + '0');
            } else {
                board[r][c] = 'B';
                for(int d = 0; d < 8; d++) {
                    int adjR = r + dy[d];
                    int adjC = c + dx[d];

                    if(adjR < 0 || adjR >= R) continue;
                    if(adjC < 0 || adjC >= C) continue;
                    if(board[adjR][adjC] == 'E') {
                        board = updateBoard(board, new int[]{adjR, adjC});
                    }
                }
            }
        }

        return board;
    }
}