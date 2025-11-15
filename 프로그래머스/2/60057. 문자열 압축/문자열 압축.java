import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        for(int token_size = 1; token_size <= s.length(); token_size++) {
            Queue<String> q = new ArrayDeque<>();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < s.length(); i += token_size) {
                int end = Math.min(i + token_size, s.length());
                q.offer(s.substring(i, end));
            }
            
            while(!q.isEmpty()) {
                String cur = q.poll();
                int count = 1;
                
                while(!q.isEmpty() && cur.equals(q.peek())) {
                    q.poll();
                    count++;
                }
                
                if(count == 1) {
                    sb.append(cur);
                } else {
                    sb.append(count);
                    sb.append(cur);
                }
            }
            
            answer = Math.min(answer, sb.toString().length());
        }
        
        return answer;
    }
}