package baekjoon.roadmap.etc.boj2667;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	// main
	public static void main(String[] args) throws IOException {
		// init
		ArrayList<Integer> cnts = new ArrayList<>();
		int[] dx = new int[] {0, 1, 0, -1}; // 동 남 서 북
		int[] dy = new int[] {1, 0, -1, 0};

		// input
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][n];
		int[][] visited = new int[n][n];

		for(int i=0; i<n; i++) {
			char[] line = br.readLine().toCharArray();
			for(int j=0; j<n; j++) {
				map[i][j] = line[j] - '0';
			}
		}

		// BFS
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(visited[i][j] > 0 || map[i][j] == 0) continue;

				ArrayDeque<int[]> dq = new ArrayDeque<>();
				int cnt = 0;

				dq.add(new int[]{i, j});
				visited[i][j]++;
				cnt++;

				while(!dq.isEmpty()) {
					int[] now = dq.poll();

					for(int d=0; d<4; d++) {
						int nx = now[0] + dx[d];
						int ny = now[1] + dy[d];
						if(nx >= 0 && nx < n && ny >= 0 && ny < n &&
								visited[nx][ny] == 0 && map[nx][ny] == 1) {
							dq.add(new int[]{nx, ny});
							visited[nx][ny]++;
							cnt++;
						}
					}
				}

				cnts.add(cnt);
			}
		}

		// sort
		Collections.sort(cnts);

		// output
		System.out.println(cnts.size());
		for(int cnt : cnts) {
			System.out.println(cnt);
		}
	}

}