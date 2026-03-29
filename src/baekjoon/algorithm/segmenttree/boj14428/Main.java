package baekjoon.algorithm.segmenttree.boj14428;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static int[] arr;
	static Node[] tree;
	static final int INF = 1_000_000_001;
	
	// Node
	static class Node {
		int index, value;

		public Node(int index, int value) {super();
			this.index = index;
			this.value = value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		tree = new Node[4 * N];
		for(int i = 0; i < tree.length; i++) tree[i] = new Node(INF, INF);
		
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
			int cmd = Integer.parseInt(st.nextToken());
			// [cmd] 1:update, 2:query(min)
			if(cmd == 1) {
				int idx = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				update(1, 1, N, idx, value);				
			} else if(cmd == 2) {
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				answer.append(query(1, 1, N, left, right).index).append("\n");
			}
		}

		// output
		System.out.println(answer);
		br.close();
	}
	
	// build(node, start, end)
	private static Node build(int node, int start, int end) {
		// 리프
		if(start == end) {
			tree[node] = new Node(start, arr[start]);
		}
		// 리프 아님 -> 좌우 분기
		else {
			int mid = (start + end) / 2;
			Node leftNode = build(node * 2, start, mid);
			Node rightNode = build(node * 2 + 1, mid + 1, end);
			if(leftNode.value <= rightNode.value) tree[node] = leftNode;
			else tree[node] = rightNode;
		}
		// 값 반환
		return tree[node];
	}
	
	// query(node, start, end, left, end)
	private static Node query(int node, int start, int end, int left, int right) {
		// 완전 불포함
		if(end < left || right < start) return new Node(INF, INF);
		// 완전 포함
		else if(left <= start && end <= right) return tree[node];
		// 일부 겹침 -> 좌우 분기
		else {
			int mid = (start + end) / 2;
			Node leftNode = query(node * 2, start, mid, left, right);
			Node rightNode = query(node * 2 + 1, mid + 1, end, left, right);
			if(leftNode.value <= rightNode.value) return leftNode;
			else return rightNode;
		}
	}
	
	// update(node, start, end, idx, value)
	private static void update(int node, int start, int end, int idx, int value) {
		// 리프 -> 값 수정
		if(start == end) {
			tree[node].value = value;
		}
		// 리프 아님 -> 좌우 분기
		else {
			int mid = (start + end) / 2;
			// idx 왼쪽
			if(idx <= mid) update(node * 2, start, mid, idx, value);
			// idx 오른쪽
			else update(node * 2 + 1, mid + 1, end, idx, value);
			// 값 갱신
			Node leftNode = tree[node * 2];
			Node rightNode = tree[node * 2 + 1];
			if(leftNode.value <= rightNode.value) tree[node] = leftNode;
			else tree[node] = rightNode;
		}
	}
}