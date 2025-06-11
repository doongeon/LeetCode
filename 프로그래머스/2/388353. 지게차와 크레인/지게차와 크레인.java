import java.util.*;

class Solution {
    public int solution(String[] storage, String[] requests) {
        int R = storage.length, C = storage[0].length();
        int answer = R * C;
        char[][] map = new char[R][C];
        
        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                map[r][c] = storage[r].charAt(c);
            }
        }
        
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        
        for(String req : requests) {
            char cur = req.charAt(0);
            
            if(req.length() > 1) {
                for(int r = 0; r < R; r++) {
                    for(int c = 0; c < C; c++) {
                        if(map[r][c] == cur) {
                            map[r][c] = '0';
                            answer--;
                        }
                    }
                }
            } else {
                Queue<int[]> deleteQ = new ArrayDeque<>();
                
                for(int r = 0; r < R; r++) {
                    for(int c = 0; c < C; c++) {
                        if(map[r][c] != cur) continue;
                        
                        boolean isEdge = false;
                        Queue<int[]> q = new ArrayDeque<>();
                        
                        q.offer(new int[] {r, c});
                        boolean[][] v = new boolean[R][C];
                        v[r][c] = true;
                        
                        while(!q.isEmpty()) {
                            int pos[] = q.poll();
                            int curR = pos[0];
                            int curC = pos[1];
                            
                            if(curR == 0 || curR == R - 1) {
                                isEdge = true;
                                break;
                            }
                            
                            if(curC == 0 || curC == C - 1) {
                                isEdge = true;
                                break;
                            }
                            
                            for(int d = 0; d < 4; d++) {
                                int nextR = curR + dy[d];
                                int nextC = curC + dx[d];
                                
                                if(nextR < 0 || nextR >= R) continue;
                                if(nextC < 0 || nextC >= C) continue;
                                if(v[nextR][nextC]) continue;
                                if(map[nextR][nextC] != '0') continue;
                                
                                v[nextR][nextC] = true;
                                q.offer(new int[] {nextR, nextC});
                            }
                        }
                        
                        if(isEdge) {
                            deleteQ.offer(new int[] {r, c});
                            answer--;
                        }
                    }
                }
                
                for(int[] pos : deleteQ) {
                    map[pos[0]][pos[1]] = '0';
                }
            }
            
            
//             for(int r = 0; r < R; r++) {
//                 for(int c = 0; c < C; c++) {
//                     System.out.print(map[r][c]);
//                 }
//                 System.out.println();
//             }
            
//              System.out.println();
//              System.out.println();
            
        }
        
        return answer;
    }
}