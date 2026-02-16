package swea.trialSW.p1949;

import java.io.*;
import java.util.*;

class Solution2 {
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
        	List<int[]> top = new ArrayList<>();
        	int topHeight = 0;
        	List<int[]> second = new ArrayList<>();
        	int secondHeight = 0;

            // input
        	for(int r=0; r<N; r++) {
        		st = new StringTokenizer(br.readLine());
        		for(int c=0; c<N; c++) {
        			int height = Integer.parseInt(st.nextToken());
        			initialMap[r][c] = height;
        			if(height > topHeight) {
        				second = top;
        				secondHeight = topHeight;
        				top = new ArrayList<>();
        				top.add(new int[] {r, c});
        				topHeight = height;
        			} else if(height == topHeight) {
        				top.add(new int[] {r, c});
        			}
        		}
        	}
            
            // solve
        	// 바로 진입
        	for(int[] pos : top) {
        		int r = pos[0];
        		int c = pos[1];
        		map = copyMap(initialMap);
                visited = new boolean[N][N];
                visited[r][c] = true;
    	        dfs(r, c, 1, false);
        	}
        	// 봉우리 공사
        	if(top.size() == 1) {
            	for(int k=topHeight-secondHeight; k<=K; k++) {
            		// 두번째 높이에서 시작
            		for(int[] pos : second) {
            			int r = pos[0];
            			int c = pos[1];
            			int cutR = top.get(0)[0];
                		int cutC = top.get(0)[1];
                		map = copyMap(initialMap);
                        visited = new boolean[N][N];
                        map[cutR][cutC] -= k;
                        visited[r][c] = true;
            	        dfs(r, c, 1, true);
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
    	if(len == N*N) return;
    	
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
/* 14:43 ~ 16:09 */