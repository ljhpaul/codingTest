package baekjoon.algorithm.monotonicstack.boj1725;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N;
	static int[] height;
	static long[] area;

	// main
	public static void main(String[] args) throws IOException {
		// init
		N = Integer.parseInt(br.readLine());
		height = new int[N + 1];
		area = new long[N + 1];
		
		// input
		for(int i = 0; i < N; i++) {
			height[i] = Integer.parseInt(br.readLine());
		}
		
		// solve
		monotonicStack();
		System.out.println(Arrays.toString(area));
		long answer = getMax();
		
		// output
		System.out.println(answer);
		br.close();
	}
	

	// monotonic stack
	private static void monotonicStack() {
		// init
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		
		// loop
		for(int i = 0; i <= N; i++) {
			// curr
			int current = height[i];
			
			// pop
			while(!stack.isEmpty() && height[stack.peek()] > current) {
				int top = stack.pop();
				int leftBound = stack.isEmpty() ? -1 : stack.peek();
				area[top] = height[top] * (i - leftBound - 1);
			}
			
			// push
			stack.push(i);
		}
		
		// flush
		int idx = N;
		while(!stack.isEmpty()) {
			int top = stack.pop();
			area[top] = height[top] * (N - top + 1);
		}
	}
	
	// get max(answer)
	private static long getMax() {
		long max = 0;
		for(long a : area) {
			max = Math.max(a, max);
		}
		return max;
	}
}