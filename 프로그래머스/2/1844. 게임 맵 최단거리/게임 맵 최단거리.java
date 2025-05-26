import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int R = maps.length, C = maps[0].length;
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[R][C];
        int answer = 0;
        
        v[0][0] = true;
        q.offer(new int[] {0, 0});
        
        int[] dx = {1 ,0, 0, -1};
        int[] dy = {0, 1, -1, 0};
        
        while(!q.isEmpty()) {
            int qSize = q.size();
            
            for(int i = 0 ; i < qSize; i++) {
                int[] cur = q.poll();
                if(cur[0] == R - 1 && cur[1] == C - 1) return answer + 1;
                
                for(int d = 0; d < 4; d++) {
                    int[] next = {cur[0] + dy[d], cur[1] + dx[d]};
                    
                    if(next[0] < 0 || next[0] >= R) continue;
                    if(next[1] < 0 || next[1] >= C) continue;
                    if(maps[next[0]][next[1]] == 0) continue;
                    if(v[next[0]][next[1]]) continue;
                    
                    v[next[0]][next[1]] = true;
                    q.offer(next);
                }
            }
            
            answer++;
        }
        
        
        return -1;
    }
}