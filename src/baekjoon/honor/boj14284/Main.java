package baekjoon.honor.boj14284;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int answer, n, m, s, t;
	static ArrayList<Node>[] graph;
	static final int INF = Integer.MAX_VALUE;
	
	// Node class
	static class Node implements Comparable<Node> {
		int to;
		int cost;
		
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = 0;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new ArrayList[n + 1];
		for(int i=0; i<n+1; i++) graph[i] = new ArrayList<>();
		
		// input
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[u].add(new Node(v, w));
			graph[v].add(new Node(u, w));
		}
		
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		// solve
		djikstra(s);

		// output
		System.out.println(answer);
		br.close();
	}

	// Djikstra
	private static void djikstra(int start) {
		// init
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[n + 1];
		Arrays.fill(dist, INF);
		
		// start vertex
		pq.offer(new Node(start, 0));
		dist[start] = 0;
		
		// loop
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			if(curr.to == t) {
				answer = curr.cost;
				return;
			}
			
			for(Node next : graph[curr.to]) {
				int nd = curr.cost + next.cost;
				if(nd < dist[next.to]) {
					pq.offer(new Node(next.to, nd));
					dist[next.to] = nd;
				}
			}
		}
	}

}