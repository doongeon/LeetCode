import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int[] result = new int[prices.length];
        
        stack.push(0);
        
        for(int i = 1; i < prices.length; i++) {            
            while(!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int idx = stack.pop();
                result[idx] = i - idx;
            }
            
            stack.push(i);
        }
        
        while(!stack.isEmpty()) {
            int idx = stack.pop();
            result[idx] = prices.length - idx - 1;
        }
        
        return result;
    }
}