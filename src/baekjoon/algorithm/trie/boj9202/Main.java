package baekjoon.algorithm.trie.boj9202;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static int w, b, score, count;
	static String longest;
	static HashSet<String> words;
	static TrieNode root;
	static char[][] board;
	static boolean[][] visited;
	
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	
	// TrieNode
	static class TrieNode {
		TrieNode[] children;
		boolean end;
		String word;
		
		public TrieNode() {
			children = new TrieNode[26];
			end = false;
			word = "";
		}
		
		TrieNode getChild(char c) {
			return children[c - 'A'];
		}
		
		void setChild(char c, TrieNode node) {
			children[c - 'A'] = node;
		}
	}
	
	// insert(word)
	static void insert(String word) {
		// root
		TrieNode curr = root;
		
		// loop
		for(char c : word.toCharArray()) {
			if(curr.getChild(c) == null) curr.setChild(c, new TrieNode());
			curr = curr.getChild(c);
		}
		
		// end
		curr.end = true;
		curr.word = word;
	}

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		w = Integer.parseInt(br.readLine());
		root = new TrieNode();
		
		// input
		for(int i = 0; i < w; i++) {
			String word = br.readLine();
			insert(word);
		}
		br.readLine();
		
		// solve
		b = Integer.parseInt(br.readLine());
		for(int i = 0; i < b; i++) {
			board = new char[4][4];
			for(int r = 0; r < 4; r++) {
				board[r] = br.readLine().toCharArray();
			}
			solve(board);
			if(i < b - 1) br.readLine();
		}

		// output
		System.out.println(answer);
		br.close();
	}

	// solve
	private static void solve(char[][] board) {
		visited = new boolean[4][4];
		words = new HashSet<>();
		score = 0;
		longest = "";
		count = 0;
		
		for(int r = 0; r < 4; r++) {
			for(int c = 0; c < 4; c++) {
				TrieNode start = root.getChild(board[r][c]);
				if(start == null) continue;
				
				visited[r][c] = true;
				dfs(r, c, start);
				visited[r][c] = false;
			}
		}
		
		answer.append(score).append(" ")
			  .append(longest).append(" ")
			  .append(count).append("\n");
	}

	// dfs
	private static void dfs(int r, int c, TrieNode curr) {
		// found word
		if(curr.end && !words.contains(curr.word)) {
			words.add(curr.word);
			score += getScore(curr.word.length());
			longest = getLongest(curr.word, longest);
			count++;
		}
		
		// recursion
		for(int dir = 0; dir < 8; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if(nr < 0 || nr >= 4 || nc < 0 || nc >= 4) continue;
			if(visited[nr][nc]) continue;
			
			TrieNode next = curr.getChild(board[nr][nc]);
			if(next == null) continue;
			
			visited[nr][nc] = true;
			dfs(nr, nc, next);
			visited[nr][nc] = false;
		}
	}
	
	// get score
	private static int getScore(int length) {
		if(length <= 2) return 0;
		if(length <= 4) return 1;
		if(length == 5) return 2;
		if(length == 6) return 3;
		if(length == 7) return 5;
		else return 11;
	}

	// get longest
	private static String getLongest(String str1, String str2) {
		if(str1.length() == str2.length()) {
			return str1.compareTo(str2) < 0 ? str1 : str2;
		} else if (str1.length() > str2.length()) {
			return str1;
		} else {
			return str2;
		}
	}
}