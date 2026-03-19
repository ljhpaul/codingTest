package baekjoon.newbie.week07.boj2169;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int answer, N, M;
	static int[][] grid, dp;

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = 0;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		grid = new int[N][M];
		
		// input
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				grid[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// solve
		dp();

		// output
		System.out.println(answer);
		br.close();
	}

	// DP
	private static void dp() {
		// 배열 초기화
		dp = new int[N][M];
		int[] fromUp = new int[M];
		int[] fromLeft = new int[M];
		int[] fromRight = new int[M];
		
		// 첫 행 초기화(좌 → 우)
		dp[0][0] = grid[0][0];
		for(int c = 1; c < M; c++) {
			dp[0][c] = dp[0][c - 1] + grid[0][c]; 
		}
		
		// 두 번째 행부터 진행
		for(int r = 1; r < N; r++) {
			// 행 초기 세팅(위에서 내려오는 경우)
			for(int c = 0; c < M; c++) {
				fromUp[c] = dp[r - 1][c] + grid[r][c];
			}
			
			// 좌 → 우
			fromLeft[0] = fromUp[0];
			for(int c = 1; c < M; c++) {
				fromLeft[c] = Math.max(fromUp[c], fromLeft[c - 1] + grid[r][c]);
			}
			
			// 우 → 좌
			fromRight[M - 1] = fromUp[M - 1];
			for(int c = M - 2; c >= 0; c--) {
				fromRight[c] = Math.max(fromUp[c], fromRight[c + 1] + grid[r][c]);
			}
			
			// 행 최적값 완성
			for(int c = 0; c < M; c++) {
				dp[r][c] = Math.max(fromLeft[c], fromRight[c]);
			}
		}
		
		// 도착지 최댓값 반영
		answer = dp[N - 1][M - 1];
	}
}