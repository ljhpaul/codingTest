package baekjoon.algorithm.trie.boj7432;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static TrieNode root;
	
	// TrieNode
	static class TrieNode {
		TreeMap<String, TrieNode> children;
		
		public TrieNode() {
			children = new TreeMap<>();
		}
	}
	
	// insert(path)
	private static void insert(String path) {
		// root start
		TrieNode curr = root;
		
		// loop path
		st = new StringTokenizer(path, "\\");
		while(st.hasMoreTokens()) {
			String dir = st.nextToken();
			if(!curr.children.containsKey(dir)) curr.children.put(dir, new TrieNode());
			curr = curr.children.get(dir);
		}
	}

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		root = new TrieNode();
		int N = Integer.parseInt(br.readLine());
		
		// input
		for(int i = 0; i < N; i++) {
			insert(br.readLine());
		}

		// solve
		dfs(root, 0);

		// output
		System.out.println(answer);
		br.close();
	}
	
	// dfs
	private static void dfs(TrieNode node, int depth) {
		// base
		if(node.children.size() == 0) return;
		
		// recursion
		for(String dir : node.children.keySet()) {
			for(int i = 0; i < depth; i++) answer.append(" ");
			answer.append(dir).append("\n");
			dfs(node.children.get(dir), depth + 1);
		}
	}
}