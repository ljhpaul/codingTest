package baekjoon.solvedac.class04.boj1238;

import java.io.*;
import java.util.*;

public class Main2 {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int maxTime, N, M, X;
	static ArrayList<Node>[] graph;
	static int[][] dist;
	
	static final int INF = Integer.MAX_VALUE;

	// Node class
	static class Node implements Comparable<Node> {
		int to, cost;
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
		maxTime = 0;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];
		for(int i=1; i<N+1; i++) graph[i] = new ArrayList<>();
		dist = new int[N + 1][N + 1];
		for(int[] line : dist) Arrays.fill(line, INF); 
		
		// input
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[u].add(new Node(v, w));
		}

		// solve
		getDist();
		getMaxTime();

		// output
		System.out.println(maxTime);
		br.close();
	}

	// get dist : Djikstra * N
	private static void getDist() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		// 시작점 : 1 ~ N번 마을
		for(int s=1; s<N+1; s++) {
			// init
			pq.clear();
			
			// start vertex
			pq.offer(new Node(s, 0));
			dist[s][0] = 0;
			
			// loop
			while(!pq.isEmpty()) {
				Node curr = pq.poll();
				
				if(curr.cost > dist[s][curr.to]) continue;
				
				for(Node next : graph[curr.to]) {
					int nd = curr.cost + next.cost;
					
					if(nd >= dist[s][next.to]) continue;
					
					pq.offer(new Node(next.to, nd));
					dist[s][next.to] = nd; 
				}
			}
		}
	}

	// get maxTime
	private static void getMaxTime() {
		// 시작점 : 1 ~ N번 마을
		for(int s=1; s<N+1; s++) {
			if(s == X) continue;
			maxTime = Math.max(dist[s][X] + dist[X][s], maxTime);
		}
	}

}