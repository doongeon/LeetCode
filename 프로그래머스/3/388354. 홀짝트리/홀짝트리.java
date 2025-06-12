import java.util.*;

class Solution {
    int treeId = 1;
    
    public int[] solution(int[] nodes, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for(int node : nodes) {
            map.put(node, new ArrayList<>());
        }
        
        for(int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            
            map.get(from).add(to);
            map.get(to).add(from);
        }
        
        
        Map<Integer, List<Integer>> treeMap = new HashMap<>();
        Set<Integer> v = new HashSet<>();
        
        for(int node : nodes) {
            if(v.contains(node)) continue;
            
            treeMap.put(treeId, new ArrayList<>());
            dfs(node, map, v, treeMap);
            treeId++;
        }
        
        int[] answer = new int[2];
        
        for(int key : treeMap.keySet()) {
            int count = 0;
            
            for(int node : treeMap.get(key)) {
                boolean isNodeEven = node % 2 == 0;
                boolean isEdgeEven = map.get(node).size() % 2 == 0;
                
                if(isNodeEven == isEdgeEven) 
                    count++;
            }
            
            if(count == 1) 
                answer[0]++;
            
            if(count == treeMap.get(key).size() - 1)
                answer[1]++;
            
        }
        
        
        
        
        
        
        
        return answer;
    }
    
    private void dfs(int node, Map<Integer, List<Integer>> map, Set<Integer> v, Map<Integer, List<Integer>> treeMap) {
        if(v.contains(node)) return;
        
        v.add(node);
        treeMap.get(treeId).add(node);
        
        Set<Integer> childSet = new HashSet<>();
        for(int c : map.get(node)) {
            if(v.contains(c)) continue;
            
            childSet.add(c);
        }
        
        for(int child : childSet) {
            dfs(child, map, v, treeMap);
        }
    }
}