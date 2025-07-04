import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        List<int[]>[] adj = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for(int[] fare : fares) {
            adj[fare[0]].add(new int[] {fare[1], fare[2]});
            adj[fare[1]].add(new int[] {fare[0], fare[2]});
        }
        
        int[][] dist = new int[3][n + 1];
        for(int i = 0; i < 3; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        int[] startPoints = {s, a, b};
        for(int i = 0; i < 3; i++) {
            int[] d = dist[i];
            int startPoint = startPoints[i];
            Queue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
            pq.offer(new int[] {startPoint, 0});
            d[startPoint] = 0;
            
            while(!pq.isEmpty()) {
                int[] cur = pq.poll();
                
                if(cur[1] > d[cur[0]]) continue;
                
                for(int[] e : adj[cur[0]]) {
                    if(cur[1] + e[1] >= d[e[0]]) continue;
                    d[e[0]] = cur[1] + e[1];
                    pq.offer(new int[] {e[0], d[e[0]]});
                }
            }
        }
        
        int minCoast = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) {
            int sum = 0;
            for(int j = 0; j < 3; j++) {
                if(dist[j][i] == Integer.MAX_VALUE) {
                    sum = Integer.MAX_VALUE;
                    break;
                }
                sum += dist[j][i];
            }
            minCoast = Math.min(minCoast, sum);
        }
        
        return minCoast;
    }
}