import java.util.*;

class Solution {
    private int minDiff = 100;
    public int solution(int n, int[][] wires) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for(int[] w : wires) {
            adj.get(w[0]).add(w[1]);
            adj.get(w[1]).add(w[0]);
        }
        
        dfs(1, new boolean[n + 1], adj);
        
        return minDiff;
    }
    
    public int dfs(final int node, final boolean[] v, final List<List<Integer>> adj) {
        v[node] = true;
        int count = 1;
        
        for(int child : adj.get(node)) {
            if(v[child]) continue;
            
            count += dfs(child, v, adj);
        }
        
        minDiff = Math.min(minDiff, Math.abs(adj.size() - 1 - 2 * count));
        return count;
    }
}