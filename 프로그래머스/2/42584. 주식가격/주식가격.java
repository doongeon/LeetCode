import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        ArrayDeque<Integer> stack = new ArrayDeque<>(); // 인덱스를 저장하는 스택
        int[] answer = new int[prices.length];
        for(int i = 0; i < prices.length; i++) {
            while(!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int idx = stack.pop();
                answer[idx] = i - idx;
            }
            
            stack.push(i);
        }
        
        while(!stack.isEmpty()) {
            int idx = stack.pop();
            answer[idx] = prices.length - idx - 1;
        }
        
        return answer;
    }
}