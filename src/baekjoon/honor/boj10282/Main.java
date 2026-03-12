package baekjoon.honor.boj10282;

import java.io.*;
import java.util.*;

class Main {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int INF = 1_000_000_000;
    
    static int N, D, C;
    static StringBuilder answer;
    static ArrayList<Node>[] graph;
    
    // Node
    static class Node {
    	int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
    }

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            answer = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            graph = new ArrayList[N + 1];
            for(int i=1; i<=N; i++) graph[i] = new ArrayList<>();

            // input
            for(int i=0; i<D; i++) {
            	st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                // 방향 그래프 : a가 b를 의존, b 감염 시 a 감염
                graph[b].add(new Node(a, s));
            }
            
            // solve
            dijkstra();

            // answer
            sb.append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    // dijkstra
	private static void dijkstra() {
		// init
		int cnt = 0;
		int time = 0;
		int[] dist = new int[N+1];
		Arrays.fill(dist, INF);
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.w, b.w));
		
		// first vertex
		dist[C] = 0;
		pq.offer(new Node(C, 0));
		
		// loop
		while(!pq.isEmpty()) {
			// cur
			Node cur = pq.poll();
			
			// check
			if(cur.w > dist[cur.v]) continue;
			cnt++;
			time = cur.w;
			
			// next
			for(Node next : graph[cur.v]) {
				int nd = cur.w + next.w;
				if(nd < dist[next.v]) {
					dist[next.v] = nd;
					pq.offer(new Node(next.v, nd));
				}
			}
		}
		
		// set answer
		answer.append(cnt).append(" ").append(time);
	}
}