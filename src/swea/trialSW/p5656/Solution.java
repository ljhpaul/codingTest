package swea.trialSW.p5656;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

import programmers.java.ch05_Array.Array;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int answer;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            answer = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            int[][] grid = new int[H][W];
            List<Integer>[] bricks = new ArrayList[W];

            // input & turn grid
            for(int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for(int i = 0; i < W; i++) {
                for (int j = H - 1; j > 0; j--) {
                    if(bricks[i] == null) {
                        bricks[i] = new ArrayList<>();
                    }
                    if(grid[j][i] > 0) {
                        bricks[i].add(grid[j][i]);
                    }
                }
            }

            // solve
            boolean[][] boom = new boolean[W][H];

            dfs(N, bricks, boom);

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        // output
        System.out.println(sb);
        br.close();
    }

    public static List<Integer>[] copyBricks(List<Integer>[] bricks) {
        List<Integer>[] tmp = new ArrayList[bricks.length];
        for(int i=0; i<bricks.length; i++) {
            tmp[i] = new ArrayList<>(bricks[i]);
        }
        return tmp;
    }

    public static boolean[][] copyBoom(boolean[][] boom) {
        boolean[][] tmp = new boolean[boom.length][boom[0].length];
        for(int i=0; i<boom.length; i++) {
            for (int j = 0; j < boom[i].length; j++) {
                tmp[i][j] = boom[i][j];
            }
        }
        return tmp;
    }

    public static void dfs(int N, List<Integer>[] bricks, boolean[][] boom) {
        if(N == 0) {
            int cnt = 0;
            for(List line : bricks) {
                cnt += line.size();
            }
            answer = Math.min(cnt, answer);
            return;
        }

        for(int w=0; w<bricks.length; w++) {
            List<Integer>[] tmpBricks = copyBricks(bricks);
            boolean[][] tmpBoom = copyBoom(boom);
            bfs(w, tmpBricks, tmpBoom);
            dfs(N - 1, tmpBricks, tmpBoom);
        }
    }

    public static void bfs(int shot, List<Integer>[] bricks, boolean[][] boom) {
        int x = 0;
        


    }

}
