package swea.trialSW.p1767;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int maxCoreCnt, minLineSum, N;
    static int[][] map;
    static List<int[]> cores;
    
    static int[] dr = {1, -1, 0, 0}; // 상하좌우
    static int[] dc = {0, 0, -1, 1};

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	maxCoreCnt = 0;
        	minLineSum = Integer.MAX_VALUE;
        	N = Integer.parseInt(br.readLine());
        	map = new int[N][N];
        	cores = new ArrayList<>();
        	
        	// input
        	for(int i=0; i<N; i++) {
        		st = new StringTokenizer(br.readLine());
        		for(int j=0; j<N; j++) {
        			map[i][j] = Integer.parseInt(st.nextToken());
        			// 가장자리가 아닌 코어 리스트에 넣기
        			if(i > 0 && i < N-1 && j > 0 && j < N-1) {
        				if(map[i][j] == 1) {
        					cores.add(new int[] {i, j});
        				}
        			}
        		}
        	}
            
            // solve
        	dfs(0, map, 0, 0);

            // answer
            sb.append("#").append(tc).append(" ").append(minLineSum).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }
    
    // dfs
    private static void dfs(int idx, int[][] map, int coreCnt, int lineSum) {
    	// base
    	if(idx == cores.size()) {
			if(coreCnt > maxCoreCnt) {
				maxCoreCnt = coreCnt;
				minLineSum = lineSum;
			} else if(coreCnt == maxCoreCnt) {
				minLineSum = Math.min(lineSum, minLineSum);
			}
    		return;
    	}
    	
    	// pruning
    	int remain = cores.size() - idx;
    	if(coreCnt + remain < maxCoreCnt) return;
    	
    	// recursion
    	int r = cores.get(idx)[0];
    	int c = cores.get(idx)[1];
    	for(int d=0; d<4; d++) {
        	int[][] next = copyMap(map);
        	int line = setLine(next, r, c, d);
    		if(line > 0) {	// 전선 연결 성공
        		dfs(idx + 1, next, coreCnt + 1, lineSum + line);
    		}
    	}
    	dfs(idx + 1, map, coreCnt, lineSum); // 연결을 안 하는 경우
    }
    
    // copy map
    private static int[][] copyMap(int[][] map) {
    	int[][] copy = new int[N][N];
    	for(int i=0; i<N; i++) {
    		for(int j=0; j<N; j++) {
    			copy[i][j] = map[i][j];
    		}
    	}
    	return copy;
    }
    
    // set line
    private static int setLine(int[][] map, int r, int c, int d) {
    	int cnt = 0;
    	while(true) {
    		r += dr[d];
    		c += dc[d];
    		if(map[r][c] == 0) {
    			map[r][c] = 2;	// 선 배치
    			cnt++;
    			if(r == 0 || c == 0 || r == N-1 || c == N-1) {
    				break;	// 선 배치 성공
    			}
    		} else {
    			cnt = 0;
    			break;	// 선 배치 실패
    		}
    	}
    	return cnt;
    }
}
/* 09:30 ~ 10:08 */