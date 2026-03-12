package baekjoon.ssafy.boj11779;

import java.io.*;
import java.util.*;

public class Main2 {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static final int INF = Integer.MAX_VALUE;

	static StringBuilder answer;
	static int n, m, s, e;
	static ArrayList<Node>[] graph;
	
	// Node
	static class Node {
		int v, w, cnt;
		List<Integer> path;

		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}

		public Node(int v, int w, int cnt, List<Integer> path) {
			super();
			this.v = v;
			this.w = w;
			this.cnt = cnt;
			this.path = path;
		}
	}
	
	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		graph = new ArrayList[n+1];
		for(int i=1; i<=n; i++) graph[i] = new ArrayList<>();
		
		// input
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[u].add(new Node(v, w));
		}
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		// solve
		dijkstra();
		
		// output
		System.out.println(answer);
		br.close();
	}

	// Dijkstra
	private static void dijkstra() {
		// init
		int[] dist = new int[n+1];
		Arrays.fill(dist, INF);
		List<Integer> path = new ArrayList<>();
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.w - b.w);
		
		// first vertex
		pq.offer(new Node(s, 0, 1, new ArrayList<>()));
		dist[s] = 0;
		
		// loop
		while(!pq.isEmpty()) {
			// 현재까지의 누적 상태
			Node cur = pq.poll();
			
			if(cur.w > dist[cur.v]) continue;
			if(cur.v == e) path = cur.path;
			
			// 현재 정점에서 갈 수 있는 도시 확인
			for(Node next : graph[cur.v]) {
				int nd = cur.w + next.w;
				if(nd < dist[next.v]) {
					List<Integer> nextPath = new ArrayList<>();
					for(int v : cur.path) nextPath.add(v);
					nextPath.add(next.v);
					pq.offer(new Node(next.v, nd, cur.cnt + 1, nextPath));
					dist[next.v] = nd; 
				}
			}
		}
		
		// set answer
		answer.append(dist[e]).append("\n");
		answer.append(path.size() + 1).append("\n");
		answer.append(s).append(" ");
		for(int v : path) {
			answer.append(v).append(" ");
		}
	}
}