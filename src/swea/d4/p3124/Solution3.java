package swea.d4.p3124;

import java.io.*;
import java.util.*;

class Solution3 {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int V, E;
    static int[][] adj;
    static final int INF = Integer.MAX_VALUE;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	st = new StringTokenizer(br.readLine());
        	V = Integer.parseInt(st.nextToken());
        	E = Integer.parseInt(st.nextToken());
        	adj = new int[V+1][V+1];
        	
        	// input
        	for(int i=0; i<E; i++) {
        		st = new StringTokenizer(br.readLine());
            	int u = Integer.parseInt(st.nextToken());
            	int v = Integer.parseInt(st.nextToken());
            	int w = Integer.parseInt(st.nextToken());
            	// 무방향 그래프
            	adj[u][v] = w;
            	adj[v][u] = w;
        	}

            // solve
        	long wSum = primMST();

            // answer
            sb.append("#").append(tc).append(" ").append(wSum).append("\n");
        }
        // output
        System.out.println(sb);
        br.close();
    }

    // Prim MST - Matrix
	private static long primMST() {
		// init
		long wSum = 0L;
		boolean[] visited = new boolean[V+1];
		int[] minEdge = new int[V+1];
		Arrays.fill(minEdge, INF);
		
		// start vertex
		minEdge[1] = 0;
		
		// loop
		for(int i=0; i<V ; i++) {
			int min = INF;
			int u = -1;
			
			// MST 비포함 정점 중 최소 비용 간선의 정점 선택
			for(int v=1; v<=V; v++) {
				if(!visited[v] && minEdge[v] < min) {
					min = minEdge[v];
					u = v;
				}
			}
			
			// 연결 그래프 아니면 실패
			if(u == -1) return -1;
			
			// 고른 정점을 MST에 추가
			wSum += min;
			visited[u] = true;
			
			// 새 정점으로 다른 정점의 최소 연결 비용 갱신
			for(int v=1; v<=V; v++) {
				if(!visited[v] && adj[u][v] < minEdge[v]) {
					minEdge[v] = adj[u][v];
				}
			}
		}
		
		// return
		return wSum;
	}
}
/*
Prim MST - Matrix
1. 인접 행렬, 방문 배열, 최소 비용 배열 초기화
	- int[][] adj
	- boolean[] visited
	- int[] minEdge <- INF
2. 시작 정점 선택
	- minEdge[0] = 0;
3. 모든 정점 순회
	- minEdge가 최소 정점 선택
	- MST에 해당 정점 추가(방문 처리 및 합계 갱신)
	- 해당 정점으로 다른 정점들의 minEdge 갱신
*/