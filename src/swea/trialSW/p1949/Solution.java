package swea.trialSW.p1949;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int maxLen, N, K;
    static int[][] map;
    static boolean[][] visited;
    
    static int[] dr = {-1, 1, 0, 0}; // 상하좌우
    static int[] dc = {0, 0, -1, 1};

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	maxLen = 1;
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	K = Integer.parseInt(st.nextToken());
        	int[][] initialMap = new int[N][N];
        	int topHeight = 0;

            // input
        	for(int i=0; i<N; i++) {
        		st = new StringTokenizer(br.readLine());
        		for(int j=0; j<N; j++) {
        			initialMap[i][j] = Integer.parseInt(st.nextToken());
        			topHeight = Math.max(initialMap[i][j], topHeight);
        		}
        	}
            
            // solve
        	for(int r=0; r<N; r++) {
        		for(int c=0; c<N; c++) {
        			if(initialMap[r][c] == topHeight) {
                    	map = copyMap(initialMap);
                    	visited = new boolean[N][N];
                    	visited[r][c] = true;
        	        	dfs(r, c, 1, false);
        			}
        		}
        	}

            // answer
            sb.append("#").append(tc).append(" ").append(maxLen).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }
    
    // dfs
    private static void dfs(int r, int c, int len, boolean isCut) {
    	// base
    	maxLen = Math.max(len, maxLen);
    	
    	// recursion
    	for(int d=0; d<4; d++) {
    		// 다음 좌표
    		int nr = r + dr[d];
    		int nc = c + dc[d];
    		if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;	// 유효하지 않은 좌표면 패스
    		if(visited[nr][nc]) continue;	// 방문했으면 패스
    		
    		// 공사 가능
    		if(!isCut) {
    			for(int i=1; i<=K; i++) {
    				map[nr][nc] -= i;	// i만큼 공사
    				visited[nr][nc] = true;
    				if(map[nr][nc] < map[r][c]) {	// 공사 후 낮아졌으면 진입
    					dfs(nr, nc, len + 1, true);
    				}
    				map[nr][nc] += i;	// 공사 원복
    				visited[nr][nc] = false;
    			}
    		}
    		
    		// 공사 안하고 진입
    		if(map[nr][nc] >= map[r][c]) continue;	// 현재 위치보다 같거나 높으면 패스
    		visited[nr][nc] = true;
    		dfs(nr, nc, len + 1, isCut);
    		visited[nr][nc] = false;
    	}
    }
    
    // copy map
    private static int[][] copyMap(int[][] target) {
    	int[][] copy = new int[N][N];
    	for(int r=0; r<N; r++) {
    		for(int c=0; c<N; c++) {
    			copy[r][c] = target[r][c];
    		}
    	}
    	return copy;
    }
}