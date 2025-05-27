import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        long sum1 = 0, sum2 = 0;
        
        for(int n : queue1) {
            sum1 += n;
            q1.offer(n);
        }
        
        for(int n : queue2) {
            sum2 += n;
            q2.offer(n);
        }
        
        if( (sum1 + sum2) % 2 != 0 ) return -1;
        
        int count = 0;
        
        while(true) {
            if(q1.isEmpty()) break;
            if(q2.isEmpty()) break;
            if(count > 1000000) break;
            
            if(sum1 == sum2) return count;
            
            if(sum1 > sum2) {
                sum1 -= q1.peek();
                sum2 += q1.peek();
                q2.offer(q1.poll());
            } else {
                sum2 -= q2.peek();
                sum1 += q2.peek();
                q1.offer(q2.poll());
            }
            
            count++;
        }
        
        return -1;
    }
}