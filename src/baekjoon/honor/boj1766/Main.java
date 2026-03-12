package baekjoon.honor.boj1766;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static int N, M;
	static int[] indegree;
	static List<Integer>[] graph;

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		indegree = new int[N+1];
		graph = new ArrayList[N+1];
		for(int i=1; i<=N; i++) graph[i] = new ArrayList<>();
		
		// input
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);
			indegree[to]++;
		}

		// solve
		topologicalSort();

		// output
		System.out.println(answer);
		br.close();
	}

	private static void topologicalSort() {
		// init
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		// add start
		for(int i=1; i<=N; i++) {
			if(indegree[i] == 0) {
				pq.offer(i);
			}
		}
		
		// loop
		while(!pq.isEmpty()) {
			// cur
			int cur = pq.poll();
			answer.append(cur).append(" ");
			
			// next
			for(int next : graph[cur]) {
				if(--indegree[next] == 0) {
					pq.offer(next);
				}
			}
		}
	}
}