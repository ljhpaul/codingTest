package baekjoon.solvedac.class04.boj15654;

import java.io.*;
import java.util.*;

public class Main2 {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static int N, M;
	static int[] nums;
	static boolean[] isSelected;
	static List<Integer> selectedNums;

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N];
		isSelected = new boolean[N];
		selectedNums = new ArrayList<>();
		
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
			for(int num : selectedNums) {
				answer.append(num).append(" ");
			}
			answer.append("\n");
		}
		
		// recursion
		for(int i=0; i<N; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			selectedNums.add(nums[i]);
			dfs(cnt + 1);
			isSelected[i] = false;
			selectedNums.remove(selectedNums.size() - 1);
		}
	}
}