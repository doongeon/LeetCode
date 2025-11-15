class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int click_r = click[0], click_c = click[1];

        if(board[click_r][click_c] == 'M') {
            board[click_r][click_c] = 'X';
            return board;
        }

        int num_adj_mines = getNumOfAdjMines(click_r, click_c, board);
        if(num_adj_mines > 0) {
            board[click_r][click_c] = (char) ((int) '0' + num_adj_mines);
            return board;
        } else {
            Queue<int[]> q = new ArrayDeque<>();
            int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
            int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
            board[click_r][click_c] = 'B';
            q.offer(new int[] {click_r, click_c});

            while(!q.isEmpty()) {
                int[] cur = q.poll();
                
                for(int d = 0; d < 8; d++) {
                    int[] next = {cur[0] + dr[d], cur[1] + dc[d]};
                    if(next[0] < 0 || next[0] >= board.length) continue;
                    if(next[1] < 0 || next[1] >= board[0].length) continue;
                    if(board[next[0]][next[1]] != 'E') continue;

                    int temp_num_adj_mines = getNumOfAdjMines(next[0], next[1], board);
                    if(temp_num_adj_mines > 0) {
                        board[next[0]][next[1]] = (char) ((int) '0' + temp_num_adj_mines);
                    } else {
                        board[next[0]][next[1]] = 'B';
                        q.offer(next);
                    }
                }
            }
        }

        return board;
    }

    private int getNumOfAdjMines(int r, int c, char[][] board) {
        int num_adj_mines = 0;
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
        for(int d = 0; d < 8; d++) {
            int adj_r = r + dr[d];
            int adj_c = c + dc[d];

            if(adj_r < 0 || adj_r >= board.length) continue;
            if(adj_c < 0 || adj_c >= board[0].length) continue;
            if(board[adj_r][adj_c] == 'M') num_adj_mines++;
        }

        return num_adj_mines;
    }
}