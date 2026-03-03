package test;

import java.io.*;
import java.util.*;

public class Test4_이정헌 {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer st;
	
	static String answer;
	static int N, M, sr, sc;
	static int[][] map, poisonTime, indiTime;
	static List<int[]> poison;
	
	static final int INF = Integer.MAX_VALUE;
	static int[] dr = {-1, 1, 0, 0};	// 상하좌우(0123)
	static int[] dc = {0, 0, -1, 1};

	// main
	public static void main(String[] args) throws Exception {
		// test case
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			// init
			answer = "";
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			poison = new ArrayList<>();
			
			
			// input
			for(int r=0; r<N; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<M; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if(map[r][c] == 3) {
						sr = r;
						sc = c;
					} else if(map[r][c] == 2) {
						poison.add(new int[] {r, c});
					}
				}
			}
			
			// solve
			calcPoisonTime();
			indiEscape();
			
			// output
			System.out.println("#" + tc + " " + answer);
		}
	}

	// 다중 소스 BFS : 독가스 확산 시각 계산
	private static void calcPoisonTime() {
		// init
		poisonTime = new int[N][M];
		for(int[] line : poisonTime) Arrays.fill(line, INF);
		Queue<int[]> q = new ArrayDeque<>();
		
		for(int[] p : poison) {
			q.offer(p);
			poisonTime[p[0]][p[1]] = 0;
		}
		
		// loop
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			
			// delta 4
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if(poisonTime[nr][nc] != INF) continue;
				if(map[nr][nc] == 1) continue;
				
				// 독가스 확산 시간 계산
				q.offer(new int[] {nr, nc});
				poisonTime[nr][nc] = poisonTime[r][c] + 1;
			}
		}
	}

	// BFS : 인디의 유적 탈출기
	private static void indiEscape() {
		// init
		indiTime = new int[N][M];
		for(int[] line : indiTime) Arrays.fill(line, INF);
		Queue<int[]> q = new ArrayDeque<>();
		boolean isSafe = true;
		
		q.offer(new int[] {sr, sc});
		indiTime[sr][sc] = 0;
		
		// loop
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int r = curr[0];
			int c = curr[1];
			
			// 인디가 독가스 안전 영역에 위치하는지 확인
			if(poisonTime[r][c] != INF) {
				isSafe = false;
			}
			
			// delta 4
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 탈출 성공
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
					answer = (indiTime[r][c] + 1) + "";
					return;
				}
				
				if(indiTime[nr][nc] != INF) continue;
				if(map[nr][nc] == 1) continue;
				
				// 독가스 확산 시간보다 느리거나 같으면 이동 불가
				if(indiTime[r][c] + 1 >= poisonTime[nr][nc]) continue;
				
				q.offer(new int[] {nr, nc});
				indiTime[nr][nc] = indiTime[r][c] + 1;
			}
		}
		
		answer = isSafe ? "CANNOT ESCAPE" : "ZOMBIE";
	}
}
