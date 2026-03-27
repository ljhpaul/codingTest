package baekjoon.algorithm.segmenttree.boj14438;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static int[] arr, tree;
	
	static final int INF = 1_000_000_000;

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		tree = new int[4 * N];
		
		// input
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken()); 
		}

		// solve
		build(1, 1, N);
		int M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int mode = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// function (1:update, 2:query-min)
			if(mode == 1) update(1, 1, N, a, b);
			else if(mode == 2) answer.append(query(1, 1, N, a, b)).append("\n");
		}

		// output
		System.out.println(answer);
		br.close();
	}
	
	// build(node, start, end)
	private static int build(int node, int start, int end) {
		// 리프 도달
		if(start == end) tree[node] = arr[start];
		// 좌우 분기
		else {
			int mid = (start + end) / 2;
			tree[node] = Math.min(build(node * 2, start, mid),
					build(node * 2 + 1, mid + 1 , end));
		}
		// 값 반환
		return tree[node];
	}
	
	// query(node, start, end, left, right)
	private static int query(int node, int start, int end, int left, int right) {
		// 완전 불포함
		if(end < left || right < start) return INF;
		// 완전 포함
		else if(left <= start && end <= right) return tree[node];
		// 일부 겹침 -> 좌우 분기
		else {
			int mid = (start + end) / 2;
			return Math.min(query(node * 2, start, mid, left, right), 
					query(node * 2 + 1, mid + 1, end, left, right));
		}
	}
	
	// query(node, start, end, idx, value)
	private static void update(int node, int start, int end, int idx, int value) {
		// 리프 노드
		if(start == end) tree[node] = value;
		// 리프 아님
		else {
			int mid = (start + end) / 2;
			// idx가 왼쪽
			if(idx <= mid) update(node * 2, start, mid, idx, value);
			// idx가 오른쪽
			else update(node * 2 + 1, mid + 1, end, idx, value);
			// 갱신
			tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
		}
	}
}