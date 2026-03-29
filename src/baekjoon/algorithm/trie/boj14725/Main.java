package baekjoon.algorithm.trie.boj14725;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static int N;
	static TrieNode root;
	
	// TrieNode(children, end)
	static class TrieNode {
		TreeMap<String, TrieNode> children;
		boolean end;
		
		public TrieNode() {
			children = new TreeMap<>();
			end = false;
		}
	}
	
	// insert
	private static void insert(String[] info) {
		// root
		TrieNode curr = root;
		
		// loop
		for(String str : info) {
			curr.children.putIfAbsent(str, new TrieNode());
			curr = curr.children.get(str);
		}
		
		// end
		curr.end = true;
	}

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		root = new TrieNode();
		
		// input
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int t = Integer.parseInt(st.nextToken());
			String[] info = new String[t];
			for(int j = 0; j < t; j++) {
				info[j] = st.nextToken();
			}
			insert(info);
		}

		// solve
		dfs(root, 0);

		// output
		System.out.println(answer);
		br.close();
	}
	
	// dfs
	private static void dfs(TrieNode curr, int depth) {
		// loop children
		for(String str : curr.children.keySet()) {
			for(int i = 0; i < depth; i++) answer.append("--");
			answer.append(str).append("\n");
			dfs(curr.children.get(str), depth + 1);
		}
	}
}