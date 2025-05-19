import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        List<Integer>[] adj = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) adj[i] = new ArrayList<>();
        for(int[] w : wires) {
            int from = w[0];
            int to = w[1];
            adj[from].add(to);
            adj[to].add(from);
        }
        
        int[] c = new int[n + 1];
        boolean[] v = new boolean[n + 1];
        dfs(1, adj, v, c);
        
        int answer = 9999;
        for(int i = 0; i <= n; i++) {
            answer = Math.min(answer, Math.abs(n - 2 * c[i]));
            
        }
        return answer;
    }
    
    private int dfs(int cur, List<Integer>[] adj, boolean[] v, int[] result) {
        int count = 1;
        v[cur] = true;
        
        for(int next : adj[cur]) {
            if(v[next]) continue;
            count += dfs(next, adj, v, result);
        }
        
        result[cur] = count;
        return count;
    }
}