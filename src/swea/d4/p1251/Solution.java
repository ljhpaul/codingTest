package swea.d4.p1251;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int N;
    static double E;
    static int[][] adj, pos;
    static final long INF = Long.MAX_VALUE;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	N = Integer.parseInt(br.readLine());
        	adj = new int[N+1][N+1];
        	pos = new int[N+1][2];
        	
        	// input
        	st = new StringTokenizer(br.readLine());
        	for(int i=1; i<=N; i++) pos[i][0] = Integer.parseInt(st.nextToken());
        	st = new StringTokenizer(br.readLine());
        	for(int i=1; i<=N; i++) pos[i][1] = Integer.parseInt(st.nextToken());
        	E = Double.parseDouble(br.readLine());
        	
            // solve
        	long answer = primMST();

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        // output
        System.out.println(sb);
        br.close();
    }
    
    // prim MST - Matrix
    private static long primMST() {
    	// init
    	long result = 0;
    	boolean[] visited = new boolean[N+1];
    	long[] minEdge = new long[N+1];
    	Arrays.fill(minEdge, INF);
    	
    	// first vertex
    	minEdge[1] = 0;
    	
    	// loop
    	for(int i=0; i<N; i++) {
    		int u = -1;
    		long min = INF;
    		
    		// 최소 비용 간선의 정점 선택
    		for(int v=1; v<=N; v++) {
    			if(!visited[v] && minEdge[v] < min) {
    				u = v;
    				min = minEdge[v];
    			}
    		}
    		
    		// 연결 그래프가 아니면 실패
    		if(u == -1) return -1;
    		
    		// MST에 추가
    		result += minEdge[u];
    		visited[u] = true;
    		
    		// 해당 정점으로 minEdge 갱신
    		for(int v=1; v<=N; v++) {
    			long dx = pos[u][0] - pos[v][0];
    			long dy = pos[u][1] - pos[v][1];
    			long dist = dx*dx + dy*dy;
    			if(!visited[v] && dist < minEdge[v]) {
    				minEdge[v] = dist;
    			}
    		}
    	}
    	
    	// return
    	result = Math.round(E * result);
    	return result;
    }
}