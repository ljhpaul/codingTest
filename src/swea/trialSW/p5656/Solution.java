package swea.trialSW.p5656;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N;   // dfs 최대 깊이
    static int W;
    static int H;
    static int answer;   // 남은 블록 최소치 갱신(최대 폭파)

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            answer = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            int[][] bricks = new int[H][W];

            // input
            for(int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    bricks[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // solve
            dfs(0, bricks);

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        // output
        System.out.println(sb);
        br.close();
    }

    public static void dfs(int depth, int[][] bricks) {
        // 종료 조건
        if(depth == N) {
            int cnt = 0;
            for(int[] line : bricks) {
                for(int brick : line) {
                    if(brick > 0) cnt++;
                }
            }
            answer = Math.min(cnt, answer);
            return;
        }

        // 구슬 쏘는 경우의 수(shot : 0 ~ W-1 인덱스)
        for(int shot = 0; shot < W; shot++) {
            int[][] boomedBricks = bfs(shot, bricks);   // 폭파
            if(boomedBricks == null) {
                dfs(depth + 1, bricks);  // 아무것도 부수지 못한 경우 그대로 진행
            } else {
                dfs(depth + 1, brickDown(boomedBricks));  // 낙하
            }
        }
    }

    public static int[][] bfs(int shot, int[][] bricks) {
        // 구슬 맞힌 지점 구하기
        int startX = 0;
        int startY = shot;
        while(startX < H && bricks[startX][startY] == 0) {
            if(++startX == H) return null;
        }

        // 기존 배열 복사
        int[][] result = copyBricks(bricks);

        // BFS
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{startX, startY});

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            // 좌표 범위 체크
            if(x < 0 || x >= H || y < 0 || y >= W) continue;

            // 폭파 작업
            int brick = result[x][y];
            if(brick == 0) {
                continue;
            } else if(brick > 1) {
                // 4방향 연쇄 폭파 : 각 방향당 brick - 1 범위 만큼 q에 삽입
                for (int i = 1; i < brick; i++) {
                    q.offer(new int[]{x + i, y});
                    q.offer(new int[]{x - i, y});
                    q.offer(new int[]{x, y + i});
                    q.offer(new int[]{x, y - i});
                }
            }
            result[x][y] = 0;   // 현재 위치 폭파
        }

        //

        return result;
    }

    public static int[][] copyBricks(int[][] bricks) {
        int[][] result = new int[H][W];
        for(int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                result[i][j] = bricks[i][j];
            }
        }
        return result;
    }

    public static int[][] brickDown(int[][] boomedBricks) {
        int[][] result = new int[H][W];
        for(int j = 0; j < W; j++) {
            int idx = H - 1;
            for (int i = H - 1; i >= 0; i--) {
                if(boomedBricks[i][j] > 0) {
                    result[idx--][j] = boomedBricks[i][j];
                }
            }
        }
        return result;
    }
}
