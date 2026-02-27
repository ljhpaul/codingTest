package swea.d3.p1873;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static StringBuilder answer;
    static int H, W, N, row, col, dir;
    static char[][] map;
    static char[] cmdArr;
    
    static int[] dr = {-1, 1, 0, 0}; // 상하좌우(0123)
    static int[] dc = {0, 0, -1, 1};

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            answer = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            for(int r=0; r<H; r++) {
            	String line = br.readLine();
            	for(int c=0; c<W; c++) {
            		char tmp = line.charAt(c);
            		switch(tmp) {
        			case '^' :
        				row = r;
        				col = c;
        				dir = 0;
        				map[r][c] = 'T';
        				break;
        			case 'v' :
        				row = r;
        				col = c;
        				dir = 1;
        				map[r][c] = 'T';
        				break;
        			case '<' : 
        				row = r;
        				col = c;
        				dir = 2;
        				map[r][c] = 'T';
        				break;
        			case '>' : 
        				row = r;
        				col = c;
        				dir = 3;
        				map[r][c] = 'T';
        				break;
        			default :
        				map[r][c] = tmp;
        			}
            	}
            }
            N = Integer.parseInt(br.readLine());
            cmdArr = br.readLine().toCharArray();
            
            // solve
            run();
            setAnswer();

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

	// run
	private static void run() {
		// 명령어 수행
		for(char cmd : cmdArr) {
			switch(cmd) {
			case 'U':
				move(0);
				break;
			case 'D':
				move(1);
				break;
			case 'L':
				move(2);
				break;
			case 'R':
				move(3);
				break;
			case 'S':
				shoot();
				break;
			}
		}
	}

	// move
	private static void move(int d) {
		// 방향 변경
		dir = d;
		// 다음 이동 좌표
		int nr = row + dr[d];
		int nc = col + dc[d];
		// 맵 밖 벗어나는지 확인
		if(nr < 0 || nr >= H || nc < 0 || nc >= W) return;
		char next = map[nr][nc];
		// 평지인지 확인
		if(next != '.') return;
		// 이동, 맵+위치+방향 갱신
		map[row][col] = '.';
		row = nr;
		col = nc;
		map[row][col] = 'T';
	}

	// shoot
	private static void shoot() {
		// 탱크 위치에서 시작
		int r = row;
		int c = col;
		while(true) {
			// 포탄의 다음 위치
			r += dr[dir];
			c += dc[dir];
			// 포탄이 닿은 요소
			if(r < 0 || r >= H || c < 0 || c >= W) return;
			char now = map[r][c];
			if(now == '*') {
				// 벽돌일 경우
				map[r][c] = '.';
				return;
			} else if(now == '#') {
				// 강철일 경우
				return;
			}
		}
	}
	
	// set answer
    private static void setAnswer() {
		// 탱크 방향 반영
		switch(dir) {
		case 0:
			map[row][col] = '^';
			break;
		case 1:
			map[row][col] = 'v';
			break;
		case 2:
			map[row][col] = '<';
			break;
		case 3:
			map[row][col] = '>';
			break;
		}
		// 출력문 반영
        for(int r=0; r<H; r++) {
        	for(int c=0; c<W; c++) {
        		answer.append(map[r][c]);
        	}
        	if(r < H-1)	{
        		answer.append("\n");
        	}
        	
        }
	}
}