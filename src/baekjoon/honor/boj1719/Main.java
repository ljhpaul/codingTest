package baekjoon.honor.boj1719;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int n, m;
	static ArrayList<Edge>[] graph;
	static int[][] arr;
	
	static final int INF = Integer.MAX_VALUE;
	
	// Edge class
	static class Edge implements Comparable<Edge> {
		int v, w, adj;
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
			this.adj = 0;
		}
		public Edge(int v, int w, int adj) {
			this.v = v;
			this.w = w;
			this.adj = adj;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}
	

	// main
	public static void main(String[] args) throws IOException {
		// init
		StringBuilder answer = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new ArrayList[n + 1];
		for(int i=1; i<=n; i++) graph[i] = new ArrayList<>();
		arr = new int[n][n];
		
		// input
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[u].add(new Edge(v, w));
			graph[v].add(new Edge(u, w));			
		}

		// solve
		for(int s=1; s<=n; s++) {
			djikstra(s);
		}
		for(int[] line : arr) {
			for(int num : line) {
				if(num == 0) {
					answer.append('-').append(" ");
				} else {
					answer.append(num).append(" ");
				}
			}
			answer.append("\n");
		}

		// output
		System.out.println(answer);
		br.close();
	}

	// Djikstra
	private static void djikstra(int s) {
		// init
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int[] dist = new int[n + 1];
		Arrays.fill(dist, INF);
		
		// start vertex
		pq.offer(new Edge(s, 0));
		dist[s] = 0;
		
		// loop
		while(!pq.isEmpty()) {
			Edge curr = pq.poll();
			
			for(Edge next : graph[curr.v]) {
				int nd = curr.w + next.w;
				if(nd >= dist[next.v]) continue;
				if(curr.adj == 0) {
					pq.offer(new Edge(next.v, nd, next.v));
				} else {
					pq.offer(new Edge(next.v, nd, curr.adj));
				}
				dist[next.v] = nd;
				arr[s - 1][next.v - 1] = curr.adj == 0 ? next.v : curr.adj;
			}
		}
	}
}