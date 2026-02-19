package swea.d4.p7733;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int maxCnt, maxTaste, N;
    static int[][] cheese;
    static boolean[][] isAte, visited;
    
    static int[] dr = {-1, 1, 0, 0}; // 상하좌우(0123)
    static int[] dc = {0, 0, -1, 1};

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	maxCnt = 1;	// 첫날은 무조건 한 덩어리
        	maxTaste = 0;
        	N = Integer.parseInt(br.readLine());
        	cheese = new int[N][N];
        	isAte = new boolean[N][N];
        	
        	// input
        	for(int r=0; r<N; r++) {
        		st = new StringTokenizer(br.readLine());
        		for(int c=0; c<N; c++) {
        			int taste = Integer.parseInt(st.nextToken());
        			cheese[r][c] = taste;
        			maxTaste = Math.max(taste, maxTaste);
        		}
        	}

            // solve
        	solve();

            // answer
            sb.append("#").append(tc).append(" ").append(maxCnt).append("\n");
        }
        // output
        System.out.println(sb);
        br.close();
    }

    // solve
	private static void solve() {
		// day flow
		for(int day=1; day<=maxTaste; day++) {
			// 1. ate cheese
			for(int r=0; r<N; r++) {
        		for(int c=0; c<N; c++) {
        			if(day == cheese[r][c]) {
        				isAte[r][c] = true;
        			}
        		}
        	}
			// 2. count cheese
			visited = new boolean[N][N];
			int cnt = 0;
			for(int r=0; r<N; r++) {
        		for(int c=0; c<N; c++) {
        			if(isAte[r][c] || visited[r][c]) continue;
        			bfs(r, c);
        			cnt++;
        		}
        	}
			maxCnt = Math.max(cnt, maxCnt);
		}
	}

	// bfs : count cheese
	private static void bfs(int sr, int sc) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			// delta 4 search
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue; // 좌표 유효성 검사
				if(isAte[nr][nc]) continue;	// 먹은 곳인지 확인
				if(visited[nr][nc]) continue;	// 이미 덩어리 체크한 곳인지 확인
				q.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
	}
}
