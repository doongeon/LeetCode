import java.util.*;

class Solution {
    public int solution(int[] info, int[][] edges) {
        List<Integer>[] adj_list = new ArrayList[info.length];
        for(int i = 0; i < info.length; i++) adj_list[i] = new ArrayList<>();
        for(int[] e : edges) {
            adj_list[e[0]].add(e[1]);
        }
        
        Queue<Node> q = new ArrayDeque<>();
        Set<Integer> s = new HashSet<>();
        for(int child : adj_list[0]) s.add(child);
        q.offer(new Node(1, 0, s));
        int answer = 0;
        
        while(!q.isEmpty()) {
            Node cur = q.poll();
            answer = Math.max(answer, cur.sheep);
            
            for(int next : cur.next) {
                if(info[next] == 0) {
                    Set<Integer> temp_next = new HashSet(cur.next);
                    temp_next.remove(next);
                    for(int child : adj_list[next]) temp_next.add(child);
                    Node nextNode = new Node(cur.sheep + 1, cur.wolf, temp_next);
                    q.offer(nextNode);
                } else {
                    if(cur.sheep <= cur.wolf + 1) continue;
                    
                    Set<Integer> temp_next = new HashSet(cur.next);
                    temp_next.remove(next);
                    for(int child : adj_list[next]) temp_next.add(child);
                    Node nextNode = new Node(cur.sheep, cur.wolf + 1, temp_next);
                    q.offer(nextNode);
                }
            }
        }
        
        return answer;
    }
    
    class Node {
        int sheep;
        int wolf;
        Set<Integer> next;
        
        public Node(int sheep, int wolf, Set<Integer> next) {
            this.sheep = sheep;
            this.wolf = wolf;
            this.next = next;
        }
    }
}