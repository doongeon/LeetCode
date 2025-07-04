import java.util.*;

class Solution {
    private int minDiff = Integer.MAX_VALUE;
    
    public int solution(int n, int[][] wires) {
        List<Integer>[] adj = new ArrayList[n + 1];
        
        for(int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for(int[] wire : wires) {
            adj[wire[0]].add(wire[1]);
            adj[wire[1]].add(wire[0]);
        }
        
        dfs(1, adj, new boolean[n + 1], n);
        
        return minDiff;
    }
    
    public int dfs(int node, List<Integer>[] adj, boolean[] v, int n) {
        int count = 1;
        
        for(int next : adj[node]) {
            if(v[next]) continue;
            v[next] = true;
            count += dfs(next, adj, v, n);
        }
        
        minDiff = Math.min(minDiff, Math.abs(n - count - count));
        
        return count;
    }
}