package baekjoon.algorithm.trie.boj16934;

import java.io.*;
import java.util.*;

public class Main2 {
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
	
	// 1.insert(word)
	private static void insert(String word) {
		// root
		TrieNode curr = root;
		// loop
		for(char c : word.toCharArray()) {
			curr.children.putIfAbsent(c, new TrieNode());
			curr = curr.children.get(c);
		}
		// end
		curr.end = true;
		if(!nicknameCnt.containsKey(word)) nicknameCnt.put(word, 0);
		nicknameCnt.put(word, nicknameCnt.get(word) + 1);
	}
	
	// 2.startsWtih(prefix)
	private static boolean startsWith(String prefix) {
		return findNode(prefix) != null;
	}
	
	// 3.findNode(str)
	private static TrieNode findNode(String prefix) {
		// root
		TrieNode curr = root;
		// loop
		for(char c : prefix.toCharArray()) {
			if(!curr.children.containsKey(c)) return null;
			curr = curr.children.get(c);
		}
		// return
		return curr;
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
			String currAlias = "";
			StringBuilder tmp = new StringBuilder();
			boolean flag = false;
			
			// 별칭 등록 시도
			for(char c : nickname.toCharArray()) {
				tmp.append(c);
				currAlias = tmp.toString();
				// 접두사 없으면 별칭 등록
				if(!startsWith(currAlias)) {
					flag = true;
					break;
				}
			}
			
			// 닉네임 등록
			insert(nickname);
			
			// 별칭 등록 안 됨
			if(!flag) {
				int x = nicknameCnt.get(nickname);
				currAlias = nickname + (x == 1 ? "" : x);
			}
			
			answer.append(currAlias).append("\n");
		}

		// output
		System.out.println(answer);
		br.close();
	}
}