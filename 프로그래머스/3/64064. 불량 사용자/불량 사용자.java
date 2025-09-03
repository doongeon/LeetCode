import java.util.*;

class Solution {
    Set<Set<String>> answerSet;
    Set<String> tempSet;
    int answer = 0;
    
    public int solution(String[] user_id, String[] banned_id) {
        answerSet = new HashSet<>();
        tempSet = new HashSet<>();
        
        dfs(0, user_id, banned_id);
        
        return answerSet.size();
    }
    
    public void dfs(int idx, String[] user_id, String[] banned_id) {
        if(idx == banned_id.length) {
            answerSet.add(new HashSet<>(tempSet));
            return;
        }
        
        String banned = banned_id[idx];
        
        for(int i = 0; i < user_id.length; i++) {
            String user = user_id[i];
            
            if(tempSet.contains(user)) continue;
            if(banned.length() != user.length()) continue;
            
            int d = 0;
            int s = 0;
            
            for(int j = 0; j < user.length(); j++) {
                if(user.charAt(j) != banned.charAt(j)) d++;
                if(banned.charAt(j) == '*') s++;
            }
            
            if(d != s) continue;
            
            tempSet.add(user);
            dfs(idx + 1, user_id, banned_id);
            tempSet.remove(user);
        }
    }
}