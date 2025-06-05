import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();
        int C = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();

        boolean[][] map = new boolean[R][C];

        for(int r = 0; r < R; r++) {
            String line = sc.nextLine();
            for(int c = 0; c < C; c++) {
                int input = line.charAt(c) - '0';
                if(input == 0) {
                    map[r][c] = true;
                }
            }
        }

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        boolean[][][] v = new boolean[k + 1][R][C];
        Queue<int[]> q = new ArrayDeque<>();
        int dist = 1;

        v[0][0][0] = true;
        q.offer(new int[]{0, 0, 0});

        while(!q.isEmpty()) {
            int qSize = q.size();

            for(int i = 0; i < qSize; i++) {
                int[] cur = q.poll();
                int crashed = cur[0];

                if(cur[1] == R - 1 && cur[2] == C - 1) {
                    System.out.println(dist);
                    return;
                }

                for(int d = 0; d < 4; d++) {
                    int nextR = cur[1] + dy[d];
                    int nextC = cur[2] + dx[d];

                    if(nextR < 0 || nextR >= R) continue;
                    if(nextC < 0 || nextC >= C) continue;
                    if(!map[nextR][nextC]) continue;
                    if(v[crashed][nextR][nextC]) continue;

                    v[crashed][nextR][nextC] = true;
                    q.offer(new int[]{crashed, nextR, nextC});
                }

                if(crashed < k) {
                    for (int d = 0; d < 4; d++) {
                        int nextR = cur[1] + dy[d];
                        int nextC = cur[2] + dx[d];

                        if(nextR < 0 || nextR >= R) continue;
                        if(nextC < 0 || nextC >= C) continue;
                        if(map[nextR][nextC]) continue;
                        if(v[crashed + 1][nextR][nextC]) continue;

                        v[crashed + 1][nextR][nextC] = true;
                        q.offer(new int[]{crashed + 1, nextR, nextC});
                    }
                }
            }

            dist++;
        }

        System.out.println(-1);
    }
}