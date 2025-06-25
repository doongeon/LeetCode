import java.util.*;

class Solution {
    public int solution(String numbers) {
        boolean[] prime = new boolean[10000000];
        for(int i = 2; i < 10000000; i++) 
            prime[i] = true;
        
        for(int i = 2; i <= 10000000 / 2; i++) {
            if(!prime[i]) continue;
            
            for(int c = 2; c * i < 10000000; c++) 
                prime[c * i] = false;
        }        
        
        Set<Integer> s = new HashSet<>();
        boolean[] v = new boolean[numbers.length()];
        dfs(new ArrayList<>(), numbers, s, v);
        
        
        int answer = 0;
        
        for(int i : s) 
            if(prime[i]) answer++;
        
        
        return answer;
    }
    
    private void dfs(List<Character> l, String numbers, Set<Integer> s, boolean[] v) {
        if(l.size() > 0) {
            char[] temp = new char[l.size()];
            for(int i = 0; i < l.size(); i++) 
                temp[i] = l.get(i);
            
            int num = Integer.parseInt(new String(temp));
            s.add(num);
        }
        
        for(int i = 0; i < numbers.length(); i++) {
            if(v[i]) continue;
            
            v[i] = true;
            l.add(numbers.charAt(i));
            dfs(l, numbers, s, v);
            l.remove(l.size() - 1);
            v[i] = false;
        }
    }
}