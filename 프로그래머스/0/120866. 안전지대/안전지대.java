class Solution {
    public int solution(int[][] board) {
        int n = board.length;
        int danger = 0;
        
        int[] dx = {1, 1, 1, 0, 0, -1, -1, -1};
        int[] dy = {1, 0, -1, 1, -1, 1, 0 ,-1};
        
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                if(board[r][c] == 1) {
                    danger++;
                } else {
                    for(int d = 0; d < 8; d++) {
                        int adjR = r + dy[d];
                        int adjC = c + dx[d];
                        
                        if(adjR < 0 || adjR >= n) continue;
                        if(adjC < 0 || adjC >= n) continue;
                        if(board[adjR][adjC] == 1) {
                            danger++;
                            break;
                        }
                    }
                }
            }
        }
        
        return n * n - danger;
    }
}