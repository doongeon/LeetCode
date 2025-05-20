import java.util.*;

class Solution {
    private class Position {
        public int r;
        public int c;
        
        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    public int[] solution(String[][] places) {   
        List<Integer> result = new ArrayList<>();
        
        for(String[] place : places) {
            List<Position> l = new LinkedList<>();
            
            for(int i = 0; i < 5; i++) {
                for(int j = 0; j < 5; j++) {
                    if(place[i].charAt(j) == 'P') {
                        l.add(new Position(i, j));
                    }
                }
            }
            
            int lSize = l.size();
            boolean danger = false;
            for(int i = 0; i < lSize; i++) {
                if(danger) break;
                Position cur = l.get(0);
                l.remove(0);
                for(Position p : l) {
                    if(!isValid(cur, p, place)) danger = true;
                }
            }
            
            
            if(danger) result.add(0);
            else result.add(1);
        }
        
        
        return result.stream().mapToInt(e -> e).toArray();
    }
    
    private boolean isValid(Position start, Position dest, String[] place) {
        final int R = place.length;
        final int C = place[0].length();
        Queue<Position> q = new ArrayDeque<>();
        boolean[][] v = new boolean[R][C];
        int dist = 0;
        int[] dx = {1, 0, 0, -1};
        int[] dy = {0, 1, -1, 0};
        
        q.offer(start);
        v[start.r][start.c] = true;
        
        while(!q.isEmpty() && dist <= 2) {
            int qSize = q.size();
            
            for(int i = 0; i < qSize; i++) {
                Position cur = q.poll();
                if(cur.r == dest.r && cur.c == dest.c) return false;
                
                for(int d = 0; d < 4; d++) {
                    Position next = new Position(cur.r + dx[d], cur.c + dy[d]);
                    if(next.r < 0 || next.r >= R) continue;
                    if(next.c < 0 || next.c >= C) continue;
                    if(place[next.r].charAt(next.c) == 'X') continue;
                    if(v[next.r][next.c]) continue;
                    
                    v[next.r][next.c] = true;
                    q.offer(next);
                }
            }
            dist++;
        }
        
        return true;
    }
}