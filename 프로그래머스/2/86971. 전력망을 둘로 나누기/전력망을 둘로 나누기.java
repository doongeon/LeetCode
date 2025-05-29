import java.util.*;

class Solution {
    int minDiff = 10000000;
    int N = 0;
    
    public int solution(int n, int[][] wires) {
        List<Integer>[] adj = new ArrayList[n + 1];
        boolean[] v = new boolean[n + 1];
        N = n;
        
        for(int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for(int[] wire : wires) {
            int from = wire[0];
            int to = wire[1];
            
            adj[from].add(to);
            adj[to].add(from);
        }
        
        dfs(1, adj, v);
        
        return minDiff;
    }
    
    private int dfs(int node, List<Integer>[] adj, boolean[] v) {
        v[node] = true;
        int count = 1;
        
        for(int next : adj[node]) {
            if(v[next]) continue;
            count += dfs(next, adj, v);
        }
        
        int tempDiff = Math.abs(N - count * 2);
        minDiff = Math.min(tempDiff, minDiff);
        
        return count;
    }
}