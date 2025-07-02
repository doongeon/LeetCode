import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] dist = new int[3][n + 1];
        int[][] coast = new int[n + 1][n + 1];
        for(int i = 0; i < 3; i++)
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        
        for(int[] fare : fares) {
            coast[fare[0]][fare[1]] = fare[2];
            coast[fare[1]][fare[0]] = fare[2];
        }
        
        final int[] startPoints = {s, a, b};
        for(int i = 0; i < 3; i++) {
            int start = startPoints[i];
            Queue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
            dist[i][start] = 0;
            pq.offer(new int[] {start, 0});
            while(!pq.isEmpty()) {
                int[] cur = pq.poll();
                for(int j = 1; j <= n; j++) {
                    if(coast[cur[0]][j] == 0) continue;
                    if(cur[1] + coast[cur[0]][j] < dist[i][j]) {
                        dist[i][j] = cur[1] + coast[cur[0]][j];
                        pq.offer(new int[] {j, dist[i][j]});
                    }
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) {
            int sum = 0;
            for(int j = 0; j < 3; j++) {
                if(dist[j][i] == Integer.MAX_VALUE) {
                    sum = Integer.MAX_VALUE;
                    break;
                }
                sum += dist[j][i];
            }
            answer = Math.min(answer, sum);
        }
        
        return answer;
    }
}