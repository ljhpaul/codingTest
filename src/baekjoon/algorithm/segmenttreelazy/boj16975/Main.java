package baekjoon.algorithm.segmenttreelazy.boj16975;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static int[] arr;
	static long[] lazy, tree;

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		lazy = new long[4 * N];
		tree = new long[4 * N];
		
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
			if(mode == 1) {
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				update(1, 1, N, left, right, value);
			} else if(mode == 2) {
				int idx = Integer.parseInt(st.nextToken());
				answer.append(query(1, 1, N, idx)).append("\n");
			}
		}

		// output
		System.out.println(answer);
		br.close();
	}
	
	// build
	private static void build(int node, int start, int end) {
		// 리프
		if(start == end) tree[node] = arr[start];
		// 리프 아님 -> 좌우
		else {
			int mid = (start + end) / 2;
			build(node * 2, start, mid);
			build(node * 2 + 1, mid + 1, end);
		}
	}
	
	// query
	private static long query(int node, int start, int end, int idx) {
		// 1.리프
		if(start == end) return lazy[node] + tree[node];
		// 2.리프 아님
		else {
			int mid = (start + end) / 2;
			// idx -> 왼쪽 영역
			if(idx <= mid) return lazy[node] + query(node * 2, start, mid, idx);
			// idx -> 오른쪽 영역
			else return lazy[node] + query(node * 2 + 1, mid + 1, end, idx);
		}
	}
	
	// update
	private static void update(int node, int start, int end, int left, int right, int value) {
		// 1.완전 불포함
		if(end < left || right < start) return;
		// 2.완전 포함
		else if(left <= start && end <= right) {
			lazy[node] += value;
			return;
		}
		// 3.일부 겹침 -> 좌우 분기
		else {
			int mid = (start + end) / 2;
			update(node * 2, start, mid, left, right, value);
			update(node * 2 + 1, mid + 1, end, left, right, value);
		}
	}
}