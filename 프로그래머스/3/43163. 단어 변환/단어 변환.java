import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        boolean is_include = false;
        for(String word : words) {
            if(word.equals(target)) is_include = true;
        }
        if(!is_include) return 0;
        
        Queue<String> q = new ArrayDeque<>();
        boolean[] v = new boolean[words.length];
        q.offer(begin);
        int dist = 0;
        
        
        while(!q.isEmpty()) {
            int q_size = q.size();
            
            for(int i = 0; i < q_size; i++) {
                String cur = q.poll();
                if(cur.equals(target)) {
                    answer = dist;
                }

                for(int word_idx = 0; word_idx < words.length; word_idx++) {
                    if(v[word_idx]) continue;
                    String next = words[word_idx];
                    int match_count = 0;
                    for(int c = 0; c < next.length(); c++) {
                        if(cur.charAt(c) == next.charAt(c)) match_count++;
                    }

                    if(match_count == next.length() - 1) {
                        q.offer(next);
                        v[word_idx] = true;
                    }
                }
            }
            
            dist++;
        }
        
        return answer;
    }
}