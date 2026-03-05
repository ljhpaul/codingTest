package baekjoon.newbie.week05.boj2447;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static int N;
	static boolean[][] isStar;

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		isStar = new boolean[N][N];
		
		// solve
		star(0, 0, N, false);
		for(boolean[] line : isStar) {
			for(boolean b : line) {
				answer.append(b ? "*" : " ");
			}
			answer.append("\n");
		}
		
		// output
		System.out.println(answer);
		br.close();
	}
	
	// star
	private static void star(int r, int c, int sz, boolean isBlank) {
		// base
		if(sz == 1) {
			isStar[r][c] = !isBlank;
			return;
		}
		
		// recursion
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				boolean blank = isBlank;
				if(i == 1 && j == 1) blank = true;
				star(r + i*(sz/3), c + j*(sz/3), sz/3, blank);
			}
		}
	}
}