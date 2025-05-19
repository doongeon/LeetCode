import java.util.*;

class Solution {
    public int solution(int[] info, int[][] edges) {
        List<Integer>[] child = new ArrayList[info.length];
        for(int i = 0; i < info.length; i++) child[i] = new ArrayList<>();
        for(int[] e : edges) {
            int from = e[0];
            int to = e[1];
            child[from].add(to);
        }
        
        
        Set<Integer> s = new HashSet<>();
        s.add(0);
        return dfs(0, 0, 0, info, child, s);
    }
    
    private int dfs(int cur, int sheep, int wolf, int[] info, List<Integer>[] child, Set<Integer> s) {
        if(info[cur] == 1) wolf++;
        if(info[cur] == 0) sheep++;
        
        if(wolf >= sheep) return -1;
        Set<Integer> nextS = new HashSet<>(s);
        for(int c : child[cur]) nextS.add(c);
        nextS.remove(cur);
        
        int maxSheep = sheep;
        for(int next : nextS) {
            maxSheep = Math.max(maxSheep, dfs(next, sheep, wolf, info, child, nextS));
        }
        
        return maxSheep;
    }
}