import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for(int[] e : edge) {
            if(!adj.containsKey(e[0])) adj.put(e[0], new HashSet<>());
            if(!adj.containsKey(e[1])) adj.put(e[1], new HashSet<>());
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        
        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> s = new HashSet<>();
        q.offer(1);
        s.add(1);
        
        while(!q.isEmpty()) {
            int qSize = q.size();
            answer = qSize;
            for(int i = 0; i < qSize; i++) {
                int cur = q.poll();
                for(int adjNode : adj.get(cur)) {
                    if(!s.contains(adjNode)) {
                        q.offer(adjNode);
                        s.add(adjNode);
                    }
                }
            }
        }
        
        return answer;
    }
}