package baekjoon.ssafy.boj20040;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int[] parent, rank, size;

	// main
	public static void main(String[] args) throws IOException {
		// init
		int answer = 0;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		parent = new int[n];
		rank = new int[n];
		size = new int[n];
		
		for(int i=0; i<n; i++) {
			parent[i] = i;
		}

		// solve
		for(int i=1; i<=m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(find(a) == find(b)) {
				answer = i;
				break;
			}
			union(a, b);
		}

		// output
		System.out.println(answer);
		br.close();
	}

	// find
	private static int find(int x) {
		// 현재 요소 x가 대표노드가 아니면 대표노드 갱신
		// 대표노드 -> 인덱스와 요소가 동일 / 단, 트리 배열 초기화 시 노드와 부모가 같게 초기화해야 함!
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		// 대표 노드 반환
		return parent[x];
	}

	// union
	private static void union(int a, int b) {
		// 각 요소의 대표 노드 반환
		int A = find(a);
		int B = find(b);

		if (A == B) return;

		// A가 더 작다면 B를 A에 묶기
		if (A <= B) {
			parent[B] = A;
		}
		// B가 더 작다면 A를 B에 묶기
		else {
			parent[A] = B;
		}
	}
}