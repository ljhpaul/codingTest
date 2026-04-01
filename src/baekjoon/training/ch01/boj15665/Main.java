package baekjoon.training.ch01.boj15665;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static int N, M;
	static int[] arr, result;

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		result = new int[M];
		
		// input
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// solve
		Arrays.sort(arr);
		dfs(0, 0);

		// output
		System.out.println(answer);
		br.close();
	}

	// dfs - 중복순열
	private static void dfs(int start, int cnt) {
		// base
		if(cnt == M) {
			for(int num : result) {
				answer.append(num).append(" ");
			}
			answer.append("\n");
			return;
		}
		
		// recursion
		int prev = 0;
		for(int i = 0; i < N; i++) {
			if(arr[i] == prev) continue;
			
			prev = arr[i];
			result[cnt] = arr[i];
			dfs(i, cnt + 1);
		}
	}
}