package baekjoon.algorithm.segmenttree.boj12837;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer = new StringBuilder();
	static long[] tree;
	static int N, Q;

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		tree = new long[4 * N];
		
		// solve
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int mode = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// function (0 : update, 1 : query)
			if(mode == 1) {
				update(1, 1, N, a, b);
			} else if(mode == 2) {
				long change = query(1, 1, N, a, b);
				answer.append(change).append("\n");
			}
		}
		
		// output
		System.out.println(answer);
		br.close();
	}
	
	// query(node, start, end, left, right)
	private static long query(int node, int start, int end, int left, int right) {
		// 1.완전 불포함
		if(end < left || right < start) return 0;
		
		// 2.완전 포함
		else if(left <= start && end <= right) return tree[node];
		
		// 3.일부 겹침 -> 좌우 분기
		else {
			int mid = (start + end) / 2;
			return query(node * 2, start, mid, left, right)
					+ query(node * 2 + 1, mid + 1, end, left, right);
		}
	}
	
	// update(node, start, end, idx, value
	private static void update(int node, int start, int end, int idx, int value) {
		// 리프 도달
		if(start == end) tree[node] += value;
		
		// 리프 아님
		else {
			int mid = (start + end) / 2;
			// idx 왼쪽 영역
			if(idx <= mid) update(node * 2, start, mid, idx, value); 
			// idx 오른쪽 영역
			else update(node * 2 + 1, mid + 1, end, idx, value);
			// 현재 노드 갱신
			tree[node] = tree[node * 2] + tree[node * 2 + 1];
		}
	}
}