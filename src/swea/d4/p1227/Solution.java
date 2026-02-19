package swea.d4.p1227;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int startR, startC, destR, destC;
    static int[][] maze;
    static boolean[][] visited;
    
    static int[] dr = {-1, 1, 0, 0}; // 상하좌우(0123)
    static int[] dc = {0, 0, -1, 1};

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = 10;
        for(int tc = 1; tc <= T; tc++) {
            // init
        	int t = Integer.parseInt(br.readLine());
        	maze = new int[100][100];
        	visited = new boolean[100][100];
        	
        	// input
        	for(int r=0; r<100; r++) {
        		String line = br.readLine();
        		for(int c=0; c<100; c++) {
        			maze[r][c] = line.charAt(c) - '0';
        			if(maze[r][c] == 2) {
        				startR = r;
        				startC = c;
        			} else if(maze[r][c] == 3) {
        				destR = r;
        				destC = c;
        			}
        		}
        	}

            // solve
        	int answer = bfs(maze, visited);

            // answer
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }
        // output
        System.out.println(sb);
        br.close();
    }

	private static int bfs(int[][] maze, boolean[][] visited) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {startR, startC});
		visited[startR][startC] = true;
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			
			if(r == destR && c == destC) return 1;
			
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr < 0 || nr >= 100 || nc < 0 || nc >= 100) continue;
				if(maze[nr][nc] == 1) continue;
				if(visited[nr][nc]) continue;
				q.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
		
		return 0;
	}
}
