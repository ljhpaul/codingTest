package baekjoon.roadmap.ch01.boj2468;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Scanner sc = new Scanner(System.in);
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};

	// main
	public static void main(String[] args) throws IOException {
		// init
		int answer = 1;
		int n = sc.nextInt();
		int maxHeight = 0;
		int[][] map = new int[n][n];
		boolean[][] visited;

		// input
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				int tmp = sc.nextInt();
				map[i][j] = tmp;
				maxHeight = Math.max(maxHeight, tmp);
			}
		}

		// get answer
		for(int rain=1; rain<maxHeight; rain++) {
			visited = new boolean[n][n];
			int cnt = 0;

			// 비 내리기
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(map[i][j] <= rain) {
						visited[i][j] = true;
					}
				}
			}

			// 전체 영역 순회
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					// 비에 잠긴 땅이거나, 이미 방문한 땅이면 pass
					if(visited[i][j]) continue;

					// 큐 선언 및 방문 처리, 새 영역 카운트 반영
					ArrayDeque<int[]> q = new ArrayDeque<>();
					q.add(new int[]{i, j});
					visited[i][j] = true;
					cnt++;

					// BFS(안전영역 탐색)
					while(!q.isEmpty()) {
						int nowX = q.peek()[0];
						int nowY = q.poll()[1];

						// 4방향 탐색
						for(int d=0; d<4; d++) {
							int nx = nowX + dx[d];
							int ny = nowY + dy[d];

							// 유효 영역 검사 및 방문 여부 확인
							if(nx >= 0 && nx < n && ny >= 0 && ny < n) {
								if(!visited[nx][ny]) {
									q.add(new int[]{nx, ny});
									visited[nx][ny] = true;
								}
							}
						}
					}

					answer = Math.max(answer, cnt);
				}
			}
		}

		// output
		System.out.println(answer);
	}

}