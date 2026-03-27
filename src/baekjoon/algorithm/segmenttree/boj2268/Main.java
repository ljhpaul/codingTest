package baekjoon.algorithm.segmenttree.boj2268;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static long[] tree;
	static int N, M;

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tree = new long[4 * N];
		
		// solve
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int mode = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// function (0 : Sum, 1 : Modify)
			if(mode == 0) {
				long sum = query(1, 1, N, Math.min(a, b), Math.max(a, b));
				answer.append(sum).append("\n");
			} else if(mode == 1) {
				update(1, 1, N, a, b);
			}
		}

		// output
		System.out.println(answer);
		br.close();
	}
	
	// query(node, start, end, left, right)
	private static long query(int node, int start, int end, int left, int right) {
		// 1. 완전 불포함
		if(end < left || right < start) return 0;
		
		// 2. 완전 포함
		else if(left <= start && end <= right) return tree[node];
		
		// 3. 일부 겹침 -> 좌우 분기
		else {
			int mid = (start + end) / 2;
			return query(node * 2, start, mid, left, right) 
					+ query(node * 2 + 1, mid + 1, end, left, right);
		}
	}
	
	// update(node, start, end, idx, value)
	private static void update(int node, int start, int end, int idx, int value) {
		// 리프 노달
		if(start == end) tree[node] = value;
		
		// 리프가 아닐 경우
		else {
			int mid = (start + end) / 2;
			// idx가 mid보다 왼쪽인 경우
			if(idx <= mid) update(node * 2, start, mid, idx, value);
			// idx가 mid보다 오른쪽인 경우
			else update(node * 2 + 1, mid + 1, end, idx, value);
			// 값 갱신
			tree[node] = tree[node * 2] + tree[node * 2 + 1];
		}
	}
}