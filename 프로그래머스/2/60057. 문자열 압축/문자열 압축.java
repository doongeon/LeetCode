import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 1000;
        
        if(s.length() < 3) return s.length();
        
        for(int i = 1; i <= s.length() / 2; i++) {
            String resultStr = "";
            Queue<String> q = new ArrayDeque<>();
            
            int head = 0;
            int tail = head + i;
            while(tail <= s.length()) {
                q.offer(s.substring(head, tail));
                head = tail;
                tail += i;
            }
            
            if(head < s.length()) q.offer(s.substring(head));
            
            while(!q.isEmpty()) {
                int count = 1;
                String str = q.poll();
                while(!q.isEmpty() && q.peek().equals(str)) {
                    count++;
                    q.poll();
                }
                 
                if(count == 1) resultStr += str;
                else resultStr += count + str;
            }
            
            answer = Math.min(answer, resultStr.length());
        }
        
        return answer;
    }
}