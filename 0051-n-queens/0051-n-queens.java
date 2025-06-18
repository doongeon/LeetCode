class Solution {
    private int N = 0;
    private List<List<int[]>> possibles = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> answer = new ArrayList<>();
        N = n;

        boolean[] vc = new boolean[N];
        boolean[] vr = new boolean[N];

        dfs(0, 0, new ArrayList<>(), vc, vr);

        for(List<int[]> possible : possibles) {
            List<String> l = new ArrayList<>();

            for(int[] p : possible) {
                int c = p[1];

                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < N; i++) {
                    if(i == c)
                        sb.append('Q');
                    else
                        sb.append('.');
                }
                l.add(sb.toString());
            }
            answer.add(l);
        }

        return answer;
    }

    private void dfs(int count, int idx, List<int[]> l, boolean[] vc, boolean[] vr) {
        if(count == N) {
            boolean isValid = true;

            for(int i = 0; i < N; i++) {
                if(!isValid) break;
                for(int j = i + 1; j < N; j++) {
                    int[] p1 = l.get(i);
                    int[] p2 = l.get(j);

                    int r1 = p1[0];
                    int r2 = p2[0];
                    int c1 = p1[1];
                    int c2 = p2[1];
                    
                    if(Math.abs(r1 - r2) == Math.abs(c1 - c2)) {
                        isValid = false;
                        break;
                    }
                }
            }

            if(isValid) {
                List<int[]> temp = new ArrayList<>();
                for(int i = 0; i < N; i++) 
                    temp.add(l.get(i));
                possibles.add(temp);
            }

            return;
        }
        
        int curR = idx / N;
        int curC = idx % N;

        for(int r = 0; r < N; r++) {
            if(r < curR) continue;
            for(int c = 0; c < N; c++) {
                if(r == curR && c < curC) continue;

                if(vc[c] || vr[r]) continue; 
                vc[c] = true;
                vr[r] = true;
                l.add(new int[] {r, c});
                dfs(count + 1, r * N + c + 1, l, vc, vr);
                l.remove(l.size() - 1);
                vc[c] = false;
                vr[r] = false;
            }
        }
    }
}