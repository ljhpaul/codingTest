package swea.d4.p1868;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int answer, N;
    static char[][] board;
    static int[][] cnts;
    static boolean[][] visited;
    
    static int[] dr8 = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc8 = {0, 1, 1, 1, 0, -1, -1, -1};       

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	answer = 0;
        	N = Integer.parseInt(br.readLine());
        	board = new char[N][N];
        	visited = new boolean[N][N];
        	
        	// input
        	for(int r=0; r<N; r++) {
        		board[r] = br.readLine().toCharArray();
        	}

            // solve
        	solve();
            

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }
    
    // solve
    private static void solve() {
    	setCount();
    	
    	// 0일 때만 bfs 카운트 진행
    	for(int r=0; r<N; r++) {
    		for(int c=0; c<N; c++) {
    			if(board[r][c] == '0' && !visited[r][c]) {
    				bfs(r, c);
    				answer++;
    			}
    		}
    	}
    	
    	// 남은 하나짜리 세기
    	for(int r=0; r<N; r++) {
    		for(int c=0; c<N; c++) {
    			if(board[r][c] != '*' && board[r][c] != '0' && !visited[r][c]) answer++;
    		}
    	}
    }

	// set count : 지뢰 카운트 세팅
	private static void setCount() {
    	for(int r=0; r<N; r++) {
    		for(int c=0; c<N; c++) {
    			if(board[r][c] == '*') continue;
    			int cnt = 0;
    			for(int d=0; d<8; d++) {
    				int nr = r + dr8[d];
    				int nc = c + dc8[d];
    				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
    				if(board[nr][nc] == '*') {
    					cnt++;
    				}
    			}
    			board[r][c] = (char) (cnt + '0');
    		}
    	}
	}
	
	// BFS
    private static void bfs(int sr, int sc) {
		// init
    	Queue<int[]> q = new ArrayDeque<>();
    	
    	q.offer(new int[] {sr, sc});
    	visited[sr][sc] = true;
    	
    	// loop
    	while(!q.isEmpty()) {
    		int[] curr = q.poll();
    		int r = curr[0];
    		int c = curr[1];
    		
    		for(int d=0; d<8; d++) {
				int nr = r + dr8[d];
				int nc = c + dc8[d];
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				if(visited[nr][nc]) continue;
				
				visited[nr][nc] = true;
				
				if(board[r][c] == '0' && board[nr][nc] == '0') {
					q.offer(new int[] {nr, nc});
				}
			}
    	}
	}
}