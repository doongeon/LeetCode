import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        int R = sc.nextInt();
        int C = sc.nextInt();
        sc.nextLine();

        int[] pb = new int[2];
        int[] pr = new int[2];
        int[][] map = new int[R][C];


        for(int i = 0; i < R; i++) {
            String row = sc.nextLine();
            for(int j = 0; j < C; j++) {
                char cur = row.charAt(j);

                if(cur == '#') {
                    map[i][j] = 2;
                } else if (cur == 'O'){
                    map[i][j] = 0;
                } else {
                    map[i][j] = 1;
                }

                if(cur == 'B') pb = new int[]{i, j};
                if(cur == 'R') pr = new int[]{i, j};
            }
        }

        int[] dx = {1, 0, 0, -1};
        int[] dy = {0, 1, -1, 0};

        boolean[][][][] v = new boolean[R][C][R][C];
        Queue<int[][]> q = new ArrayDeque<>();

        v[pr[0]][pr[1]][pb[0]][pb[1]] = true;
        q.offer(new int[][]{pr, pb});

        int count = 1;

        while(!q.isEmpty()) {
            int qSize = q.size();

            if(count > 10) {
                System.out.println(0);
                return;
            }



            for(int i = 0; i < qSize; i++) {
                int[][] cur = q.poll();

//                System.out.println("count: " + count);
//                for(int r = 0; r < R; r++) {
//                    for(int c = 0; c < C; c++) {
//                        if (cur[0][0] == r && cur[0][1] == c) {
//                            System.out.print('R');
//                        } else if(cur[1][0] == r && cur[1][1] == c){
//                            System.out.print('B');
//                        } else if(map[r][c] == 2) {
//                            System.out.print('#');
//                        } else if(map[r][c] == 1) {
//                            System.out.print('.');
//                        } else {
//                            System.out.print('O');
//                        }
//                    }
//                    System.out.println();
//                }

                for(int d = 0; d < 4; d++) {
                    int rRow = cur[0][0];
                    int rCol = cur[0][1];
                    int bRow = cur[1][0];
                    int bCol = cur[1][1];

                    int rCount = 0;
                    while (map[rRow + dy[d]][rCol + dx[d]] != 2 && map[rRow][rCol] != 0) {
                        rRow += dy[d];
                        rCol += dx[d];
                        rCount++;
                        if (map[rRow][rCol] == 0) break;
                    }

                    int bCount = 0;
                    while (map[bRow + dy[d]][bCol + dx[d]] != 2 && map[bRow][bCol] != 0) {
                        bRow += dy[d];
                        bCol += dx[d];
                        bCount++;
                        if (map[bRow][bCol] == 0) break;
                    }

                    if (map[bRow][bCol] == 0) continue;

                    if (map[rRow][rCol] == 0) {
                        System.out.println(1);
                        return;
                    }

                    if (rRow == bRow && rCol == bCol) {
                        if (rCount > bCount) {
                            rRow -= dy[d];
                            rCol -= dx[d];
                        } else {
                            bRow -= dy[d];
                            bCol -= dx[d];
                        }
                    }

                    if (!v[rRow][rCol][bRow][bCol]) {
                        v[rRow][rCol][bRow][bCol] = true;
                        q.offer(new int[][]{{rRow, rCol}, {bRow, bCol}});
                    }
                }
            }

            count++;
        }


        System.out.println(0);
    }
}