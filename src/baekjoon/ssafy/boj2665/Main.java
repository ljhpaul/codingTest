package baekjoon.ssafy.boj2665;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int answer, N;
	static int[][] map;
	
	static final int INF = 1_000_000_000;
	static int[] dr = {-1, 1, 0, 0}; // 상하좌우(0123)
	static int[] dc = {0, 0, -1, 1};
	
	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = 0;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		// input
		for(int r=0; r<N; r++) {
			String line = br.readLine();
			for(int c=0; c<N; c++) {
				map[r][c] = line.charAt(c) - '0';
			}
		}

		// solve
		bfs();

		// output
		System.out.println(answer);
		br.close();
	}
	
	// bfs
	private static void bfs() {
		// init
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		int[][] dist = new int[N][N];
		for(int[] line : dist) Arrays.fill(line, INF);
		
		// start
		dq.add(new int[] {0, 0});
		dist[0][0] = 0;
		
		// loop
		while(!dq.isEmpty()) {
			// cur
			int[] cur = dq.pollFirst();
			int r = cur[0];
			int c = cur[1];
			
			// check
			if(r == N-1 && c == N-1) {
				answer = dist[r][c];
				return;
			}
			
			// next
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				
				if(map[nr][nc] == 1 && dist[r][c] < dist[nr][nc]) {
					dq.addFirst(new int[] {nr, nc});
					dist[nr][nc] = dist[r][c];
				} else if(map[nr][nc] == 0 && dist[r][c] + 1 < dist[nr][nc]) {
					dq.addLast(new int[] {nr, nc});
					dist[nr][nc] = dist[r][c] + 1;
				}
			}
		}
	}
}