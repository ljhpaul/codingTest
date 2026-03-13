package baekjoon.ssafy.boj14890;

import java.io.*;
import java.util.*;

class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int answer, N, X;
	static int[][] map;

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = 0;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		// input
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// solve
		solve();

		// answer
		System.out.println(answer);
	}

	// solve
	private static void solve() {
		// 수평 방향 확인
		for (int r = 0; r < N; r++) {
			int[] line = new int[N];
			for (int i = 0; i < N; i++)
				line[i] = map[r][i];
			answer += check(line) ? 1 : 0;
		}
		// 수직 방향 확인
		for (int c = 0; c < N; c++) {
			int[] line = new int[N];
			for (int i = 0; i < N; i++)
				line[i] = map[i][c];
			answer += check(line) ? 1 : 0;
		}
	}

	// check
	private static boolean check(int[] line) {
		boolean[] used = new boolean[N];
		for (int i = 1; i < N; i++) {
			int curr = line[i];
			int prev = line[i - 1];

			// 평지 스킵
			if (curr == prev)
				continue;

			// 1. 높이차 2 이상이면 실패
			if (Math.abs(curr - prev) >= 2)
				return false;

			// 2. 오르막 : 앞으로 X칸 미사용 평지
			if (curr > prev) {
				if (i < X)
					return false;
				int idx = i - 1;
				int cnt = 0;
				while (cnt < X) {
					if (used[idx])
						return false;
					if (line[idx] != prev)
						return false;
					used[idx] = true;
					idx--;
					cnt++;
				}
			}

			// 3. 내리막 : 뒤로 X칸 미사용 평지
			if (curr < prev) {
				if (N - i < X)
					return false;
				int idx = i;
				int cnt = 0;
				while (cnt < X) {
					if (used[idx])
						return false;
					if (line[idx] != curr)
						return false;
					used[idx] = true;
					idx++;
					cnt++;
				}
			}
		}
		return true;
	}
}