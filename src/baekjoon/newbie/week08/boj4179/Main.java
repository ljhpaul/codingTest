package baekjoon.newbie.week08.boj4179;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static String answer;
	static int R, C, sr, sc;
	static boolean[][] reachable;
	static List<int[]> fireStart;
	static int[][] fireTime;
	static int[][] moveTime;
	
	static final int INF = 1_000_000_000;
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = "";
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		reachable = new boolean[R][C];
		fireStart = new ArrayList<>();
		
		// input
		for(int r = 0; r < R; r++) {
			String line = br.readLine();
			for(int c = 0; c < C; c++) {
				switch(line.charAt(c)) {
				case '#' :
					break;
				case '.' : 
					reachable[r][c] = true;
					break;
				case 'J' : 
					reachable[r][c] = true;
					sr = r;
					sc = c;
					break;
				case 'F' : 
					fireStart.add(new int[] {r, c});
					break;
				}
			}
		}

		// solve
		calcFireTime();
		escape();

		// output
		System.out.println(answer);
		br.close();
	}
	
	// calc fireTime : BFS
	private static void calcFireTime() {
		// init
		Queue<int[]> q = new ArrayDeque<>();
		fireTime = new int[R][C];
		for(int[] line : fireTime) Arrays.fill(line, INF);
		
		// start
		for(int[] pos : fireStart) {
			q.offer(pos);
			fireTime[pos[0]][pos[1]] = 0;
		}
		
		// loop
		while(!q.isEmpty()) {
			// curr
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			
			// next
			for(int dir = 0; dir < 4; dir++) {
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
				if(!reachable[nr][nc]) continue;
				if(fireTime[r][c] + 1 >= fireTime[nr][nc]) continue;
				
				q.offer(new int[] {nr, nc});
				fireTime[nr][nc] = fireTime[r][c] + 1;
			}
		}
	}
	
	// escape : BFS
	private static void escape() {
		// init
		Queue<int[]> q = new ArrayDeque<>();
		moveTime = new int[R][C];
		for(int[] line : moveTime) Arrays.fill(line, INF);
		
		// start
		q.offer(new int[] {sr, sc});
		moveTime[sr][sc] = 0;
		
		// loop
		while(!q.isEmpty()) {
			// curr
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			
			// next
			for(int dir = 0; dir < 4; dir++) {
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				
				// escape success
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) {
					answer = (moveTime[r][c] + 1) + "";
					return;
				}
				if(!reachable[nr][nc]) continue;
				if(moveTime[r][c] + 1 >= fireTime[nr][nc]) continue;
				if(moveTime[r][c] + 1 >= moveTime[nr][nc]) continue;
				
				q.offer(new int[] {nr, nc});
				moveTime[nr][nc] = moveTime[r][c] + 1;
			}
		}
		
		// escape failed
		answer = "IMPOSSIBLE";
	}

}