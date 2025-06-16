class Solution {
    private int[] dx = {1, -1, 0, 0};
    private int[] dy = {0, 0, 1, -1};
    int R, C;

    public boolean exist(char[][] board, String word) {
        R = board.length;
        C = board[0].length;
        boolean isExist = false;

        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                int[] startNode = {0, r, c};
                boolean[][] v = new boolean[R][C];
                v[r][c] = true;
                isExist = checkWord(startNode, word, board, v);
                if(isExist) break;
            }
            if(isExist) break;
        }

        return isExist;
    }

    private boolean checkWord(int[] node, String word, char[][] board, boolean[][] v) {
        int idx = node[0];
        int r = node[1], c = node[2];

        if(board[r][c] != word.charAt(idx)) return false;
        if(idx == word.length() - 1) return true;

        boolean isFind = false;

        for(int d = 0; d < 4; d++) {
            if(isFind) break;

            int nextR = r + dy[d];
            int nextC = c + dx[d];

            if(nextR < 0 || nextR >= R) continue;
            if(nextC < 0 || nextC >= C) continue;
            if(v[nextR][nextC]) continue;

            v[nextR][nextC] = true;
            int[] nextNode = {idx + 1, nextR, nextC};
            isFind = checkWord(nextNode, word, board, v);
            v[nextR][nextC] = false;
        }

        return isFind;
    }
}