package baekjoon.algorithm.segmenttreelazy.boj10999;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static long[] arr, tree, lazy;
	static int N, M, K;

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new long[N + 1];
		tree = new long[4 * N];
		lazy = new long[4 * N];
		
		// input
		for(int i = 1; i <= N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		// solve
		build(1, 1, N);
		for(int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int mode = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			// function (1:update, 2:query-sum)
			if(mode == 1) {
				long value = Long.parseLong(st.nextToken());
				update(1, 1, N, left, right, value);
			} else if(mode == 2) {
				answer.append(query(1, 1, N, left, right)).append("\n");
			}
		}

		// output
		System.out.println(answer);
		br.close();
	}

	// build(node, start, end)
	private static long build(int node, int start, int end) {
		// 1.리프 도달
		if(start == end) tree[node] = arr[start];
		// 2.좌우 분기
		else {
			int mid = (start + end) / 2;
			tree[node] = build(node * 2, start, mid)
					+ build(node * 2 + 1, mid + 1, end);
		}
		// 3.값 반환
		return tree[node];
	}
	
	// query(node, start, end, left, right)
	private static long query(int node, int start, int end, int left, int right) {
		// 1.lazy 처리
		if(lazy[node] != 0) {
			tree[node] += (end - start + 1) * lazy[node];
			if(start != end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}
			lazy[node] = 0;
		}
		// 2.완전 불포함
		if(end < left || right < start) return 0;
		// 3.완전 포함
		else if(left <= start && end <= right) return tree[node];
		// 4.일부 겹침 -> 좌우 분기
		else {
			int mid = (start + end) / 2;
			return query(node * 2, start, mid, left, right)
					+ query(node * 2 + 1, mid + 1, end, left, right);
		}
	}
	
	// update(node, start, end, left, right, value)
	private static void update(int node, int start, int end, int left, int right, long value) {
		// 1.lazy 처리
		if(lazy[node] != 0) {
			tree[node] += (end - start + 1) * lazy[node];
			if(start != end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}
			lazy[node] = 0;
		}
		// 2.완전 불포함
		if(end < left || right < start) return;
		// 3.완전 포함
		else if(left <= start && end <= right) {
			tree[node] += (end - start + 1) * value;
			// 리프 아님
			if(start != end) {
				lazy[node * 2] += value;
				lazy[node * 2 + 1] += value;
			}
		}
		// 4.일부 겹침 -> 좌우 분기
		else {
			int mid = (start + end) / 2;
			update(node * 2, start, mid, left, right, value);
			update(node * 2 + 1, mid + 1, end, left, right, value);
			
			tree[node] = tree[node * 2] + tree[node * 2 + 1];
		}
	}
}