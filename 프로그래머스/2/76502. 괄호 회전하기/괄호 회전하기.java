import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        for(int start = 0; start < s.length(); start++) {
            
            ArrayDeque<Character> stack = new ArrayDeque<>();
            for(int i = 0; i < s.length(); i++) {
                int cur = (start + i) % s.length();
                
                if(s.charAt(cur) == '[') {
                    stack.push(']');
                } else if(s.charAt(cur) == '{') {
                    stack.push('}');
                } else if(s.charAt(cur) == '(') {
                    stack.push(')');
                } else if(!stack.isEmpty() && s.charAt(cur) == stack.peek()) {
                    stack.pop();
                } else {
                    break;
                }
                
                if((cur + 1) % s.length() == start && stack.isEmpty()) answer++;
            }
        }
        
        return answer;
    }
}