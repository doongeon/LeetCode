import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0, k = 0;
        n = sc.nextInt();
        k = sc.nextInt();

        boolean[] v = new boolean[100000+1];
        Queue<Integer> q = new ArrayDeque<>();
        int dist = 0;

        v[n] = true;
        q.offer(n);

        while(!q.isEmpty()) {
            int qSize = q.size();

            for(int i = 0; i < qSize; i++) {
                int cur = q.poll();

                if(cur == k) {
                    System.out.println(dist);
                    return;
                }

                if(cur + 1 >= 0 && cur + 1 <= 100000 && !v[cur + 1]) {
                    v[cur + 1] = true;
                    q.offer(cur + 1);
                }

                if(cur - 1 >= 0 && cur - 1 <= 100000 && !v[cur - 1]) {
                    v[cur - 1] = true;
                    q.offer(cur - 1);
                }

                if(cur * 2 >= 0 && cur * 2 <= 100000 && !v[cur * 2]) {
                    v[cur * 2] = true;
                    q.offer(cur * 2);
                }
            }

            dist++;
        }
    }
}