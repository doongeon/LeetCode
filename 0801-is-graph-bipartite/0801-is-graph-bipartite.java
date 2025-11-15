class Solution {
    public boolean isBipartite(int[][] graph) {
        int v[] = new int[graph.length];

        for(int i = 0; i < v.length; i++) {
            if(v[i] != 0) continue;

            int root = i;
            Queue<Integer> q = new ArrayDeque<>();
            q.offer(root);
            v[root] = 1;
            while(!q.isEmpty()) {
                int cur = q.poll();

                for(int adj : graph[cur]) {
                    if(v[adj] == v[cur]) return false;

                    if(v[adj] == 0) {
                        v[adj] = v[cur] * -1;
                        q.offer(adj);
                    }
                }
            }
        }

        return true;        
    }
}