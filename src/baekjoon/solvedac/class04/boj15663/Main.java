package baekjoon.solvedac.class04.boj15663;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static StringBuilder answer, tmp;
	static int N, M;
	static int[] nums, result;
	static boolean[] isSelected;
	static Set<String> set;
	
	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N];
		result = new int[M];
		isSelected = new boolean[N];
		set = new TreeSet<>();
		
		// input
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		// solve
		Arrays.sort(nums);
		dfs(0);
		

		// output
		System.out.println(answer);
		br.close();
	}
	
	// DFS : Permutation
	private static void dfs(int cnt) {
		// base
		if(cnt == M) {
			tmp = new StringBuilder();
			for(int i=0; i<M; i++) {
				tmp.append(result[i]).append(" ");
			}
			tmp.append("\n");
			if(set.contains(tmp.toString())) return;
			set.add(tmp.toString());
			answer.append(tmp);
			return;
		}
		
		// recursion
		for(int i=0; i<N; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			result[cnt] = nums[i];
			dfs(cnt + 1);
			isSelected[i] = false;
		}
	}
}