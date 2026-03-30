package baekjoon.training.ch01.boj15651;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static int N, M;
	static List<Integer> list;

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		
		// solve
		dfs(0);

		// output
		System.out.println(answer);
		br.close();
	}
	
	// dfs(depth)
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
		for(int i = 1; i <= N; i++) {
			list.add(i);
			dfs(depth + 1);
			list.remove(list.size() - 1);
		}
	}
}