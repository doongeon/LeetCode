import java.util.*;

class Solution {
    public int solution(int[] info, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < info.length; i++) adj.add(new ArrayList<>());
        for(int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            
            adj.get(from).add(to);
            adj.get(to).add(from);
        }
        
        Set<Integer> next = new HashSet<>();
        next.add(0);
        boolean[] v = new boolean[info.length];
        v[0] = true;
        
        return dfs(0, 0, 0, next, adj, info, v);
    }
    
    private int dfs(final int node, final int sheep, final int wolf, final Set<Integer> next, final List<List<Integer>> adj, final int[] info, final boolean[] v) {
        Set<Integer> newNext = new HashSet<>(next);
        newNext.remove(node);
        
        int newSheep = sheep;
        int newWolf = wolf;
        
        if(info[node] == 0) newSheep++;
        else newWolf++;
        
        if(newWolf >= newSheep) return 0;
        
        for(int child : adj.get(node)) {
            if(v[child]) continue;
            newNext.add(child);
        }
        
        int maxSheep = newSheep;
        for(int n : newNext) {
            v[n] = true;
             maxSheep = Math.max(maxSheep, dfs(n, newSheep, newWolf, newNext, adj, info, v));
            v[n] = false;
        }
        
        return maxSheep;
    }
}