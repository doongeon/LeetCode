import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        boolean[] v = new boolean[words.length];
        Queue<String> q = new ArrayDeque<>();
        
        q.offer(begin);
        int dist = 0;
        
        while(!q.isEmpty()) {
            int qSize = q.size();
            
            while(qSize > 0) {
                qSize--;
                String cur = q.poll();

                if(cur.equals(target)) return dist;

                for(int i = 0; i < words.length; i++) {
                    if(v[i]) continue;

                    int count = 0;

                    for(int c = 0; c < begin.length(); c++) {
                        if(cur.charAt(c) != words[i].charAt(c)) count++;
                    }

                    if(count == 1) {
                        v[i] = true;
                        q.offer(words[i]);
                    }
                }
            }
            
            
            
            dist++;
        }
        
        return 0;
    }
}