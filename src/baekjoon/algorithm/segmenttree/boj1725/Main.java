package baekjoon.algorithm.segmenttree.boj1725;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static long answer = 0;
	static int[] arr;
	static Node[] tree;
	
	static final int INF = 1_000_000_001;
	
	// Node
	static class Node {
		int value, size;
		long area;
		
		public Node() {
			this.value = INF;
			this.area = 0;
			this.size = 0;
		}
		
		public Node(int value, long area) {
			this.value = value;
			this.area = area;
			this.size = 1;
		}
		
		@Override
		public String toString() {
			return String.valueOf(area);
		}
	}

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = 0;
		int N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		tree = new Node[4 * N];
		for(int i = 0; i < tree.length; i++) {
			tree[i] = new Node();
		}
		
		// input
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		// solve
		build(1, 1, N);
		System.out.println(Arrays.toString(tree));
		dfs(1);

		// output
		System.out.println(answer);
		br.close();
	}
	
	// build(node, start, end)
	private static Node build(int node, int start, int end) {
		// 리프 -> 값 설정
		if(start == end) tree[node] = new Node(arr[start], arr[start]);
		// 리프 아님 -> 좌우 분기
		else {
			int mid = (start + end) / 2;
			Node leftNode = build(node * 2, start, mid);
			Node rightNode = build(node * 2 + 1, mid + 1, end);
			if(leftNode.value <= rightNode.value) tree[node].value = leftNode.value;
			else tree[node].value = rightNode.value;
			tree[node].size = leftNode.size + rightNode.size;
			tree[node].area = tree[node].value * tree[node].size;
		}
		// 값 반환
		return tree[node];
	}
	
	// dfs(node)
	private static void dfs(int node) {
		if(node >= tree.length || tree[node].size == 0) return;
		answer = Math.max(answer, tree[node].area);
		dfs(node * 2);
		dfs(node * 2 + 1);
	}

}