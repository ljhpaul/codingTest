package baekjoon.training.ch01.boj15650;

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
		combination(1, 0);

		// output
		System.out.println(answer);
		br.close();
	}
	
	// combination(start, depth)
	private static void combination(int start, int depth) {
		// base
		if(depth == M) {
			for(int num : list) {
				answer.append(num).append(" ");
			}
			answer.append("\n");
			return;
		}
		
		// recursion
		for(int i = start; i <= N; i++) {
			if(!isSelected[i]) {
				list.add(nums[i]);
				isSelected[i] = true;
				combination(i + 1, depth + 1);
				list.remove(list.size() - 1);
				isSelected[i] = false;
			}
		}
	}
}