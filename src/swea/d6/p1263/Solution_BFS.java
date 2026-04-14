package swea.d6.p1263;

import java.io.*;
import java.util.*;

class Solution_BFS {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int answer, N;
    static ArrayList<Integer>[] graph;
    
    static final int INF = 1_000_000_000;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	answer = INF;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            graph = new ArrayList[N];

            // input
            for(int r = 0; r < N; r++) {
            	graph[r] = new ArrayList<>();
            	for(int c = 0; c < N; c++) {
            		int tmp = Integer.parseInt(st.nextToken());
            		if(tmp > 0) graph[r].add(c);
            	}
            }
            
            // solve
            for(int start = 0; start < N; start++) {
            	answer = Math.min(getCC(start), answer);
            }

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

	// BFS * N
	private static int getCC(int start) {
		// init
		int cc = 0;
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N];
		int[] dist = new int[N];
		
		// start
		q.add(start);
		visited[start] = true;
		dist[start] = 0;
		
		// loop
		while(!q.isEmpty()) {
			// curr
			int curr = q.poll();
			
			// next
			for(int next : graph[curr]) {
				if(!visited[next]) {
					visited[next] = true;
					dist[next] = dist[curr] + 1;
					q.add(next);
				}
			}
		}
		
		// calc cc
		for(int i = 0; i < N; i++) {
			cc += dist[i];
		}
		
		// return
		return cc;
	}
}