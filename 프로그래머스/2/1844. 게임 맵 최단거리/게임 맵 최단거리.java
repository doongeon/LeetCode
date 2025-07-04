import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        final int R = maps.length;
        final int C = maps[0].length;
        int answer = 1;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});
        
        int[] dy = {1, -1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        
        boolean[][] v = new boolean[R][C];
        v[0][0] = true;
        
        while(!q.isEmpty()) {
            final int qSize = q.size();
            
            for(int i = 0; i < qSize; i++) {
                int[] cur = q.poll();
                
                if(cur[0] == R - 1 && cur[1] == C - 1) return answer;
                
                for(int d = 0; d < 4; d++) {
                    int nextR = cur[0] + dy[d];
                    int nextC = cur[1] + dx[d];
                    
                    if(nextR < 0 || nextR >= R) continue;
                    if(nextC < 0 || nextC >= C) continue;
                    if(maps[nextR][nextC] == 0) continue;
                    if(v[nextR][nextC]) continue;
                    
                    v[nextR][nextC] = true;
                    q.offer(new int[] {nextR, nextC});
                }
            }
            
            
            answer++;
        }
        
        return -1;
    }
}