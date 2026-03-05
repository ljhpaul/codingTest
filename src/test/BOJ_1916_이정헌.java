package test;

import java.io.*;
import java.util.*;

public class BOJ_1916_이정헌 {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int answer, N, M;
	static ArrayList<Node>[] graph;
	
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
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		graph = new ArrayList[N + 1];
		for(int i=1; i<N+1; i++) graph[i] = new ArrayList<>();
		
		// input
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[s].add(new Node(e, w));
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		// solve
		djikstra(start, end);
		
		// output
		System.out.println(answer);
		br.close();
	}
	
	// Djikstra
	private static void djikstra(int start, int end) {
		// init
		int[] dist = new int[N+1];
		Arrays.fill(dist, INF);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		// start vertex
		pq.offer(new Node(start, 0));
		dist[start] = 0;
		
		// loop
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			// PQ에 남아있던 더 긴 경로(구버전)라면 스킵
			if(dist[curr.to] < curr.cost) continue;
			
			// 목적지라면 최단거리 확정 → 종료
			if(curr.to == end) break;
			
			for(Node next : graph[curr.to]) {
				int nd = curr.cost + next.cost;
				
				if(dist[next.to] <= nd) continue;
				
				pq.offer(new Node(next.to, nd));
				dist[next.to] = nd;
			}
		}
		
		// set answer
		answer = dist[end];
	}
	
}