import java.util.*;

class Solution {
    public class Position {
        int r;
        int c;
        
        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    public int solution(String[] maps) {
        int R = maps.length, C = maps[0].length();
        Position start = new Position(0, 0);
        Position lever = new Position(0, 0);
        Position end = new Position(0, 0);
        
        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                char ch = maps[r].charAt(c);
                if(ch == 'S') start = new Position(r, c);
                if(ch == 'L') lever = new Position(r, c);
                if(ch == 'E') end = new Position(r, c);
            }
        }
        
        
        int[] dx = {1, 0, 0, -1};
        int[] dy = {0, 1, -1, 0};
        
        Queue<Position> q = new ArrayDeque<>();
        boolean[][] v = new boolean[R][C];
        int count = 0;
        
        q.offer(start);
        v[start.r][start.c] = true;
        
        boolean isFindLever = false;
        while(!q.isEmpty()) {
            int qSize = q.size();
            
            for(int i = 0; i < qSize; i++) {
                Position cur = q.poll();
                
                if(lever.r == cur.r && lever.c == cur.c) {
                    isFindLever = true;
                    break;
                }
                
                
                for(int d = 0; d < dx.length; d++) {
                    Position next = new Position(cur.r + dx[d], cur.c + dy[d]);
                    
                    if(next.r < 0 || next.r >= R) continue;
                    if(next.c < 0 || next.c >= C) continue;
                    if(maps[next.r].charAt(next.c) == 'X') continue;
                    if(v[next.r][next.c]) continue;
                    
                    q.offer(next);
                    v[next.r][next.c] = true;
                }
            }
            
            if(isFindLever) break;
            count++;
        }
        
        if(!isFindLever) return -1;
        
        q = new ArrayDeque<>();
        v = new boolean[R][C];
        
        
        q.offer(lever);
        v[lever.r][lever.c] = true;
        
        boolean isFindEnd = false;
        while(!q.isEmpty()) {
            int qSize = q.size();
            
            for(int i = 0; i < qSize; i++) {
                Position cur = q.poll();
                
                if(end.r == cur.r && end.c == cur.c) {
                    isFindEnd = true;
                    break;
                }
                
                
                for(int d = 0; d < dx.length; d++) {
                    Position next = new Position(cur.r + dx[d], cur.c + dy[d]);
                    
                    if(next.r < 0 || next.r >= R) continue;
                    if(next.c < 0 || next.c >= C) continue;
                    if(maps[next.r].charAt(next.c) == 'X') continue;
                    if(v[next.r][next.c]) continue;
                    
                    q.offer(next);
                    v[next.r][next.c] = true;
                }
            }
            
            if(isFindEnd) break;
            count++;
        }
        
        if(!isFindEnd) return -1;
        
        return count;
    }
}