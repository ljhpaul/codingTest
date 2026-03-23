package swea.pro.p25958;

import java.io.*;
import java.util.*;

class UserSolution {
	// static field
	static int n, m;
	static Grade[][] grid;
	static String[][] strGrade;
	
	static final char MIN = (char) ('Z' + 1);
	static final char MAX = (char) ('A' - 1);
	static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};	// 상하좌우(0123)
	
	// Grade(first, second, third)
	static class Grade implements Comparable<Grade> {
		char first, second, third;

		public Grade() {}
		public Grade(char first, char second, char third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}

		@Override
		public int compareTo(Grade o) {
			if(this.first == o.first) {
				if(this.second == o.second) {
					return Character.compare(o.third, this.third);
				}
				return Character.compare(o.second, this.second);
			}
			return Character.compare(o.first, this.first);
		}
	}
	
	// Node(r, c, grade, remain)
	static class Node {
		int r, c, remain;
		Grade grade;

		public Node(int r, int c, Grade grade, int remain) {
			this.r = r;
			this.c = c;
			this.grade = grade;
			this.remain = remain;
		}
	}
	
	
	// init [100]
	void init(int N, int M, String mGrade[][]) {
		n = N;
		m = M;
		strGrade = mGrade;
		grid = new Grade[n][n];
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < n; c++) {
				grid[r][c] = encode(mGrade[r][c]);
			}
		}
	}
	
	// encode (String → Grade)
	Grade encode(String str) {
		char[] g = new char[3];
		for(int i = 0; i < 3; i++) {
			g[i] = i < str.length() ? str.charAt(i) : 'Z' + 1;
		}
		return new Grade(g[0], g[1], g[2]);
	}
	
	// decode (Grade → String)
	String decode(Grade grade) {
		StringBuilder sb = new StringBuilder();
		sb.append(grade.first);
		if('A' <= grade.second && grade.second <= 'Z') sb.append(grade.second);
		if('A' <= grade.third && grade.third <= 'Z') sb.append(grade.third);
		return sb.toString();
	}
	
	// change [200] (O)
	void change(int mRow, int mCol, int mDir, int mLength, String mGrade) {
		// 행 방향 증가
		if(mDir == 0) {
			for(int r = mRow; r < mRow + mLength; r++) {
				grid[r][mCol] = encode(mGrade);
			}
		}
		
		// 열 방향 증가
		if(mDir == 1) {
			for(int c = mCol; c < mCol + mLength; c++) {
				grid[mRow][c] = encode(mGrade);
			}
		}
		
		debug();
	}
	
	// calculate [300]
	String calculate(int L, int sRow, int sCol, int eRow, int eCol) {
		Node start = new Node(sRow, sCol, grid[sRow][sCol], L);
		Grade minGrade = bfs(start, eRow, eCol);
		System.out.println("[DEBUG] " + decode(minGrade));
		return decode(minGrade).trim();
	}

	// bfs
	private Grade bfs(Node start, int er, int ec) {
		// init
		Grade minGrade = new Grade(MAX, MAX, MAX);
//		Grade minGrade = new Grade(MIN, MIN, MIN);
//		System.out.println("[DEBUG] " + decode(minGrade));
		Queue<Node> q = new ArrayDeque<>();
		Grade[][] minKeys = new Grade[n][n];
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < n; c++) {
				minKeys[r][c] = new Grade(MAX, MAX, MAX);
//				minKeys[r][c] = new Grade(MIN, MIN, MIN);
			}
		}
		
		// start
		q.offer(start);
		minKeys[start.r][start.c] = start.grade; 
		
		// loop
		while(!q.isEmpty()) {
			// cur
			Node cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			
			// check
			if(cur.remain < 0) continue;
			if(minKeys[r][c].compareTo(cur.grade) < 0) continue;
			if(r == er && c == ec && cur.remain == 0) {	// 도착지에 사용 횟수를 정확히 소모하여 도착
				minGrade =  getMinGrade(cur.grade, minGrade);	// 보안등급 최소 갱신
			}
			
			// next
			for(int dir = 0; dir < 4; dir++) {
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				
				if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;	// 좌표 유효성 검사
				if(cur.remain < (Math.abs(nr - er) + Math.abs(nc - ec))) continue;	// 도착지까지의 멘하탄 거리보다 이동 가능 횟수가 적을 경우 스킵
				
				Grade nGrade = getMaxGrade(cur.grade, grid[nr][nc]);	// 현재 경로에서 필요한 가장 높은 보안 등급 계산
				if(nGrade.compareTo(minKeys[nr][nc]) > 0) continue;
				
				q.offer(new Node(nr, nc, nGrade, cur.remain - 1));
				minKeys[nr][nc] = nGrade;
			}
		}
		
		// return
		return minGrade;
	}
	
	// get min Grade
	private Grade getMinGrade(Grade g1, Grade g2) {
		return g1.compareTo(g2) < 0 ? g1 : g2;
	}
	
	// get max Grade
	private Grade getMaxGrade(Grade g1, Grade g2) {
		return g1.compareTo(g2) >= 0 ? g1 : g2;
	}
	
	// DEBUG
	private void debug() {
		for(Grade[] line : grid) {
			for(Grade g : line) {
				System.out.print(decode(g) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
