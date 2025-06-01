import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        HashMap<String, Integer> im = new HashMap<>();
        HashMap<String, Set<String>> sm = new HashMap<>();
        
        for(int i = 0; i < report.length; i++) {
            String[] bads = report[i].split(" ");
            String me = bads[0];
            String you = bads[1];
            
            if(!sm.containsKey(me)) {
                Set<String> newSet = new HashSet<>();
                sm.put(me, newSet);
            }
            
            Set<String> temp = sm.get(me);
            
            if(!temp.contains(you)) {
                if(!im.containsKey(you)) {
                    im.put(you, 0);
                }
                
                im.put(you, im.get(you) + 1);
            }
            
            temp.add(you);
            sm.put(me, temp);
        }
        
        int[] answer = new int[id_list.length];
        
        for(int i = 0; i < id_list.length; i++) {
            String cur = id_list[i];
            
            if(!sm.containsKey(cur)) continue;
            
            Set<String> bads = sm.get(cur);
            
            for(String b : bads) {
                if(im.get(b) >= k) answer[i]++;
            }
            
        }
        
        return answer;
    }
}