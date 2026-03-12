package swea.d4.p2819;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int[][] grid;
    static Set<Integer> nums;
    
    static int[] dr = {-1, 1, 0, 0}; // 상하좌우(0123)
    static int[] dc = {0, 0, -1, 1};

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	grid = new int[4][4];
        	nums = new HashSet<>();
        	
        	// input
        	for(int r=0; r<4; r++) {
        		st = new StringTokenizer(br.readLine());
        		for(int c=0; c<4; c++) {
        			grid[r][c] = Integer.parseInt(st.nextToken());
        		}
        	}

            // solve
        	for(int r=0; r<4; r++) {
        		for(int c=0; c<4; c++) {
        			dfs(r, c, 0, grid[r][c]);
        		}
        	}
        	int answer = nums.size();

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        // output
        System.out.println(sb);
        br.close();
    }
    
    // dfs
	private static void dfs(int r, int c, int move, int num) {
		// base
		if(move == 6) {
			nums.add(num);
			return;
		}
		
		// recursion
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr < 0 || nr >= 4 || nc < 0 || nc >= 4) continue;
			dfs(nr, nc, move + 1, num * 10 + grid[nr][nc]);
		}
	} 
}
