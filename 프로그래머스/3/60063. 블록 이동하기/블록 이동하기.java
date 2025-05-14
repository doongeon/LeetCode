import java.util.*;

class Solution {
    
    public int solution(int[][] board) {
        int R = board.length, C = board[0].length;
        int[] dr = {-1, 0, 0, 1};
        int[] dc = {0, 1, -1, 0};
        boolean[][] hv = new boolean[R][C];
        boolean[][] vv = new boolean[R][C];
        Queue<int[][]> q = new ArrayDeque<>();
        int count = 0;
        
        int[][] drone = {{0, 0}, {0, 1}};
        
        hv[drone[0][0]][drone[0][1]] = true;
        q.offer(drone);
        
        while(!q.isEmpty()) {
            int qSize = q.size();
            
            for(int i = 0; i < qSize; i++) {
                int[][] cur = q.poll();
                
                // System.out.print(cur[0][0] + ", " + cur[0][1] + " ");
                // if(cur[0][0] == cur[1][0]) System.out.println("h");
                // if(cur[0][1] == cur[1][1]) System.out.println("v");
                
                
                if(cur[1][0] == R - 1 && cur[1][1] == C - 1) return count;
                
                for(int d = 0; d < 4; d++) {
                    int[][] next = {{cur[0][0] + dr[d], cur[0][1] + dc[d]}, {cur[1][0] + dr[d], cur[1][1] + dc[d]}};
                    
                    if(next[0][0] < 0 || next[0][0] >= R) continue;
                    if(next[0][1] < 0 || next[0][1] >= C) continue;
                    if(next[1][0] < 0 || next[1][0] >= R) continue;
                    if(next[1][1] < 0 || next[1][1] >= C) continue;
                    if(board[next[0][0]][next[0][1]] == 1) continue;
                    if(board[next[1][0]][next[1][1]] == 1) continue;
                    if(next[0][0] == next[1][0] && hv[next[0][0]][next[0][1]]) continue;
                    if(next[0][1] == next[1][1] && vv[next[0][0]][next[0][1]]) continue;
                    
                    
                    if(next[0][0] == next[1][0]) hv[next[0][0]][next[0][1]] = true;
                    if(next[0][1] == next[1][1]) vv[next[0][0]][next[0][1]] = true;
                    q.offer(next);
                }
                
                
                if(cur[0][0] == cur[1][0]) {
                    // h일때
                    
                    // 시계 회전 왼쪽 피벗
                    int[][] clockNext1 = {cur[0], {cur[0][0] + 1, cur[0][1]}};
                    
                    if (clockNext1[0][0] >= 0 && clockNext1[0][0] < R &&
                        clockNext1[0][1] >= 0 && clockNext1[0][1] < C &&
                        clockNext1[1][0] >= 0 && clockNext1[1][0] < R &&
                        clockNext1[1][1] >= 0 && clockNext1[1][1] < C &&
                        board[clockNext1[0][0]][clockNext1[0][1]] == 0 &&
                        board[clockNext1[1][0]][clockNext1[1][1]] == 0 &&
                        board[cur[0][0] + 1][cur[0][1] + 1] == 0 &&
                        !vv[clockNext1[0][0]][clockNext1[0][1]]) {
                        vv[clockNext1[0][0]][clockNext1[0][1]] = true;
                        q.offer(clockNext1);
                    };
                    
                    // 시계 회전 오른쪽 피벗
                    int[][] clockNext2 = {{cur[0][0] - 1, cur[0][1] + 1}, cur[1]};
                    
                    if (clockNext2[0][0] >= 0 && clockNext2[0][0] < R &&
                        clockNext2[0][1] >= 0 && clockNext2[0][1] < C &&
                        clockNext2[1][0] >= 0 && clockNext2[1][0] < R &&
                        clockNext2[1][1] >= 0 && clockNext2[1][1] < C &&
                        board[clockNext2[0][0]][clockNext2[0][1]] == 0 &&
                        board[clockNext2[1][0]][clockNext2[1][1]] == 0 &&
                        board[cur[0][0] - 1][cur[0][1]] == 0 &&
                        !vv[clockNext2[0][0]][clockNext2[0][1]]) {
                        vv[clockNext2[0][0]][clockNext2[0][1]] = true;
                        q.offer(clockNext2);
                    };
                    
                    // 반시계 회전 왼쪽 피벗
                    int[][] deClockNext1 = {{cur[0][0] -1, cur[0][1]}, cur[0]};
                    
                    if (deClockNext1[0][0] >= 0 && deClockNext1[0][0] < R &&
                        deClockNext1[0][1] >= 0 && deClockNext1[0][1] < C &&
                        deClockNext1[1][0] >= 0 && deClockNext1[1][0] < R &&
                        deClockNext1[1][1] >= 0 && deClockNext1[1][1] < C &&
                        board[deClockNext1[0][0]][deClockNext1[0][1]] == 0 &&
                        board[deClockNext1[1][0]][deClockNext1[1][1]] == 0 &&
                        board[cur[0][0] - 1][cur[0][1] + 1] == 0 &&
                        !vv[deClockNext1[0][0]][deClockNext1[0][1]]) {
                        vv[deClockNext1[0][0]][deClockNext1[0][1]] = true;
                        q.offer(deClockNext1);
                    };
                    
                    // 반시계 회전 오른쪽 피벗
                    int[][] deClockNext2 = {cur[1], {cur[0][0] + 1, cur[0][1] + 1}};
                    
                    if (deClockNext2[0][0] >= 0 && deClockNext2[0][0] < R &&
                        deClockNext2[0][1] >= 0 && deClockNext2[0][1] < C &&
                        deClockNext2[1][0] >= 0 && deClockNext2[1][0] < R &&
                        deClockNext2[1][1] >= 0 && deClockNext2[1][1] < C &&
                        board[deClockNext2[0][0]][deClockNext2[0][1]] == 0 &&
                        board[deClockNext2[1][0]][deClockNext2[1][1]] == 0 &&
                        board[cur[0][0] + 1][cur[0][1]] == 0 &&
                        !vv[deClockNext2[0][0]][deClockNext2[0][1]]) {
                        vv[deClockNext2[0][0]][deClockNext2[0][1]] = true;
                        q.offer(deClockNext2);
                    };
                }
                
                if(cur[0][1] == cur[1][1]) {
                    // v일때
                    
                    // 시계 회전 위쪽 비펏
                    int[][] clockNext1 = {{cur[0][0], cur[0][1] - 1}, cur[0]};
                    
                    if (clockNext1[0][0] >= 0 && clockNext1[0][0] < R &&
                        clockNext1[0][1] >= 0 && clockNext1[0][1] < C &&
                        clockNext1[1][0] >= 0 && clockNext1[1][0] < R &&
                        clockNext1[1][1] >= 0 && clockNext1[1][1] < C &&
                        board[clockNext1[0][0]][clockNext1[0][1]] == 0 &&
                        board[clockNext1[1][0]][clockNext1[1][1]] == 0 &&
                        board[cur[0][0] + 1][cur[0][1] - 1] == 0 &&
                        !hv[clockNext1[0][0]][clockNext1[0][1]]) {
                        hv[clockNext1[0][0]][clockNext1[0][1]] = true;
                        q.offer(clockNext1);
                    };
                    
                    // 시계 회전 아래쪽 피벗
                    int[][] clockNext2 = {cur[1], {cur[0][0] + 1, cur[0][1] + 1}};
                    
                    if (clockNext2[0][0] >= 0 && clockNext2[0][0] < R &&
                        clockNext2[0][1] >= 0 && clockNext2[0][1] < C &&
                        clockNext2[1][0] >= 0 && clockNext2[1][0] < R &&
                        clockNext2[1][1] >= 0 && clockNext2[1][1] < C &&
                        board[clockNext2[0][0]][clockNext2[0][1]] == 0 &&
                        board[clockNext2[1][0]][clockNext2[1][1]] == 0 &&
                        board[cur[0][0]][cur[0][1] + 1] == 0 &&
                        !hv[clockNext2[0][0]][clockNext2[0][1]]) {
                        hv[clockNext2[0][0]][clockNext2[0][1]] = true;
                        q.offer(clockNext2);
                    };
                    
                    // 반시계 회전 위쪽 피벗
                    int[][] deClockNext1 = {cur[0], {cur[0][0], cur[0][1] + 1}};
                    
                    if (deClockNext1[0][0] >= 0 && deClockNext1[0][0] < R &&
                        deClockNext1[0][1] >= 0 && deClockNext1[0][1] < C &&
                        deClockNext1[1][0] >= 0 && deClockNext1[1][0] < R &&
                        deClockNext1[1][1] >= 0 && deClockNext1[1][1] < C &&
                        board[deClockNext1[0][0]][deClockNext1[0][1]] == 0 &&
                        board[deClockNext1[1][0]][deClockNext1[1][1]] == 0 &&
                        board[cur[0][0] + 1][cur[0][1] + 1] == 0 &&
                        !hv[deClockNext1[0][0]][deClockNext1[0][1]]) {
                        hv[deClockNext1[0][0]][deClockNext1[0][1]] = true;
                        q.offer(deClockNext1);
                    };
                    
                    // 반시계 회전 아래쪽 피벗
                    int[][] deClockNext2 = {{cur[1][0], cur[1][1] - 1}, cur[1]};
                    
                    if (deClockNext2[0][0] >= 0 && deClockNext2[0][0] < R &&
                        deClockNext2[0][1] >= 0 && deClockNext2[0][1] < C &&
                        deClockNext2[1][0] >= 0 && deClockNext2[1][0] < R &&
                        deClockNext2[1][1] >= 0 && deClockNext2[1][1] < C &&
                        board[deClockNext2[0][0]][deClockNext2[0][1]] == 0 &&
                        board[deClockNext2[1][0]][deClockNext2[1][1]] == 0 &&
                        board[cur[1][0] - 1][cur[1][1] - 1] == 0 &&
                        !hv[deClockNext2[0][0]][deClockNext2[0][1]]) {
                        hv[deClockNext2[0][0]][deClockNext2[0][1]] = true;
                        q.offer(deClockNext2);
                    };
                }
                
            }
            
            count++;
        }
        
        return count;
        
    }
}