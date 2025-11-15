class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] coast = new int[n + 1][n + 1];
        for(int i = 0; i <= n; i++) Arrays.fill(coast[i], 1000000);
        for(int[] time : times) coast[time[0]][time[1]] = time[2];
        for(int i = 1; i <= n; i++) coast[i][i] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
        for(int j = 1; j <= n; j++) {
            if(k == j) continue;
            if(coast[k][j] == 1000000) continue;
            pq.offer(new int[] {j, coast[k][j]});
        }

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            for(int next_node = 1; next_node <= n; next_node++) {
                if(coast[cur[0]][next_node] == 1000000) continue;

                int new_coast = cur[1] + coast[cur[0]][next_node];
                if(new_coast < coast[k][next_node]) {
                    coast[k][next_node] = new_coast;
                    pq.offer(new int[] {next_node, new_coast});
                }
            }
        }

        int answer = 0;
        for(int i = 1; i <= n; i++) {
            answer = Math.max(answer, coast[k][i]);
        }

        return answer == 1000000 ? -1 : answer;
    }
}