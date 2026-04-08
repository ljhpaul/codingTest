package baekjoon.algorithm.lis.boj14002;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static int N;
	static int[] arr, dp, prev;

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N];
		prev = new int[N];
		
		// input
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
			prev[i] = -1;
		}

		// solve
		solve();

		// output
		System.out.println(answer);
		br.close();
	}

	// solve
	private static void solve() {
		// dp 배열 순회 -> 이전 요소까지 최대 길이 찾고 갱신
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < i; j++) {
				if(arr[j] < arr[i] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
					prev[i] = j;
				}
			}
		}
		
		// LIS 길이 : dp 중 최댓값
		int len = 0;
		for(int tmp : dp) len = Math.max(tmp, len);
		answer.append(len).append("\n");
		
		// LIS 끝 인덱스 찾기
		int lastIdx = 0;
		for(int i = 1; i < N; i++) {
			if(dp[i] > dp[lastIdx]) {
				lastIdx = i;
			}
		}
		
		// 역추적
		Stack<Integer> stack = new Stack<>();
		while(lastIdx > -1) {
			stack.push(arr[lastIdx]);
			lastIdx = prev[lastIdx];
		}
		
		// 경로 출력
		while(!stack.isEmpty()) {
			answer.append(stack.pop()).append(" ");
		}
	}
}