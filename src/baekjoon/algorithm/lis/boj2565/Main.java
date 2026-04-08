package baekjoon.algorithm.lis.boj2565;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int answer, N;
	static Node[] pairs;
	static int[] arr, dp;
	
	// Node
	static class Node {
		int a, b;

		public Node(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = 0;
		N = Integer.parseInt(br.readLine());
		pairs = new Node[N];
		arr = new int[N];
		dp = new int[N];
		
		// input
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			pairs[i] = new Node(a, b);
		}

		// solve
		Arrays.sort(pairs, (o1, o2) -> Integer.compare(o1.a, o2.a));
		for(int i = 0; i < N; i++) {
			arr[i] = pairs[i].b;
			dp[i] = 1;
		}
		solve();

		// output
		System.out.println(answer);
		br.close();
	}

	// solve
	private static void solve() {
		// dp 배열 순회 -> 이전 요소까지의 길이보다 길면 갱신
		for(int i = 0; i < N; i++) {
			for(int j = 0 ; j < i; j++) {
				if(arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);					
				}
			}
		}
		
		// LIS 길이 : dp 배열 최댓값
		int len = 0;
		for(int tmp : dp) len = Math.max(tmp, len);
		
		// 끊어낼 전깃줄 수
		answer = N - len;
	}
}