import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        Set<String> s = new HashSet<>();
        Queue<String> q = new ArrayDeque<>();
        
        
        
        s.add(begin);
        q.offer(begin);
        
        while(!q.isEmpty()) {
            int qSize = q.size();
            for(int i = 0; i < qSize; i++) {
                String cur = q.poll();
                if(cur.equals(target)) return answer;
                for(String w : words) {
                    if(s.contains(w)) continue;
                    int count = 0;
                    for(int j = 0; j < w.length(); j++) {
                        if(cur.charAt(j) != w.charAt(j)) {
                            count++;
                        }
                    }
                    if(count == 1) {
                        q.offer(w);
                        s.add(w);
                    }
                }
            }
            answer++;
        }
        
        return 0;
    }
}