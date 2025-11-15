import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] remain_day = new int[progresses.length];
        for(int i = 0; i < remain_day.length; i++) {
            remain_day[i] = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
        }
        
        System.out.println(Arrays.toString(remain_day));
        
        Queue<Integer> q = new ArrayDeque<>();
        int max = remain_day[0];
        int count = 1;
        for(int i = 1; i < remain_day.length; i++) {
            if(remain_day[i] <= max) {
                count++;
            } else {
                q.offer(count);
                count = 1;
                max = remain_day[i];
            }
        }
        q.offer(count);
        
        int[] answer = new int[q.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = q.poll();
        }
        return answer;
    }
}