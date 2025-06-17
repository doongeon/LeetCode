import java.util.*;

public class Main {
    private static int R = 0;
    private static int C = 0;
    private static int[] dy = {1, -1, 0, 0};
    private static int[] dx = {0, 0, 1, -1};
    private static List<int[]> dangers = null;
    private static int min = 10000000;
    private static int numSafeAreas = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        int[][] map = new int[R][C];
        dangers = new ArrayList<>();

        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                int cur = sc.nextInt();
                map[r][c] = cur;
                if(cur == 2)
                    dangers.add(new int[] {r, c});

                if(cur == 0)
                    numSafeAreas++;
            }
        }


        dfs(0, 0, map);

        System.out.println(numSafeAreas - 3 - min + dangers.size());
    }

    private static void dfs(int count, int idx, int[][] map) {
        if(count == 3) {
            boolean[][] v = new boolean[R][C];
            int numDangerAreas = 0;
            for(int[] d : dangers)
                numDangerAreas += bfs(d, v, map);

            min = Math.min(min, numDangerAreas);
            return;
        }

        int curR = idx / C;
        int curC = idx % C;

        for(int r = 0; r < R; r++) {
            if(r < curR) continue;
            for(int c = 0; c < C; c++) {
                if(r == curR && c < curC) continue;

                int next = map[r][c];
                if(next == 1 || next == 2)
                    continue;

                map[r][c] = 1;
                dfs(count + 1, r * C + c + 1, map);
                map[r][c] = 0;
            }
        }
    }

    private static int bfs(int[] root, boolean[][] v, int[][] map) {
        if(v[root[0]][root[1]]) return 0;

        Queue<int[]> q = new ArrayDeque<>();
        int count = 0;

        q.offer(root);
        v[root[0]][root[1]] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            count++;

            for(int d = 0; d < 4; d++) {
                int nextR = cur[0] + dy[d];
                int nextC = cur[1] + dx[d];

                if(nextR < 0 || nextR >= R) continue;
                if(nextC < 0 || nextC >= C) continue;
                if(v[nextR][nextC]) continue;
                if(map[nextR][nextC] == 1) continue;

                q.offer(new int[] {nextR, nextC});
                v[nextR][nextC] = true;
            }
        }

        return count;
    }
}