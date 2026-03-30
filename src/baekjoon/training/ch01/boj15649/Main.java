package baekjoon.training.ch01.boj15649;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static int N, M;
	static int[] nums;
	static List<Integer> list;
	static boolean[] isSelected;

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N + 1];
		for(int i = 1; i <= N; i++) nums[i] = i;
		list = new ArrayList<>();
		isSelected = new boolean[N + 1];

		// solve
		permutation(0);

		// output
		System.out.println(answer);
		br.close();
	}
	
	// permutation(depth)
	private static void permutation(int depth) {
		// base
		if(depth == M) {
			for(int num : list) {
				answer.append(num).append(" ");
			}
			answer.append("\n");
			return;
		}
		
		// recursion
		for(int i = 1; i <= N; i++) {
			if(!isSelected[i]) {
				list.add(nums[i]);
				isSelected[i] = true;
				permutation(depth + 1);
				list.remove(list.size() - 1);
				isSelected[i] = false;
			}
		}
	}
}