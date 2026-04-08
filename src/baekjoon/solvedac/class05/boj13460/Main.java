package baekjoon.solvedac.class05.boj13460;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int answer, N, M;
	static boolean[][] moveable;
	static Pos red, blue, hole;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	// Pos
	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = 11;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		moveable = new boolean[N][M];
		
		// input
		for(int r = 0; r < N; r++) {
			String line = br.readLine();
			for(int c = 0; c < M; c++) {
				char tmp = line.charAt(c);
				switch(tmp) {
				case '#': break;
				case '.':
					moveable[r][c] = true;
					break;
				case 'R':
					red = new Pos(r, c);
					moveable[r][c] = true;
					break;
				case 'B':
					blue = new Pos(r, c);
					moveable[r][c] = true;
					break;
				case 'O':
					hole = new Pos(r, c);
					moveable[r][c] = true;
					break;
				}
			}
		}

		// solve
		tipBoard(1);
		if(answer == 11) answer = -1;

		// output
		System.out.println(answer);
		br.close();
	}

	// solve
	private static void tipBoard(int cnt) {
		// pruning
		if(cnt >= answer) return;
		
		// base
		if(cnt > 10) return;
		
		// fix initial pos
		Pos posR = new Pos(red.r, red.c);
		Pos posB = new Pos(blue.r, blue.c);
		
		// dir loop
		for(int dir = 0; dir < 4; dir++) {
			boolean redToHole = false;
			boolean blueToHole = false;
			
			// move red
			while(true) {
				int nr = red.r + dr[dir];
				int nc = red.c + dc[dir];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) break;
				if(!moveable[nr][nc]) break;
				
				red.r = nr;
				red.c = nc;
				if(red.r == hole.r && red.c == hole.c) {
					redToHole = true;
					break;
				}
			}
			
			// move blue
			while(true) {
				int nr = blue.r + dr[dir];
				int nc = blue.c + dc[dir];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) break;
				if(!moveable[nr][nc]) break;
				
				blue.r = nr;
				blue.c = nc;
				if(blue.r == hole.r && blue.c == hole.c) {
					// backtracking
					blueToHole = true;
					break;
				};
			}
			
			// check blue into hole
			if(blueToHole) {
				red.r = posR.r;
				red.c = posR.c;
				blue.r = posB.r;
				blue.c = posB.c;
				continue;
			}
			
			// check same pos
			if(red.r == blue.r && red.c == blue.c) {
				if(dir == 0) {
					if(posR.r > posB.r) red.r++;
					else blue.r++;
				} else if(dir == 1) {
					if(posR.r < posB.r) red.r--;
					else blue.r--;
				} else if(dir == 2) {
					if(posR.c > posB.c) red.c++;
					else blue.c++;
				} else if(dir == 3) {
					if(posR.c < posB.c) red.c--;
					else blue.c--;
				}
			}
			
			// check red into hole
			if(redToHole) {
				answer = Math.min(answer, cnt);
				// backtracking
				red.r = posR.r;
				red.c = posR.c;
				blue.r = posB.r;
				blue.c = posB.c;
				continue;
			}
			
			// next tip
			tipBoard(cnt + 1);
			
			// backtracking
			red.r = posR.r;
			red.c = posR.c;
			blue.r = posB.r;
			blue.c = posB.c;
		}
	}
}
