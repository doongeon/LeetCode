import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int[] runtime = new int[jobs.length];
        Queue<Node> pq = new PriorityQueue<>((n1, n2) -> {
            if(n1.coast != n2.coast) return n1.coast - n2.coast;
            if(n1.req != n2.req) return n1.req - n2.req;
            return n1.idx - n2.idx;
        });
        
        Queue<Node> q = new PriorityQueue<>((n1, n2) -> n1.req - n2.req);
        for(int i = 0; i < jobs.length; i++) {
            q.offer(new Node(i, jobs[i][1], jobs[i][0]));
        }
        
        int time = 0;
        while(!q.isEmpty()) {
            Node cur = q.poll();
            if(time < cur.req) time = cur.req;
            pq.offer(cur);
            while(!q.isEmpty() && q.peek().req <= time) {
                pq.offer(q.poll());
            }
            
            Node nextNode = null;
            if(!q.isEmpty()) nextNode = q.peek();
            
            while(!pq.isEmpty()) {
                Node task = pq.poll();
                System.out.println(task);
                time += task.coast;
                runtime[task.idx] = time - task.req;
                if(nextNode != null && nextNode.req <= time) break;
            }
        }
        
        int sum = 0;
        for(int i = 0; i < runtime.length; i++) {
            sum += runtime[i];
        }
        
        return sum / runtime.length;
    }
    
    private class Node {
        public int idx;
        public int coast;
        public int req;
        
        public Node(int idx, int coast, int req) {
            this.idx = idx;
            this.coast = coast;
            this.req = req;
        }
        
        public String toString() {
            return idx + " " + coast + " " + req;
        }
    }
}