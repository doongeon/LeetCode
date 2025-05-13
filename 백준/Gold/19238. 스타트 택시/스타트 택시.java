import java.io.*;
import java.util.*;
import java.util.Arrays;

public class Main {
    private static class Position {
        public int r;
        public int c;

        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int[] getInput(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputs = getInput(br);
        int N = inputs[0], M = inputs[1], fuel = inputs[2];
        int[][] map = new int[N][N];
        for(int i = 0; i < N; i++) {
            inputs = getInput(br);
            System.arraycopy(inputs, 0, map[i], 0, N);
        }

        inputs = getInput(br);
        Position start = new Position(inputs[0] - 1, inputs[1] - 1);
        List<Position> p = new ArrayList<>(M);
        List<Position> d = new ArrayList<>(M);
        for(int i = 0; i < M; i++) {
            inputs = getInput(br);
            p.add(new Position(inputs[0] - 1, inputs[1] - 1));
            d.add(new Position(inputs[2] - 1, inputs[3] - 1));
        }


        for(int m = 0; m < M; m++) {
            int pId = -1;
            int minDest = 9999;

            int[][] distMap = getDistMap(start, p, map);

            for(int i = 0; i < p.size(); i++) {
                Position cur = p.get(i);
                if(distMap[cur.r][cur.c] < 0) {
                    System.out.println(-1);
                    return;
                }

                if(distMap[cur.r][cur.c] < minDest) {
                    minDest = distMap[cur.r][cur.c];
                    pId = i;
                } else if(distMap[cur.r][cur.c] == minDest) {
                    if(p.get(pId).r > p.get(i).r) {
                        pId = i;
                    } else if(p.get(pId).r == p.get(i).r) {
                        pId = p.get(pId).c > p.get(i).c ? i : pId;
                    }
                }
            }

            if(fuel - minDest <= 0) {
                System.out.println(-1);
                return;
            }

            int curToP = minDest;
            int pToD = getDist(p.get(pId), d.get(pId), map);

            if(pToD == -1 || pToD + curToP > fuel) {
                System.out.println(-1);
                return;
            }

            fuel = fuel - curToP + pToD;
            start = d.get(pId);
            p.remove(pId);
            d.remove(pId);
        }

        System.out.println(fuel);
    }

    private static int getDist(Position start, Position des, int[][] map) {
        int R = map.length, C = map[0].length;

        int[] dr = {1, 0, 0, -1};
        int[] dc = {0, 1, -1, 0};

        Queue<Position> q = new ArrayDeque<>();
        boolean[][] v = new boolean[R][C];
        int count = 0;
        q.offer(start);
        v[start.r][start.c] = true;

        while(!q.isEmpty()) {
            int qSize = q.size();

            for(int i = 0; i < qSize; i++) {
                Position cur = q.poll();
                if(cur.r == des.r && cur.c == des.c) return count;
                for(int d = 0; d < 4; d++) {
                    Position next = new Position(cur.r + dr[d], cur.c + dc[d]);
                    if(next.r < 0 || next.r >= R) continue;
                    if(next.c < 0 || next.c >= C) continue;
                    if(map[next.r][next.c] == 1) continue;
                    if(v[next.r][next.c]) continue;

                    q.offer(next);
                    v[next.r][next.c] = true;
                }
            }
            count++;
        }

        return -1;
    }

    private static int[][] getDistMap(Position start, List<Position> destnaions, int[][] map) {
        int R = map.length, C = map[0].length;
        int[][] distMap = new int[R][C];
        for(int[] row : distMap) {
            Arrays.fill(row, -1);
        }

        int[] dr = {1, 0, 0, -1};
        int[] dc = {0, 1, -1, 0};

        Queue<Position> q = new ArrayDeque<>();
        boolean[][] v = new boolean[R][C];
        int dist = 0;
        q.offer(start);
        v[start.r][start.c] = true;

        while(!q.isEmpty()) {
            int qSize = q.size();

            for(int i = 0; i < qSize; i++) {
                Position cur = q.poll();
                distMap[cur.r][cur.c] = dist;

                for(int d = 0; d < 4; d++) {
                    Position next = new Position(cur.r + dr[d], cur.c + dc[d]);

                    if(next.r < 0 || next.r >= R) continue;
                    if(next.c < 0 || next.c >= C) continue;
                    if(map[next.r][next.c] == 1) continue;
                    if(v[next.r][next.c]) continue;

                    q.offer(next);
                    v[next.r][next.c] = true;
                }
            }
            dist++;
        }

        return distMap;
    }
}
