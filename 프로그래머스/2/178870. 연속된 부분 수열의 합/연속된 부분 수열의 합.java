import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int tempSum = 0;
        int head = 0; int tail = 0;
        int minLength = 1000000;
        int[] result = new int[2];
        
        for(int i = 0; i < sequence.length; i++) {
            tail = i;
            tempSum += sequence[tail];
            
            if(tempSum == k) {
                if(head == tail) return new int[]{head, head};
                if(tail - head < minLength) {
                    minLength = tail - head;
                    result[0] = head;
                    result[1] = tail;
                }
            }
            
            while(tempSum > k) {
                tempSum -= sequence[head++];
            }
            
            if(tempSum == k) {
                if(head == tail) return new int[]{head, head};
                if(tail - head < minLength) {
                    minLength = tail - head;
                    result[0] = head;
                    result[1] = tail;
                }
            }
        }
        
        return result;
    }
}