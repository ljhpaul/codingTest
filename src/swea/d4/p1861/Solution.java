package swea.d4.p1861;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};

    static int N, maxRoom, maxCnt;
    static int[][] grid, pos;
    static int[] cnt;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            String answer;
            N = Integer.parseInt(br.readLine());
            maxRoom = 0;
            maxCnt = 0;
            grid = new int[N][N];
            pos = new int[N*N+2][2];
            cnt = new int[N*N+1];

            // input
            for(int r=0; r<N; r++) {
                st = new StringTokenizer(br.readLine());
                for(int c=0; c<N; c++) {
                    int num = Integer.parseInt(st.nextToken());
                    grid[r][c] = num;
                    pos[num][0] = r;
                    pos[num][1] = c;
                }
            }
            Arrays.fill(cnt, 1);
            
            // solve
            solve();
            answer = maxRoom + " " + maxCnt;

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    // solve
    private static void solve() {
        for(int num=N*N; num>=1; num--) {
            // get pos
            int r = pos[num][0];
            int c = pos[num][1];

            // delta 4
            for(int d=0; d<4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
                if(grid[nr][nc] == num + 1) {
                    cnt[num] = 1 + cnt[num + 1];
                    if(cnt[num] >= maxCnt) {
                        maxCnt = cnt[num];
                        maxRoom = num;
                    }
                }
            }
        }
    }
}