import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = s.length();
        for(int i = 1; i <= s.length() / 2; i++) {
            String resultStr = "";
            
            Queue<String> q = new ArrayDeque<>();
            int head = 0, tail = head + i;
            while(tail <= s.length()) {
                q.offer(s.substring(head, tail));
                head = tail;
                tail += i;
            }
            
            if(head < s.length()) q.offer(s.substring(head));
            
            while(!q.isEmpty()) {
                int count = 1;
                String cur = q.poll();
                while(!q.isEmpty() && q.peek().equals(cur)) {
                    q.poll();
                    count++;
                }
                
                if(count == 1) {
                    resultStr += cur;
                } else {
                    resultStr += count + cur;
                }
            }
            
            if(resultStr.length() < answer) {
                answer = resultStr.length();
                System.out.println(resultStr);
            }
        }
        
        return answer;
    }
}