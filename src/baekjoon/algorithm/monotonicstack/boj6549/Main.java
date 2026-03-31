package baekjoon.algorithm.monotonicstack.boj6549;

import java.io.*;
import java.util.*;

public class Main {
	// main
	public static void main(String[] args) throws IOException {
		// init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder answer = new StringBuilder();
		
		// test case
		while(true) {
			// init
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			if(N == 0) break;
			int[] height = new int[N + 1];
			
			// input
			for(int i = 0; i < N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			
			// solve
			long max = monotonicStack(N, height);
			answer.append(max).append("\n");
		}

		// output
		System.out.println(answer);
		br.close();
	}
	
	// monotonic stack
	private static long monotonicStack(int N, int[] height) {
		// init
		long max = 0;
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		
		// loop
		for(int i = 0; i <= N; i++) {
			// curr
			int curr = height[i];
			
			// pop - 단조 증가 스택(새 값이 더 작으면 pop)
			while(!stack.isEmpty() && height[stack.peek()] > curr) {
				int top = stack.pop();
				int leftBound = stack.isEmpty() ? -1 : stack.peek();
				max = Math.max(max, (long) height[top] * (i - leftBound - 1));
			}
			
			// push
			stack.push(i);
		}
		
		return max;
	}

}