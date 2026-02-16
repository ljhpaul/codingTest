package swea.trialSW.p1767;

import java.io.*;
import java.util.*;

class Solution2 {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static List<int[]> cores;
    static int[][] grid;
    static int maxCoreCnt;
    static int minLineSum;
    static int N;

    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            cores = new ArrayList<>();
            maxCoreCnt = Integer.MIN_VALUE;
            minLineSum = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            grid = new int[N][N];

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                    if((i == 0 || i == N - 1 || j == 0 || j == N - 1) && grid[i][j] == 1) continue;
                    if(grid[i][j] == 1) {
                        cores.add(new int[]{ i, j });
                    }
                }
            }
            
            // solve
            dfs(0, 0, 0);

            // answer
            sb.append("#").append(tc).append(" ").append(minLineSum).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    // dfs
    private static void dfs(int idx, int coreCnt, int lineSum) {
        // base case
        if(idx == cores.size()) {
            if (maxCoreCnt < coreCnt) {
                maxCoreCnt = coreCnt;
                minLineSum = lineSum;
            } else if(maxCoreCnt == coreCnt) {
                minLineSum = Math.min(minLineSum, lineSum);
            }
            return;
        }

        // recursion case
        int[] cur = cores.get(idx);
        int r = cur[0];
        int c = cur[1];
        /// 1. delta 4 moving
        for(int d = 0; d < 4; d++) {
            // check available
            if(!isAvailable(r, c, d)) continue;
            // set line
            int len = toggleLine(r, c, d, 2);
            // next recursion
            dfs(idx + 1, coreCnt + 1, lineSum + len);
            // rollback line
            toggleLine(r, c, d, 0);
        }
        /// 2. not core set
        dfs(idx + 1, coreCnt, lineSum);
    }

    // check settable
    private static boolean isAvailable(int r, int c, int d) {
        int nr = r;
        int nc = c;

        while(true) {
            nr += dr[d];
            nc += dc[d];
            if(nr < 0 || nr >= N || nc < 0 || nc >= N) break;
            if(grid[nr][nc] != 0) return false;
        }

        return true;
    }

    // line toggling (0: not using / 2: using)
    private static int toggleLine(int r, int c, int d, int s) {
        int nr = r;
        int nc = c;
        int len = 0;

        while(true) {
            nr += dr[d];
            nc += dc[d];
            if(nr < 0 || nr >= N || nc < 0 || nc >= N) break;
            grid[nr][nc] = s;
            len++;
        }

        return len;
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