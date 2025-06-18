import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        int C = relation[0].length;
        Set<Set<Integer>> v = new HashSet<>();
        List<Set<Integer>> candidates = new ArrayList<>();
        Queue<Set<Integer>> q = new ArrayDeque<>();
        for(int i = 0; i < C; i++) {
            Set<Integer> temp = new TreeSet<>();
            temp.add(i);
            q.offer(temp);
        }
        
        while(!q.isEmpty()) {
            Set<Integer> colSet = q.poll();    
            boolean isMinColSet = true;
        
            for(Set<Integer> candidate : candidates) {
                boolean allIncluded = true;

                for(int col : candidate) {
                    if(!colSet.contains(col)) {
                        allIncluded = false;
                        break;
                    }
                }

                if(allIncluded) {
                    isMinColSet = false;
                    break;
                }
            }
            
            if(!isMinColSet) continue;
                
            
            Set<String> keySet = new HashSet<>();
            boolean isUnique = true;
            for(String[] row : relation) {
                StringBuilder sb = new StringBuilder();
                for(int c : colSet) {
                    sb.append(row[c]);
                    sb.append("/");
                }
                String key = sb.toString();
                if(keySet.contains(key)) {
                    isUnique = false;
                    break;
                }
                keySet.add(key);
            }
            
            if(isUnique) {
                candidates.add(colSet);
                continue;
            }
            
            
            for(int c = 0; c < C; c++) {
                if(colSet.contains(c)) continue;
                
                Set<Integer> nextColSet = new TreeSet<>(colSet);
                nextColSet.add(c);
                if(v.contains(nextColSet)) continue;
                
                v.add(nextColSet);
                q.offer(nextColSet);
            }
        }
        
        return candidates.size();
    }
}