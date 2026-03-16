package test;
import java.io.*;
import java.util.*;

public class Test {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int answer, N, M, K;
	static boolean[][] isGem;	// 마력석 여부
	static boolean[][] visited;
	
	static int[] dr = {-1, 1, 0, 0}; // 상하좌우(0123)
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		// test case
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			// init
			answer = 0;
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			isGem = new boolean[N][M];
			visited = new boolean[N][M];
			
			// input
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				isGem[r][c] = true;
			}
			
			// solve
			for(int r=0; r<N; r++) {
				for(int c=0; c<M; c++) {
					if(visited[r][c] || !isGem[r][c]) continue;
					bfs(r, c);
					answer++;
				}
			}
			
			// answer
			sb.append(answer).append("\n");
		}
		// output
		System.out.print(sb);
		br.close();
	}

	// BFS
	private static void bfs(int sr, int sc) {
		// init
		Queue<int[]> q = new ArrayDeque<>();
		
		// first
		q.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		// loop
		while(!q.isEmpty()) {
			// cur
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			// next
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if(visited[nr][nc] || !isGem[nr][nc]) continue;
				
				q.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
	}	
}