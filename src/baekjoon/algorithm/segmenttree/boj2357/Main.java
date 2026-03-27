package baekjoon.algorithm.segmenttree.boj2357;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static int[] arr;
	static int[][] tree;
	static int N, M;
	
	static final int INF = 1_000_000_000;
	
	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N + 1];
		tree = new int[4 * N][2];
		for(int[] line : tree) line[0] = INF;
		
		// input
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		// solve
		build(1, 1, N);
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int[] tmp = query(1, 1, N, a, b);
			answer.append(tmp[0]).append(" ").append(tmp[1]).append("\n");
		}

		// output
		System.out.println(answer);
		br.close();
	}
	
	// build(node, start, end)
	private static int[] build(int node, int start, int end) {
		// 1.리프 도달
		if(start == end) {
			tree[node][0] = arr[start];
			tree[node][1] = arr[start];
		}
		// 2.리프 아님 -> 좌우 분기
		else {
			int mid = (start + end) / 2;
			int[] l = build(node * 2, start, mid);
			int[] r = build(node * 2 + 1, mid + 1, end);
			tree[node][0] = Math.min(l[0], r[0]);
			tree[node][1] = Math.max(l[1], r[1]);
		}
		// 3.값 반환
		return tree[node];
	}
	
	// query(node, start, end, left, right) -> [min, max]
	private static int[] query(int node, int start, int end, int left, int right) {
		// 1.완전 불포함
		if(end < left || right < start) return new int[] {INF, 0};
		// 2.완전 포함
		else if(left <= start && end <= right) return tree[node];
		// 3.일부 겹침 -> 좌우 분기
		else {
			int mid = (start + end) / 2;
			int[] l = query(node * 2, start, mid, left, right);
			int[] r = query(node * 2 + 1, mid + 1, end, left, right);
			return new int[] {Math.min(l[0], r[0]), Math.max(l[1], r[1])};
		}
	}

}