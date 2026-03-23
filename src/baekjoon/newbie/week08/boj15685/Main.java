package baekjoon.newbie.week08.boj15685;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int answer, N;
	static boolean[][] isDragon;
	static List<Integer> path;
	
	static int[] dr = {0, -1, 0, 1}; // 우상하좌(0123)
	static int[] dc = {1, 0, -1, 0};
	static int[] convertDir = {1, 2, 3, 0};
	
	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = 0;
		N = Integer.parseInt(br.readLine());
		isDragon = new boolean[101][101];
		
		// solve
		for(int i = 0; i < N; i++) {
			path = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			setDragon(r, c, d, g);
		}
		
		getAnswer();

		// output
		System.out.println(answer);
		br.close();
	}
	
	// set dragon
	private static void setDragon(int r, int c, int d, int g) {
		// init
		List<Integer> curr = new ArrayList<>();
		
		// start
		isDragon[r][c] = true;
		curr.add(d);
		
		// path
		for(int i = 0; i <= g; i++) {
			// dragon curve
			for(int dir : curr) {
				r += dr[dir];
				c += dc[dir];
				isDragon[r][c] = true;
				path.add(dir);
			}
			
			curr = getNextCurve();
		}
	}
	
	// get next curve
	private static List<Integer> getNextCurve() {
		List<Integer> next = new ArrayList<>();
		for(int i = path.size() - 1; i >= 0; i--) {
			next.add(convertDir[path.get(i)]);
		}
		return next;
	}

	// get answer
	private static void getAnswer() {
		for(int r = 0; r < 100; r++) {
			for(int c = 0; c < 100; c++) {
				if(isDragon[r][c] && isDragon[r + 1][c]
						&& isDragon[r][c + 1] && isDragon[r + 1][c + 1]) {
					answer++;
				}
			}
		}
	}
}