package baekjoon.ssafy.boj3055;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static String answer;
	static int R, C, kaktusR, kaktusC;
	static List<int[]> waters;
	static char[][] map;
	static int[][] waterTime, kaktusTime;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = "";
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		waters = new ArrayList<>();
		map = new char[R][C];
		waterTime = new int[R][C];
		kaktusTime = new int[R][C];
		for(int[] line : waterTime) Arrays.fill(line, Integer.MAX_VALUE);
		for(int[] line : kaktusTime) Arrays.fill(line, Integer.MAX_VALUE);
		
		// input
		for(int r=0; r<R; r++) {
			String line = br.readLine();
			for(int c=0; c<C; c++) {
				char tmp = line.charAt(c);
				map[r][c] = tmp;
				if(tmp == 'S') {
					kaktusR = r;
					kaktusC = c;
				} else if(tmp == '*') {
					waters.add(new int[] {r, c});
				}
			}
		}
		
		// solve
		calcWaterTime();
		moveKaktus();
		
		// output
		System.out.println(answer);
		br.close();
	}

	// calcWaterTime - bfs
	private static void calcWaterTime() {
		// init
		Queue<int[]> q = new ArrayDeque<>();
		
		// first
		for(int[] w : waters) {
			q.offer(w);
			waterTime[w[0]][w[1]] = 0;
		}
		
		// loop
		while(!q.isEmpty()) {
			// cur
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			// next
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
				if(map[nr][nc] == 'X' || map[nr][nc] == 'D') continue;
				
				if(waterTime[r][c] + 1 < waterTime[nr][nc]) {
					q.offer(new int[] {nr, nc});
					waterTime[nr][nc] = waterTime[r][c] + 1;
				}
			}
		}
	}

	// moveKaktus - bfs
	private static void moveKaktus() { 
		// init
		Queue<int[]> q = new ArrayDeque<>();
		
		// first
		q.offer(new int[] {kaktusR, kaktusC});
		kaktusTime[kaktusR][kaktusC] = 0;
		
		// loop
		while(!q.isEmpty()) {
			// cur
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			// check
			if(map[r][c] == 'D') {
				answer = kaktusTime[r][c] + "";
				return;
			}
			
			// next
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
				if(map[nr][nc] == 'X') continue;
				if(kaktusTime[r][c] + 1 >= kaktusTime[nr][nc]) continue;
				
				if(kaktusTime[r][c] + 1 < waterTime[nr][nc]) {
					q.offer(new int[] {nr, nc});
					kaktusTime[nr][nc] = kaktusTime[r][c] + 1; 
				}
			}
		}
		
		// failed
		answer = "KAKTUS";
	}
}