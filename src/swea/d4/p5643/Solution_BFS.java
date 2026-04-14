package swea.d4.p5643;

import java.io.*;
import java.util.*;

class Solution_BFS {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int answer, N, M;
    static ArrayList<Integer>[] graph;
    static int[] smaller, larger;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            answer = 0;
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());
            graph = new ArrayList[N + 1];
            for(int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
            smaller = new int[N + 1];
            larger = new int[N + 1];

            // input
            for(int i = 0; i < M; i++) {
            	st = new StringTokenizer(br.readLine());
            	int a = Integer.parseInt(st.nextToken());
            	int b = Integer.parseInt(st.nextToken());
            	graph[a].add(b);
            }
            
            // solve
            for(int start = 1; start <= N; start++) {
            	bfs(start);
            }
            for(int i = 1; i <= N; i++) {
            	if(smaller[i] + larger[i] == N - 1) answer++;
            }

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    // bfs
	private static void bfs(int start) {
		// init
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];
		
		// start
		q.add(start);
		visited[start] = true;
		
		// loop
		while(!q.isEmpty()) {
			// curr
			int curr = q.poll();
			
			// next
			for(int next : graph[curr]) {
				if(visited[next]) continue;
				q.add(next);
				smaller[next]++;
				larger[start]++;
				visited[next] = true;
			}
		}
	}
}