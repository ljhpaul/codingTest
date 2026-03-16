package baekjoon.ssafy.boj12094;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int answer, N;
	static int cnt = 1;
	
	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = 0;
		N = Integer.parseInt(br.readLine());
		int[][] board = new int[N][N];
		
		// input
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
				answer = Math.max(answer, board[r][c]);
			}
		}

		// solve
		dfs(0, board, answer);

		// output
		System.out.println(answer);
		br.close();
	}

	// dfs
	private static void dfs(int depth, int[][] board, int max) {
		answer = Math.max(answer, max);
		
		// base
		if(depth == 10) return;
		
		// pruning
		if(max << (10 - depth) <= answer) return;
		
		// recursion
		for(int dir=0; dir<4; dir++) {
			int[][] next = new int[N][N];
			int nextMax = move(board, next, dir);
			if(isSame(board, next)) continue;
			dfs(depth + 1, next, nextMax);
		}
	}
	
	// isSame
	private static boolean isSame(int[][] board, int[][] next) {
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				if(board[r][c] != next[r][c]) {
					return false;
				}
			}
		}
		return true;
	}

	// move
	private static int move(int[][] board, int[][] next, int dir) {
		// init
		int max = 0;
		
		// 좌
		if(dir == 0) {
			for(int r=0; r<N; r++) {
				int idx = 0;
				for(int c=0; c<N; c++) {
					if(board[r][c] == 0) continue;
					if(next[r][idx] == 0) next[r][idx] = board[r][c];
					else if(next[r][idx] == board[r][c]) next[r][idx++] <<= 1;
					else next[r][++idx] = board[r][c];
				}
			}
		}
		
		// 우
		if(dir == 1) {
			for(int r=0; r<N; r++) {
				int idx = N-1;
				for(int c=N-1; c>=0; c--) {
					if(board[r][c] == 0) continue;
					if(next[r][idx] == 0) next[r][idx] = board[r][c];
					else if(next[r][idx] == board[r][c]) next[r][idx--] <<= 1;
					else next[r][--idx] = board[r][c];
				}
			}
		}
		
		// 상
		if(dir == 2) {
			for(int c=0; c<N; c++) {
				int idx = 0;
				for(int r=0; r<N; r++) {
					if(board[r][c] == 0) continue;
					if(next[idx][c] == 0) next[idx][c] = board[r][c];
					else if(next[idx][c] == board[r][c]) next[idx++][c] <<= 1;
					else next[++idx][c] = board[r][c];
				}
			}
		}
		
		// 하
		if(dir == 3) {
			for(int c=0; c<N; c++) {
				int idx = N-1;
				for(int r=N-1; r>=0; r--) {
					if(board[r][c] == 0) continue;
					if(next[idx][c] == 0) next[idx][c] = board[r][c];
					else if(next[idx][c] == board[r][c]) next[idx--][c] <<= 1;
					else next[--idx][c] = board[r][c];
				}
			}
		}
		
		// 최댓값 반환
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
		        max = Math.max(max, next[r][c]);
			}
		}
		return max;
	}
}