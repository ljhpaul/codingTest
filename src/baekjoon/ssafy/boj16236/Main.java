package baekjoon.ssafy.boj16236;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int cntSum, sSize, exp, sr, sc, N;
	static int[][] map;

	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };

	// main
	public static void main(String[] args) throws IOException {
		// init
		cntSum = 0;
		sSize = 2;
		exp = sSize;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		// input
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				int size = Integer.parseInt(st.nextToken());
				// 상어
				if(size == 9) {
					sr = r;
					sc = c;
				}
				// 물고기
				else {
					map[r][c] = size;
				}
			}
		}

		// solve
		while(true) if(!bfs()) break;
		
		// output
		System.out.println(cntSum);
		br.close();
	}
	
	// BFS
	private static boolean bfs() {
		// init
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->{
			if(a[0] != b[0]) return a[0] - b[0];
			if(a[1] != b[1]) return a[1] - b[1];
			return a[2] - b[2];
		});
		int[][] dist = new int[N][N];
		for(int[] line : dist) Arrays.fill(line, -1);
		Queue<int[]> q = new ArrayDeque<>();
		
		// first pos
		q.offer(new int[] {sr, sc});
		dist[sr][sc] = 0;
		
		// loop
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			
			// delta 4
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				if(dist[nr][nc] != -1) continue;
				
				int fSize = map[nr][nc];
				// 물고기
				if(fSize > 0) {
					if(sSize < fSize) continue;
					if(sSize > fSize) pq.offer(new int[] {dist[r][c] + 1, nr, nc});
				}
				
				// 이동
				q.offer(new int[] {nr, nc});
				dist[nr][nc] = dist[r][c] + 1;
			}
		}
		
		// renew cntSum
		if(!pq.isEmpty()) {
			int[] fish = pq.poll();
			cntSum += fish[0];
			sr = fish[1];
			sc = fish[2];
			// 먹기
			map[sr][sc] = 0;
			// 성장
			if(--exp == 0) {
				sSize++;
				exp = sSize;
			}
			return true;
		}
		
		return false;
	}
}
