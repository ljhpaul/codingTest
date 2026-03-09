package baekjoon.solvedac.class04.boj1629;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	// main
	public static void main(String[] args) throws IOException {
		// init
		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		// solve
		int answer = (int) pow(A, B, C);

		// output
		System.out.println(answer);
		br.close();
	}

	// solve
	private static long pow(int A, int B, int C) {
		// base
		if(B == 1) {
			return A % C;
		}
		
		// recursion
		long tmp = pow(A, B/2, C);
		if(B % 2 == 0) {
			return (tmp * tmp) % C;
		} else {
			return (((tmp * tmp) % C) * (A % C)) % C;
		}
	}
}