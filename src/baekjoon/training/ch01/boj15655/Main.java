package baekjoon.training.ch01.boj15655;

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
		dfs(0, 0);

		// output
		System.out.println(answer);
		br.close();
	}
	
	// dfs : combination
	private static void dfs(int start, int cnt) {
		// base
		if(cnt == M) {
			for(int num : nums) {
				answer.append(num).append(" ");
			}
			answer.append("\n");
			return;
		}
		
		// recursion
		for(int i = start; i < N; i++) {
			if(!isSelected[i]) {
				nums[cnt] = arr[i];
				isSelected[i] = true;
				dfs(i, cnt + 1);
				isSelected[i] = false;
			}
		}
	}

}