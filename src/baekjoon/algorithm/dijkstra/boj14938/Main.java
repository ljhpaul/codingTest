package baekjoon.algorithm.dijkstra.boj14938;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int answer, n, m, r;
	static int[] scores;
	static ArrayList<Node>[] graph;
	
	static final int INF = 1_000_000_000;
	
	// Node
	static class Node {
		int v, w, score;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
			this.score = 0;
		}
		
		public Node(int v, int w, int score) {
			this.v = v;
			this.w = w;
			this.score = score;
		}
	}

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = 0;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		scores = new int[n + 1];
		graph = new ArrayList[n + 1];
		for(int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
		
		// input
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			scores[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			// 양방향
			graph[u].add(new Node(v, w));
			graph[v].add(new Node(u, w));
		}

		// solve
		for(int start = 1; start <= n; start++) {
			dijkstra(start);
		}

		// output
		System.out.println(answer);
		br.close();
	}
	
	// dijkstra
	private static void dijkstra(int start) {
		// init
		int scoreSum = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.w, b.w));
		int[] dist = new int[n + 1];
		Arrays.fill(dist, INF);
		
		// start
		pq.offer(new Node(start, 0, scores[start]));
		dist[start] = 0;
		
		// loop
		while(!pq.isEmpty()) {
			// curr
			Node curr = pq.poll();
			
			// check
			if(curr.w > m) break;
			if(curr.w > dist[curr.v]) continue;
			scoreSum += curr.score;
			
			// next
			for(Node next : graph[curr.v]) {
				int nd = curr.w + next.w;
				
				if(dist[next.v] > nd) {
					pq.offer(new Node(next.v, nd, scores[next.v]));
					dist[next.v] = nd;
				}
			}
		}
		
		// renew
		answer = Math.max(answer, scoreSum);
	}

}