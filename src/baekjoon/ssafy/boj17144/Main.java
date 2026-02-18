package baekjoon.ssafy.boj17144;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int R, C, T;
	static int[] airCleaner;
	
	static int[] dr = {0, -1, 1, 0, 0}; // 상하좌우(1234)
	static int[] dc = {0, 0, 0, -1, 1};

	// main
	public static void main(String[] args) throws IOException {
		// init
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		int[][] dusts = new int[R][C];
		int[][] wind = new int[R][C];
		airCleaner = new int[2];
		int idx = 0;
		
		// input
		for(int r=0; r<R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C; c++) {
				dusts[r][c] = Integer.parseInt(st.nextToken());
				if(dusts[r][c] == -1) {
					airCleaner[idx++] = r;
				}
			}
		}
		
		// solve
		setWind(wind);
		int answer = simulate(dusts, wind);
		
		// output
		System.out.println(answer);
		br.close();
	}
	
	// set wind flow
	private static void setWind(int[][] wind) {
		int r1 = airCleaner[0];
		int r2 = airCleaner[1];
		for(int c=1; c<C; c++) {
			wind[0][c] = 3;
			wind[r1][c] = 4;
			wind[r2][c] = 4;
			wind[R-1][c] = 3;
		}
		for(int r=0; r<r1; r++) wind[r][0] = 2;
		for(int r=1; r<r2; r++) wind[r][C-1] = 1;
		for(int r=r2; r<R-1; r++) wind[r][C-1] = 2;
		for(int r=r2+1; r<R; r++) wind[r][0] = 1;
	}

	// simulate
	private static int simulate(int[][] dusts, int[][] wind) {
		for(int sec=0; sec<T; sec++) {
			// 먼지 확산
			int[][] next = new int[R][C];
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					int cnt = dusts[r][c];
					if(cnt == -1) {
						next[r][c] = -1;
						continue;	// 공기청정기 패스
					}
					// 4방향 확산
					int spread = cnt / 5;
					for(int d=1; d<5; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue; // 유효하지 않은 좌표
						if(dusts[nr][nc] == -1) continue;	// 공기청정기 패스
						next[nr][nc] += spread;
						cnt -= spread;
					}
					// 제자리 설정
					next[r][c] += cnt;
				}
			}
			dusts = next;
			
			// 바람 이동
			next = new int[R][C];
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					int cnt = dusts[r][c];
					int d = wind[r][c];
					if(d > 0) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue; // 유효하지 않은 좌표
						if(dusts[nr][nc] == -1) continue;	// 공기청정기 패스
						next[nr][nc] = cnt;
					} else {
						next[r][c] = cnt;
					}
				}
			}
			dusts = next;
		}
		// 남은 먼지 합계
		int sum = 0;
		for(int r=0; r<R; r++) {
			for(int c=0; c<C; c++) {
				if(dusts[r][c] > 0) {
					sum += dusts[r][c];
				}
			}
		}
		return sum;
	}

}