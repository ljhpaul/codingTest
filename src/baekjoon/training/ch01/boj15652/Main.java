package baekjoon.training.ch01.boj15652;

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
		dfs(1, 0);

		// output
		System.out.println(answer);
		br.close();
	}
	
	// dfs(start, depth)
	private static void dfs(int start, int depth) {
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
			list.add(i);
			dfs(i, depth + 1);
			list.remove(list.size() - 1);
		}
	}

}