package baekjoon.algorithm.segmenttree.boj1275;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static int N, Q;
	static long[] nums;
	static long[] tree;

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		nums = new long[N];
		tree = new long[4 * N];
		
		// input
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		// solve
		build(1, 0, N - 1);
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken());
			answer.append(query(1, 0, N - 1, Math.min(x, y), Math.max(x, y))).append("\n");
			update(1, 0, N - 1, a, b);
		}
		
		// output
		System.out.println(answer);
		br.close();
	}

	// build
	private static long build(int node, int start, int end) {
		// 리프 노드 도달시 값 대입
		if(start == end) {
			tree[node] = nums[start];
		}
		// 좌우 분기
		else {
			int mid = (start + end) / 2;
			tree[node] = build(node * 2, start, mid) + build(node * 2 + 1, mid + 1, end);
		}
		// 현재 노드 반환
		return tree[node];
	}

	// query
	private static long query(int node, int start, int end, int left, int right) {
		// 완전 불포함
		if(end < left || right < start) {
			return 0;
		}
		// 완전 포함
		else if(left <= start && end <= right) {
			return tree[node];
		}
		// 일부 겹치는 경우 : 좌우 분기
		else {
			int mid = (start + end) / 2;
			return query(node * 2, start, mid, left, right)
					+ query(node * 2 + 1, mid + 1, end, left, right);
		}
	}
	
	// update
	private static void update(int node, int start, int end, int idx, int value) {
		// 리프 도달 시 값 대입
		if(start == end) {
			tree[node] = value;
		}
		// 리프가 아닌 경우
		else {
			int mid = (start + end) / 2;
			// idx가 왼쪽 영역에 위치
			if(idx <= mid) update(node * 2, start, mid, idx, value);
			// idx가 오른쪽 영역에 위치
			else update(node * 2 + 1, mid + 1, end, idx, value);
			// 값 갱신
			tree[node] = tree[node * 2] + tree[node * 2 + 1];
		}
	}
}