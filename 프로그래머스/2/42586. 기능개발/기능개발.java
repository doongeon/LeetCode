import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < progresses.length; i++) {
            int t = (int)Math.ceil((100.0 - progresses[i])  / speeds[i]);
            q.offer(t);
        }
        
        
        Queue<Integer> tempQ = new ArrayDeque<>();
        
        while(!q.isEmpty()) {
            int base = q.poll();
            int count = 1;
            while(!q.isEmpty() && q.peek() <= base) {
                count++;
                q.poll();
            }
            tempQ.offer(count);
        }
        
        int[] answer = new int[tempQ.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = tempQ.poll();
        }
        
        
        return answer;
    }
}