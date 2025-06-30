class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[] dist = new int[n + 1];
        List<int[]>[] adj = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] t : times) 
            adj[t[0]].add(new int[] {t[1], t[2]});
        
        Queue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
        final int INF = 99999;
        Arrays.fill(dist, INF);
        dist[k] = 0;

        for(int[] e : adj[k])
            pq.offer(e);

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int to = cur[0];
            int t = cur[1];

            if(t >= dist[to]) continue;
            
            dist[to] = t;
            for(int[] next : adj[to]) {
                pq.offer(new int[] {next[0], t + next[1]});
            }
        }

        int max = 0;
        for(int i = 1; i <= n; i++) max = Math.max(dist[i], max);
        
        return max == INF ? -1 : max;
    }
}