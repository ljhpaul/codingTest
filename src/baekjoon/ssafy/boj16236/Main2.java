package baekjoon.ssafy.boj16236;

import java.io.*;
import java.util.*;

public class Main2 {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int answer, sharkSize, remainGrowth, sharkR, sharkC, N;
	static int[][] map;
	static int[][] dist;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	// Fish class
	static class Fish implements Comparable<Fish> {
		int r, c, size;

		public Fish(int r, int c, int size) {
			this.r = r;
			this.c = c;
			this.size = size;
		}

		@Override
		public int compareTo(Fish o) {
			return this.size - o.size;
		}
	}

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = 0;
		sharkSize = 2;
		remainGrowth = 2;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		// input
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int size = Integer.parseInt(st.nextToken());
				if (size == 9) {
					sharkR = r;
					sharkC = c;
				} else if (size > 0) {
					map[r][c] = size;
				}
			}
		}

		// solve
		System.out.println(Arrays.deepToString(map));
		while (eatable()) {
			dist = new int[N][N];
			for (int[] line : dist)
				Arrays.fill(line, Integer.MAX_VALUE);
//			bfs();
			dist[sharkR][sharkC] = 0;
			dfs(sharkR, sharkC);
			System.out.println(Arrays.deepToString(map));
		}

		// output
		System.out.println(answer);
		br.close();
	}

	// eatable
	private static boolean eatable() {
		int cnt = 0;
		int minSize = Integer.MAX_VALUE;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int size = map[r][c];
				if (size > 0) {
					cnt++;
					minSize = Math.min(minSize, size);
				}
			}
		}
		if (cnt == 0)
			return false;
		if (minSize >= sharkSize)
			return false;
		return true;
	}

	// BFS
	private static void bfs() {
		// init
		int[][] dist = new int[N][N];
		for (int[] line : dist)
			Arrays.fill(line, Integer.MAX_VALUE);
		Queue<int[]> q = new ArrayDeque<>();

		q.offer(new int[] { sharkR, sharkC });
		dist[sharkR][sharkC] = 0;

		// loop
		outer: while (!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];

			// delta 4
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;
				if (dist[nr][nc] != Integer.MAX_VALUE)
					continue; // 방문 여부 확인

				int fishSize = map[nr][nc];
				// 물고기인 경우
				if (fishSize > 0) {
					// 상어 vs 물고기 사이즈 비교
					if (sharkSize > fishSize) {
						answer += dist[r][c] + 1;
						if (--remainGrowth == 0) {
							sharkSize++;
							remainGrowth = sharkSize;
						}
						map[nr][nc] = 0; // 상어보다 작으면 먹기
						sharkR = nr;
						sharkC = nc;
						break outer;
					} else if (sharkSize < fishSize) {
						continue; // 상어보다 크면 이동 불가
					}
				}

				q.offer(new int[] { nr, nc });
				dist[nr][nc] = dist[r][c] + 1;
			}
		}

		// renew time
		if (dist[sharkR][sharkC] != Integer.MAX_VALUE) {
			answer += dist[sharkR][sharkC];
		}
	}

	// DFS
	private static boolean dfs(int r, int c) {
		int fishSize = map[r][c];
		// 물고기인 경우
		if (fishSize > 0) {
			// 상어 vs 물고기 사이즈 비교
			if (sharkSize > fishSize) {
				answer += dist[r][c] + 1;
				if (--remainGrowth == 0) {
					sharkSize++;
					remainGrowth = sharkSize;
				}
				map[r][c] = 0; // 상어보다 작으면 먹기
				sharkR = r;
				sharkC = c;
				return true;
			} else if (sharkSize < fishSize) {
				return false;
			}
		}

		// delta 4
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			if (dist[nr][nc] != Integer.MAX_VALUE) continue; // 방문 여부 확인

			int tmp = dist[nr][nc];
			dist[nr][nc] = dist[r][c] + 1;
			if(dfs(nr, nc)) return true;
			dist[nr][nc] = tmp;
		}
		
		return false;
	}
}
