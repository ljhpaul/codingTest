package baekjoon.solvedac.class05.boj12100;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int answer, N;
	
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
			}
		}

		// solve
		dfs(0, board, 0);

		// output
		System.out.println(answer);
		br.close();
	}

	// dfs
	private static void dfs(int depth, int[][] board, int max) {
		// pruning
		if(max >= answer) return;
		
		// base
		if(depth == 10) {
			answer = Math.max(answer, max);
			return;
		}
		
		// recursion
		for(int dir=0; dir<4; dir++) {
			int[][] next = copyBoard(board);
			int nextMax = move(next, dir);
			dfs(depth + 1, next, nextMax);
		}
	}

	// copyBoard
	private static int[][] copyBoard(int[][] board) {
		int[][] copy = new int[N][N];
		return copy;
	}
	
	// move
	private static int move(int[][] board, int dir) {
		// init
		int max = 0;
		
		// 좌로 밀기
		for(int r=0; r<N; r++) {
			ArrayDeque<Integer> dq = new ArrayDeque<>();
			for(int c=0; c<N; c++) {
				// 합칠 수 있는 경우
			}
		}
		
		// 우로 밀기
		
		
		// 위로 밀기
		
		
		// 아래로 밀기
		
		
		// 최댓값 반환
		return max;
	}
}