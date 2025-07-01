class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for(int i = 0; i < edges.length; i++) {
            int[] e = edges[i];
            double p = succProb[i];
            int from = e[0];
            int to = e[1];
            
            if(!adj.containsKey(from)) adj.put(from, new ArrayList<>());
            if(!adj.containsKey(to)) adj.put(to, new ArrayList<>());

            adj.get(from).add(new int[] {to, i});
            adj.get(to).add(new int[] {from, i});
        }

        double[] prob = new double[n];
        Queue<Node> pq = new PriorityQueue<Node>((n1, n2) -> n2.p > n1.p ? 1 : -1);

        pq.offer(new Node(start_node, 1));
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(prob[cur.v] > cur.p) continue;

            prob[cur.v] = cur.p;
            if(cur.v == end_node) break;

            if(!adj.containsKey(cur.v)) continue;
            for(int[] e : adj.get(cur.v)) {
                int to = e[0];
                double p = cur.p * succProb[e[1]];

                if(p > prob[to])
                    pq.offer(new Node(to, p));
            }
        }
        
        return prob[end_node];
    }

    private class Node {
        public int v;
        public double p;

        public Node(int v, double p) {
            this.v = v;
            this.p = p;
        }
    }
}