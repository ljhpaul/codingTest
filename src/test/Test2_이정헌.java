package test;

import java.io.*;
import java.util.*;

public class Test2_이정헌 {
	// static field
	static int answer, N, K;
	static int[][] map;
	static boolean[][] Rvisited, Cvisited;
	
	static int[] dr = {0, 1};	// 우하(01)
	static int[] dc = {1, 0};

	// main
	public static void main(String[] args) throws Exception {
		// init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		answer = 0;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		Rvisited = new boolean[N][N];
		Cvisited = new boolean[N][N];
		
		// input
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// solve
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(!Rvisited[r][c]) {
					dfs(r, c, 1, 1);
				}
				if(!Cvisited[r][c]) {
					dfs(r, c, 1, 0);
				}
			}
		}
		
		// output
		System.out.println(answer);
	}

	// DFS
	private static void dfs(int r, int c, int cnt, int dir) {
		if(dir == 0) {
			Cvisited[r][c] = true;
		} else {
			Rvisited[r][c] = true;
		}
		
		// base
		if(cnt == K) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 0) {
				answer++;
			}
			return;
		}
		
		// recursion
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		if(nr < 0 || nr >= N || nc < 0 || nc >= N) return;
		if(map[nr][nc] == 0) return;
		// 카운트 진행
		dfs(nr, nc, cnt + 1, dir);
	}
}
