package baekjoon.training.ch01.boj15663;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static int N, M;
	static int[] arr, nums;
	static boolean[] isSelected;

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		nums = new int[M];
		isSelected = new boolean[N];
		
		// input
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// solve
		Arrays.sort(arr);
		dfs(0);

		// output
		System.out.println(answer);
		br.close();
	}
	
	// dfs : permutation
	private static void dfs(int cnt) {
		// base
		if(cnt == M) {
			for(int num : nums) {
				answer.append(num).append(" ");
			}
			answer.append("\n");
			return;
		}
		
		// recursion
		int prev = -1;
		for(int i = 0; i < N; i++) {
			if(!isSelected[i] && (prev == -1 || arr[prev] != arr[i])) {
				isSelected[i] = true;
				nums[cnt] = arr[i];
				prev = i;
				dfs(cnt + 1);
				isSelected[i] = false;
			}
		}
	}
}