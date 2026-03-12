package baekjoon.ssafy.boj1504;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static final int INF = 1_000_000_000;
	
	static int answer, path1, path2, N, E, v1, v2;
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
		// init
		answer = 0;
		path1 = 0;
		path2 = 0;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		for(int i=1; i<=N; i++) graph[i] = new ArrayList<>();
		
		// input
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			// 무방향
			graph[u].add(new Node(v, w));
			graph[v].add(new Node(u, w));
		}
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		// solve
		if(!dijkstra(1, v1, 1) || !dijkstra(v1, v2, 1) || !dijkstra(v2, N, 1)) path1 = INF;
		if(!dijkstra(1, v2, 2) || !dijkstra(v2, v1, 2) || !dijkstra(v1, N, 2)) path2 = INF;
		answer = Math.min(path1, path2);
		if(answer == INF) answer = -1;
		
		// output
		System.out.println(answer);
		br.close();
	}

	// dijkstra
	private static boolean dijkstra(int s, int e, int path) {
		// init
		int[] dist = new int[N+1];
		Arrays.fill(dist, INF);
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.w, b.w));
		
		// start
		dist[s] = 0;
		pq.offer(new Node(s, 0));
		
		// loop
		while(!pq.isEmpty()) {
			// cur
			Node cur = pq.poll();
			
			// check
			if(cur.w > dist[cur.v]) continue;
			if(cur.v == e) {
				if(path == 1) path1 += cur.w;
				if(path == 2) path2 += cur.w;
				return true;
			}
			
			// next
			for(Node next : graph[cur.v]) {
				int nd = cur.w + next.w;
				if(nd < dist[next.v]) {
					dist[next.v] = nd; 
					pq.offer(new Node(next.v, nd));
				}
			}
		}
		
		return false;
	}
}