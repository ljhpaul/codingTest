package swea.d4.p1494;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static long answer, totalX, totalY;
    static int N;
    static Pos[] worms;
    
    // Pos
    static class Pos {
    	int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
    }

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            answer = Long.MAX_VALUE;
            totalX = 0;
            totalY = 0;
            N = Integer.parseInt(br.readLine());
            worms = new Pos[N];

            // input
            for(int i = 0; i < N; i++) {
            	st = new StringTokenizer(br.readLine());
            	int x = Integer.parseInt(st.nextToken());
            	int y = Integer.parseInt(st.nextToken());
            	totalX += x;
            	totalY += y;
            	worms[i] = new Pos(x, y);
            }
            
            // solve
            dfs(0, 0, 0, 0);

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    // DFS : combination N_C_(N/2)
	private static void dfs(int start, int cnt, long xSum, long ySum) {
		// base
		if(cnt == N/2) {
			long vx = xSum - (totalX - xSum);
			long vy = ySum - (totalY - ySum);
			answer = Math.min(answer, vx * vx + vy * vy);
			return;
		}
		
		// recursion
		for(int i = start; i < N; i++) {
			dfs(i + 1, cnt + 1, xSum + worms[i].x, ySum + worms[i].y);
		}
	}
}