package baekjoon.training.ch01.boj15654;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static int N, M;
	static int[] arr;
	static List<Integer> list;
	static boolean[] isSelected;

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		list = new ArrayList<>();
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
	private static void dfs(int depth) {
		// base
		if(depth == M) {
			for(int num : list) {
				answer.append(num).append(" ");
			}
			answer.append("\n");
			return;
		}
		
		// recursion
		for(int i = 0; i < N; i++) {
			if(!isSelected[i]) {
				list.add(arr[i]);
				isSelected[i] = true;
				dfs(depth + 1);
				list.remove(list.size() - 1);
				isSelected[i] = false;
			}
		}
	}
}