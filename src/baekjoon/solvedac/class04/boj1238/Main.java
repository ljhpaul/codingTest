package baekjoon.solvedac.class04.boj1238;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int maxTime, N, M, X;
	
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

		ArrayList<Node>[] graph = new ArrayList[N + 1];
		ArrayList<Node>[] rGraph = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++)
			graph[i] = new ArrayList<>();
		for (int i = 1; i < N + 1; i++)
			rGraph[i] = new ArrayList<>();

		int[] dist = new int[N + 1];
		int[] rDist = new int[N + 1];
		Arrays.fill(dist, INF);
		Arrays.fill(rDist, INF);

		// input
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[u].add(new Node(v, w));
			rGraph[v].add(new Node(u, w));
		}

		// solve
		getDist(graph, dist);
		getDist(rGraph, rDist);
		getMaxTime(dist, rDist);

		// output
		System.out.println(maxTime);
		br.close();
	}

	// get dist
	private static void getDist(ArrayList<Node>[] graph, int[] dist) {
		// init
		PriorityQueue<Node> pq = new PriorityQueue<>();

		// start vertex
		pq.offer(new Node(X, 0));
		dist[X] = 0;

		// loop
		while (!pq.isEmpty()) {
			Node curr = pq.poll();

			if (curr.cost > dist[curr.to])
				continue;

			for (Node next : graph[curr.to]) {
				int nd = curr.cost + next.cost;

				if (nd >= dist[next.to])
					continue;

				pq.offer(new Node(next.to, nd));
				dist[next.to] = nd;
			}
		}
	}

	// get maxTime
	private static void getMaxTime(int[] dist, int[] rDist) {
		// 시작점 : 1 ~ N번 마을
		for (int s = 1; s < N + 1; s++) {
			if (s == X)
				continue;
			maxTime = Math.max(dist[s] + rDist[s], maxTime);
		}
	}
}