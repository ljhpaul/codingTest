package baekjoon.roadmap.week01.boj1697;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static final int MAX_SIZE = 100000;

	static boolean[] visited = new boolean[MAX_SIZE + 1];
	static int[] dist = new int[MAX_SIZE + 1];
	static int n;
	static int k;

	// main
	public static void main(String[] args) throws IOException {
		// init
		int answer = 0;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		// get answer
		if(n >= k) {
			System.out.println(n - k);
			return;
		}
		BFS();
		answer = dist[k];


		// output
		System.out.println(answer);
	}



	private static void BFS() {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(n);
		visited[n] = true;
		dist[n] = 0;

		while(!q.isEmpty()) {
			int now = q.poll();

			if(now == k) {
				return;
			}

			int n1 = now - 1;
			int n2 = now + 1;
			int n3 = now * 2;

			if(n1 >= 0 && !visited[n1]) {
				q.add(n1);
				visited[n1] = true;
				dist[n1] = dist[now] + 1;
			}
			if(n2 <= MAX_SIZE && !visited[n2]) {
				q.add(n2);
				visited[n2] = true;
				dist[n2] = dist[now] + 1;
			}
			if(n3 <= MAX_SIZE && !visited[n3]) {
				q.add(n3);
				visited[n3] = true;
				dist[n3] = dist[now] + 1;
			}
		}
	}
}