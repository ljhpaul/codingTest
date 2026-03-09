package baekjoon.ssafy.boj16234;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;

	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };

	// main
	public static void main(String[] args) throws IOException {
		// init
		int answer = 0;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		// input
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// solve
		while(true) {
			visited = new boolean[N][N];
			int move = 0;
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					if(bfs(r, c)) move++;
				}
			}
			if(move > 0) answer++;
			else break;
		}
		
		// output
		System.out.println(answer);
		br.close();
	}
	
	// BFS
	private static boolean bfs(int sr, int sc) {
		// init
		boolean result = false;
		int sum = 0;
		int cnt = 0;
		Queue<int[]> q = new ArrayDeque<>();
		List<int[]> connected = new ArrayList<>();
		
		q.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		// loop
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			
			// 현재 국가
			int front = map[r][c];
			sum += front;
			cnt++;
			connected.add(curr);
			
			// delta 4
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				if(visited[nr][nc]) continue;
				
				// 대상 국가
				int back = map[nr][nc];
				
				// 국경 개방 불가
				int gap = Math.abs(front - back);
				if(gap < L || gap > R) continue;
				
				q.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
				
				// 국경 개방 발생
				result = true;
			}
		}
		
		// move
		int tmp = sum / cnt;
		for(int[] pos : connected) {
			map[pos[0]][pos[1]] = tmp;
		}
		
		return result;
	}
}
