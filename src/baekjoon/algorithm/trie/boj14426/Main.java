package baekjoon.algorithm.trie.boj14426;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static TrieNode root;
	
	// TrieNode
	static class TrieNode {
		private TrieNode[] children = new TrieNode[26];
		private boolean end = false;
		int childCount = 0;
		
		TrieNode getChild(char c) {
			return children[c - 'a'];
		}
		
		void setChild(char c, TrieNode node) {
			int idx = c - 'a';
			if(children[idx] == null && node != null) childCount++; 
			else if(children[idx] != null && node == null) childCount--;
			children[idx] = node;
		}
		
		boolean isEnd() { return end; }
		void setEnd(boolean end) { this.end = end; } 
	}
	
	// 1. insert(word)
	private static void insert(String word) {
		// root부터 시작
		TrieNode curr = root;
		
		// word 순회하면서 단어 체크
		for(char c : word.toCharArray()) {
			if(curr.getChild(c) == null) curr.setChild(c, new TrieNode());
			curr = curr.getChild(c);
		}
		
		// 단어 끝 마킹
		curr.setEnd(true);
	}
	
	// 2. startsWith(prefix)
	private static boolean startsWith(String prefix) {
		return findNode(prefix) != null;
	}
	
	// findNode(str)
	private static TrieNode findNode(String str) {
		// root부터 시작
		TrieNode curr = root;
		
		// str 순회하면서 단어 체크
		for(char c : str.toCharArray()) {
			if(curr.getChild(c) == null) return null;
			curr = curr.getChild(c);
		}
		
		// 마지막 노드 반환
		return curr;
	}

	// main
	public static void main(String[] args) throws IOException {
		// init
		int answer = 0;
		root = new TrieNode();
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// input
		for(int i = 0; i < N; i++) {
			insert(br.readLine());
		}

		// solve
		for(int i = 0; i < M; i++) {
			if(startsWith(br.readLine())) answer++;
		}		

		// output
		System.out.println(answer);
		br.close();
	}

}