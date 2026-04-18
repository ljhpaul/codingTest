package baekjoon.newbie.week10.boj15683;

import java.io.*;
import java.util.*;

public class Main2 {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int answer, N, M;
	static int[][] room;
	static List<int[]> cctv;
	static List<Node> list;
	
	static int[] dr = {-1, 1, 0, 0};  // 상하좌우(0123)
	static int[] dc = {0, 0, -1, 1};
	static int[][][] watchDir = {
			null,
			{{0}, {1}, {2}, {3}},	// ↑, ↓, ←, →
			{{0, 1}, {2, 3}},	// ↕, ↔
			{{0, 3}, {3, 1}, {1, 2}, {2, 0}},	// └, ┌, ┐, ┘
			{{0, 2, 3}, {0, 1, 3}, {1, 2, 3}, {0, 1, 2}},	// ┴, ├, ┬, ┤
			{{0, 1, 2, 3}}	// ┼
	};
	
	// Node
	static class Node {
		int[] pos;
		int[] dirs;
		
		public Node(int[] pos, int[] dirs) {
			this.pos = pos;
			this.dirs = dirs;
		}
	}

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		room = new int[N][M];
		cctv = new ArrayList<>();
		list = new ArrayList<>();
		
		// input
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				room[r][c] = Integer.parseInt(st.nextToken());
				if(0 < room[r][c] && room[r][c] < 6) {
					cctv.add(new int[] {r, c});
				}
			}
		}

		// solve
		setDirection(0);

		// output
		System.out.println(answer);
		br.close();
	}

	// set direction
	private static void setDirection(int idx) {
		// base
		if(idx == cctv.size()) {
			int[][] copy = copyRoom();
			setWatch(copy);
			int blind = countBlind(copy);
			answer = Math.min(answer, blind);
			return;
		}
		
		// recursion
		int[] curr = cctv.get(idx);
		int r = curr[0];
		int c = curr[1];
		int type = room[r][c];
		for(int[] dirs : watchDir[type]) {
			list.add(new Node(curr, dirs));
			setDirection(idx + 1);
			list.remove(list.size() - 1);
		}
	}

	// copy room
	private static int[][] copyRoom() {
		int[][] copy = new int[N][M];
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				copy[r][c] = room[r][c];
			}
		}
		return copy;
	}

	// set watch
	private static void setWatch(int[][] copy) {
		// loop
		for(Node node : list) {
			int r = node.pos[0];
			int c = node.pos[1];
			
			for(int dir : node.dirs) {
				int nr = r;
				int nc = c;
				
				while(true) {
					nr += dr[dir];
					nc += dc[dir];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= M) break;
					if(copy[nr][nc] == 6) break;
					if(copy[nr][nc] > 0) continue;
					
					copy[nr][nc] = 7;
				}
			}
		}
	}

	// count blind
	private static int countBlind(int[][] copy) {
		int cnt = 0;
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(copy[r][c] == 0) cnt++;
			}
		}
		return cnt;
	}

}