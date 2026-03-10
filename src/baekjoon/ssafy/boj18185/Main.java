package baekjoon.ssafy.boj18185;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	// main
	public static void main(String[] args) throws IOException {
		// init
		long answer = 0;
		int N = Integer.parseInt(br.readLine());
		int[] cnts = new int[N + 2];
		
		// input
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			cnts[i] = Integer.parseInt(st.nextToken());
		}

		// solve
		answer = solve(N, cnts);

		// output
		System.out.println(answer);
		br.close();
	}

	// solve
	private static long solve(int N, int[] cnts) {
		long result = 0;
		
		for(int i=0; i<N; i++) {
			if(cnts[i] <= cnts[i+1]) {
				if(cnts[i+1] > cnts[i+2]) {
					int min = Math.min(cnts[i], cnts[i+2]);
					result += 5 * min;
					cnts[i] -= min;
					cnts[i+1] -= min;
				}
			} else {
				
			}
		}
		
		return result;
	}
}