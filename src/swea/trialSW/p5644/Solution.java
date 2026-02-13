package swea.trialSW.p5644;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[] dr = {0, -1, 0, 1, 0};
    static int[] dc = {0, 0, 1, 0, -1};

    static int answer, M, A;
    static int[] C, P;
    static boolean[][][] grid;
    static int[] move1;
    static int[] move2;
    static boolean[] isChargeable1;
    static boolean[] isChargeable2;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            answer = 0;
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            move1 = new int[M];
            move2 = new int[M];
            C = new int[A];
            P = new int[A];
            grid = new boolean[10][10][A];
            isChargeable1 = new boolean[A];
            isChargeable2 = new boolean[A];

            // input
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<M; i++) {
                move1[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<M; i++) {
                move2[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=0; i<A; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int range = Integer.parseInt(st.nextToken());
                int charge = Integer.parseInt(st.nextToken());

                C[i] = charge;

                grid[r - 1][c - 1][i] = true;
                spread(i, r, c, range);
            }
            
            // move
            move();

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    private static void spread(int bcNum, int r, int c, int range) {
        if(range == 0) {
            return;
        }

        grid[r][c][bcNum] = true;

        for(int d=1; d<5; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr < 0 || nr >= 10 || nc < 0 || nc >= 10) continue;
            if(grid[r][c][bcNum]) continue;
            spread(bcNum, nr, nc, range - 1);
        }
    }

    private static void move() {
        int r1 = 1;
        int c1 = 1;
        int r2 = 10;
        int c2 = 10;

        charge(r1, c1, r2, c2);

        for(int i=0; i<M; i++) {
            r1 += dr[move1[i]];
            c1 += dc[move1[i]];
            r2 += dr[move2[i]];
            c2 += dc[move2[i]];

            charge(r1, c1, r2, c2);
        }
    }

    private static void charge(int r1, int c1, int r2, int c2) {
        for(int i=0; i<A; i++) {
            for(int j=0; j<A; j++) {
                if(grid[r1][c1][i] && grid[r2][c2][j]) {
                    if(i == j) {
                        answer = Math.max(answer, )
                    }
                }
            }
        }
    }

}