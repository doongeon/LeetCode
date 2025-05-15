import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < computers.length; i++) {
            adj.add(i, new ArrayList<>());
            for(int j = 0; j < computers[i].length; j++) {
                if(i != j && computers[i][j] == 1) adj.get(i).add(j);
            }
        }
        
        Set<Integer> s = new HashSet<>();
        int answer = 0;
        
        for(int i = 0; i < computers.length; i++) {
            if(!s.contains(i)) {
                answer++;
                dfs(adj, s, i);
            }
        }
        
        
        return answer;
    }
    
    public void dfs(List<List<Integer>> adj, Set<Integer> visited, int cur) {
        visited.add(cur);
        for(int next : adj.get(cur)) {
            if(!visited.contains(next)) {
                dfs(adj, visited, next);
            }
        }
    }
}