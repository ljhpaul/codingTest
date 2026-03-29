package baekjoon.algorithm.trie.boj16934;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static int N;
	static HashMap<String, Integer> nicknameCnt;
	static TrieNode root;
	
	// TrieNode(children, end)
	static class TrieNode {
		HashMap<Character, TrieNode> children;
		boolean end;
		public TrieNode() {
			children = new HashMap<>();
			end = false;
		}
	}
	
	// register(word)
	private static String register(String word) {
		// root
		TrieNode curr = root;
		StringBuilder prefix = new StringBuilder();
		String alias = null;
		
		// loop
		for(char c : word.toCharArray()) {
			prefix.append(c);
			
			if(!curr.children.containsKey(c)) {
				curr.children.put(c, new TrieNode());
				if(alias == null) alias = prefix.toString();
			}
			
			curr = curr.children.get(c);
		}
		// end
		curr.end = true;
		if(!nicknameCnt.containsKey(word)) nicknameCnt.put(word, 0);
		nicknameCnt.put(word, nicknameCnt.get(word) + 1);
		
		if(alias != null) return alias;
		int x = nicknameCnt.get(word);
		return x == 1 ? word : word + x;
	}
	
	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		nicknameCnt = new HashMap<>();
		root = new TrieNode();
		
		// solve
		for(int i = 0; i < N; i++) {
			String nickname = br.readLine();
			answer.append(register(nickname)).append("\n");
		}

		// output
		System.out.println(answer);
		br.close();
	}
}