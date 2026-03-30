package baekjoon.algorithm.monotonicstack.boj2493;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static int N;
	static int[] arr, idx;

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		idx = new int[N];
		
		// input
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// solve
		monotonicStack();
		setAnswer();
		
		// output
		System.out.println(answer);
		br.close();
	}

	// monotonic stack - decrease
	private static void monotonicStack() {
		// init
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		
		// loop (reverse)
		for(int i = N - 1; i >= 0; i--) {
			int current = arr[i];
			while(!stack.isEmpty() && arr[stack.peek()] < current) {
				int top = stack.pop();
				idx[top] = i + 1;
			}
			
			stack.push(i);
		}
		
		// flush
		while(!stack.isEmpty()) {
			int top = stack.pop();
			idx[top] = 0;
		}
	}
	
	// set answer
	private static void setAnswer() {
		for(int i : idx) {
			answer.append(i).append(" ");
		}
		answer.append("\n");
	}

}