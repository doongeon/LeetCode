import java.util.PriorityQueue;

public class Solution {
    static class Node {
        int from;
        int to;
        int w;

        public Node(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }

    public static int[] parent;

    public static int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n + 1];
        for(int i = 1; i < n; i++) parent[i] = i;
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);

        for(int[] cost : costs) {
            int from = cost[0];
            int to = cost[1];
            int w = cost[2];

            pq.offer(new Node(from, to, w));
        }

        int count = 0;
        while(count != n-1) {
            Node node = pq.poll();

            int r1 = findRoot(node.from);
            int r2 = findRoot(node.to);

            if(r1 == r2) continue;

            union(r1, r2);
            count++;
            answer += node.w;
        }

        return answer;
    }

    static void union(int v1, int v2) {
        int r1 = findRoot(v1);
        int r2 = findRoot(v2);

        if(r1 < r2) parent[r2] = r1;
        else parent[r1] = r2;
    }

    static int findRoot(int v) {
        if(parent[v] == v) return v;
        return parent[v] = findRoot(parent[v]);
    }
}
