package swea.trialSW.p5648;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static Atom[][] grid;
    static Queue<int[]> q;
    static List<int[]> list;
    static int answer;

    static int[] dummy = {-1, -1, -1, -1};
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    // Atom
    static class Atom {
        int d;
        int k;
        boolean isMoved;
        boolean isCrushed;

        Atom(int d, int k) {
            this.d = d;
            this.k = k;
            this.isMoved = false;
            this.isCrushed = false;
        }
    }

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            int N = Integer.parseInt(br.readLine());
            grid = new Atom[2001][2001];
            q = new ArrayDeque<>();
            q.offer(dummy);  // cycle point enqueue
            list = new ArrayList<>();
            answer = 0;

            // input
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                q.offer(new int[]{r + 1000, c + 1000, d, k});
                grid[r + 1000][c + 1000] = new Atom(d, k);
            }
            
            // solve
            solve();

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    // solve
    private static void solve() {
        while(q.size() > 1) {
            // new cycle start -> remove shadow(2)
            if(q.peek() == dummy) {
                q.poll();
                removeCrush();
                q.offer(dummy);
                continue;
            }

            // get pos
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int d = cur[2];
            int k = cur[3];

            // get remain crush
            if(grid[r][c]) {
                answer += k;
                continue;
            }

            // remove shadow
            shadow[r][c] = 0;

            // move atom
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr < 0 || nr >= 2001 || nc < 0 || nc >= 2001) continue;
            if(grid[nr][nc] > 0) {
                // crush
                answer += k;
                grid[nr][nc] = 2;
                list.add(new int[]{r, c});
                continue;
            } else {
                grid[nr][nc] = 1;
            }

            // end move
            q.offer(new int[]{nr, nc, d, k});
        }
    }

    private static void removeCrush() {
        for(int[] pos : list) {
            int r = pos[0];
            int c = pos[1];
            grid[r][c] = 0;
        }
        list = new ArrayList<>();
    }
}
/*
# 상태
- 결정:
- 미결정:
# 선택지
-
# 제약
-
# 종료 조건
-
 */