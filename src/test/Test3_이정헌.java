package test;

import java.io.*;
import java.util.*;

public class Test3_이정헌 {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer st;
	
	static StringBuilder answer;
	static int H, W, sr, sc, dir;
	static char[][] map;
	static Map<Character, Integer> encode;
	
	static int[] dr = {-1, 1, 0, 0};	// 상하좌우(0123)
	static int[] dc = {0, 0, -1, 1};
	static char[] decode = {'^', 'v', '<', '>'};

	// main
	public static void main(String[] args) throws Exception {
		// 로빈 방향 인코딩
		encode = new HashMap<>();
		encode.put('^', 0);
		encode.put('v', 1);
		encode.put('<', 2);
		encode.put('>', 3);
		
		// test case
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			// init
			answer = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			
			// input
			for(int r=0; r<H; r++) {
				String line = br.readLine();
				for(int c=0; c<W; c++) {
					char tmp = line.charAt(c);
					if(tmp == '^' || tmp == 'v' || tmp == '<' || tmp == '>') {
						map[r][c] = 'T';
						sr = r;
						sc = c;
						dir = encode.get(tmp);
					} else {
						map[r][c] = tmp;
					}
				}
			}
			
			int N = Integer.parseInt(br.readLine());
			char[] cmd = br.readLine().toCharArray();
			
			// solve
			for(char c : cmd) {
				switch(c) {
				case 'U':
					dir = 0;
					move();
					break;
				case 'D':
					dir = 1;
					move();
					break;
				case 'L':
					dir = 2;
					move();
					break;
				case 'R':
					dir = 3;
					move();
					break;
				case 'S':
					shoot();
					break;
				}
			}
			
			
			// output
			map[sr][sc] = decode[dir];
			for(int r=0; r<H; r++) {
				for(int c=0; c<W; c++) {
					answer.append(map[r][c]);
				}
				answer.append("\n");
			}
			System.out.println("#" + tc + " " + answer);
		}
		
	}

	// move
	private static void move() {
		int nr = sr + dr[dir];
		int nc = sc + dc[dir];
		
		if(nr < 0 || nr >= H || nc < 0 || nc >= W) return;
		// 평지 이동
		if(map[nr][nc] == '.') {
			map[sr][sc] = '.';
			sr = nr;
			sc = nc;
			map[sr][sc] = 'T';
		}
	}

	// shoot
	private static void shoot() {
		int nr = sr;
		int nc = sc;
		while(true) {
			nr += dr[dir];
			nc += dc[dir];
			
			if(nr < 0 || nr >= H || nc < 0 || nc >= W) return;
			if(map[nr][nc] == '*') {
				map[nr][nc] = '.';
				return;
			} else if(map[nr][nc] == '#') {
				return;
			}
		}
	}
}
