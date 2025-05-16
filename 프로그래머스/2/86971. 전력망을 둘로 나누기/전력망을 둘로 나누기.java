import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for(int[] w : wires) {
            int from = w[0];
            int to = w[1];
            adj.get(from).add(to);
            adj.get(to).add(from);
        }
        
        int minDiff = 1000000;
        
        for(int i = 1; i <= n; i++) {
            List<Integer> l = new ArrayList<>();
            
            for(int node : adj.get(i)) {
                boolean[] v = new boolean[n + 1];
                v[i] = true;
                int count = dfs(node, adj, v);
                l.add(count);
            }
            
            int tempDiff = minDiff;
            
            if(l.size() == 1) {
                tempDiff = Math.abs(l.get(0) - 1);
            } else {
                int max = 0;
                int maxIdx = 0;
                for(int j = 0; j < l.size(); j++) {
                    if(l.get(j) > max) {
                        max = l.get(j);
                        maxIdx = j;
                    }
                }
                
                int sum = 0;
                for(int j = 0; j < l.size(); j++) {
                    if(j != maxIdx) {
                        sum += l.get(j);
                    }
                }
                
                tempDiff = Math.abs(sum + 1 - l.get(maxIdx));
            }
            
            minDiff = Math.min(minDiff, tempDiff);
        }
        
        
        return minDiff;
    }
    
    private int dfs(int node, List<List<Integer>> adj, boolean[] v) {
        int count = 0;
        v[node] = true;
        for(int next : adj.get(node)) {
            if(!v[next]) count += dfs(next, adj, v);
        }
        
        return count + 1;
    }
}