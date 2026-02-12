package baekjoon.roadmap.etc.boj2805;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static long answer;
	static int N, M;
	static int[] H;

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = 0;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = new int[N];
		int maxHeight = 0;

		// input
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			H[i] = Integer.parseInt(st.nextToken());
			maxHeight = Math.max(H[i], maxHeight);
		}

		// solve
		binarySearch(0, maxHeight);

		// output
		System.out.println(answer);
		br.close();
	}

	// lower bound
	private static void binarySearch(int s, int e) {
		if(s > e) {
			return;
		}

		int m = s + (e - s) / 2;

		long sum = 0;
		for(int i=0; i<N; i++) {
			if(H[i] > m) {
				sum += H[i] - m;
			}
		}

		if(sum >= M) {
			answer = m;
			binarySearch(m + 1, e);
		} else {
			binarySearch(s, m - 1);
		}
	}

}