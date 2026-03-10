package swea.d5.p1247;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int minPath, N;
    static int[][] loc;
    static boolean[] visited;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	minPath = Integer.MAX_VALUE;
        	N = Integer.parseInt(br.readLine());
        	loc = new int[N + 2][2];
        	visited = new boolean[N + 2];
        	
        	// input
        	st = new StringTokenizer(br.readLine());
        	for(int i=0; i<N + 2; i++) {
        		loc[i][0] = Integer.parseInt(st.nextToken());
        		loc[i][1] = Integer.parseInt(st.nextToken());
        	}
            
            // solve
        	dfs(0, 0, 0);

            // answer
            sb.append("#").append(tc).append(" ").append(minPath).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }
    
    // DFS
    private static void dfs(int idx, int cnt, int sum) {
    	// pruning
    	if(sum >= minPath) return;
    	
    	// base
    	if(cnt == N) {
    		sum += getDist(loc[idx][0], loc[idx][1], loc[1][0], loc[1][1]);
    		minPath = Math.min(sum, minPath);
    	}
    	
    	// recursion
    	for(int i=2; i<N+2; i++) {
    		if(visited[i]) continue;
    		visited[i] = true;
    		dfs(i, cnt + 1, sum + getDist(loc[idx][0], loc[idx][1], loc[i][0], loc[i][1]));
    		visited[i] = false;
    	}
    }
    
    // get Dist
    private static int getDist(int x1, int y1, int x2, int y2) {
    	return Math.abs(x1 - x2) + Math.abs(y1 - y2); 
    }
}