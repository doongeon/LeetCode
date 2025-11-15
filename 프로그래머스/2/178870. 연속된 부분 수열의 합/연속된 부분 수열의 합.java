import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int min_length = Integer.MAX_VALUE;
        int[] answer = null;
        int head = 0, tail = 0;
        int temp_sum = sequence[0];
        while(head <= tail) {
            if(temp_sum < k) {
                if(tail + 1 >= sequence.length) break;
                tail++;
                temp_sum += sequence[tail];
            } else if(temp_sum > k){
                temp_sum -= sequence[head];
                head++;
            } else {
                int temp_length = tail - head;
                if(temp_length < min_length) {
                    min_length = temp_length;
                    answer = new int[] {head, tail};
                }
                temp_sum -= sequence[head];
                head++;
            }
        }
        return answer;
    }
}