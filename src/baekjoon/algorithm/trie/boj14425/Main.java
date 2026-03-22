package baekjoon.algorithm.trie.boj14425;

import java.io.*;
import java.util.*;

class Trie {
	TrieNode root;
	
	public Trie() {
		root = new TrieNode();
	}

	// TrieNode
	public static class TrieNode {
		TrieNode[] children = new TrieNode[26];
		boolean isEndOfWord = false;
		int childCount = 0;
		
		public TrieNode getChild(char c) {
			return children[c - 'a'];
		}
		
		public void setChild(char c, TrieNode node) {
			int idx = c - 'a';
			if(children[idx] == null && node != null) childCount++;
			else if(children[idx] != null && node == null) childCount--;
			children[idx] = node;
		}
		
		public boolean isEndOfWord() { return isEndOfWord; }
		public void setEndOfWord(boolean end) { this.isEndOfWord = end; };
	}
	
	// 1. 삽입 : O(L)
	public void insert(String word) {
		// 시작 노드는 root
		TrieNode curr = root;
		
		// str 순회하면서 트라이에 삽입
		for(char c : word.toCharArray()) {
			if(curr.getChild(c) == null) {
				curr.setChild(c, new TrieNode());
			}
			curr = curr.getChild(c);
		}
		
		// 문자열 끝 마킹
		curr.setEndOfWord(true);
	}
	
	// 2. 검색 : O(L)
	public boolean search(String word) {
		TrieNode node = findNode(word);
		return node != null && node.isEndOfWord();
	}
	
	// 3. 접두사 존재 여부 확인
	public boolean startsWith(String prefix) {
		return findNode(prefix) != null;
	}
	
	// 4. 접두사로 시작하는 모든 단어 반환
	public List<String> autoComplete(String prefix) {
		List<String> words = new ArrayList<>();
		TrieNode node = findNode(prefix);
		if(node != null) {
			completeWords(words, node, new StringBuilder(prefix));
		}
		return words;
	}
	
	public void completeWords(List<String> words, TrieNode node, StringBuilder sb) {
		// 단어의 끝 여부 확인
		if(node.isEndOfWord()) words.add(sb.toString());
		
		// 소문자 내에서 자식 여부 확인 후 단어 이어가기(백트래킹)
		for(char c = 'a'; c <= 'z'; c++) {
			TrieNode child = node.getChild(c);
			if(child != null) {
				sb.append(c);
				completeWords(words, child, sb);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}

	// 5. 삭제 : O(L)
	public boolean delete(String word) {
		return delete(root, word, 0).deleted;
	}

	public DeleteResult delete(TrieNode curr, String word, int idx) {
		DeleteResult result = new DeleteResult();
		
		// 단어의 끝에 도달
		if(idx == word.length()) {
			// 실제 단어가 아닌 경우
			if(!curr.isEndOfWord()) {
				result.deleted = false;
				result.removable = false;
				return result;
			}
			// 실제 단어 삭제
			curr.setEndOfWord(false);
			result.deleted = true;
			result.removable = curr.childCount == 0;
			return result;
		}
		
		// word의 idx를 접근을 통해 자식 노드에 접근
		char c = word.charAt(idx);
		TrieNode child = curr.getChild(c);
		
		// 경로가 없으면 삭제 실패
		if(child == null) {
			result.deleted = false;
			result.removable = false;
			return result;
		}
		
		// 자식 노드로 재귀 호출하여 직계 자식의 삭제 여부와 끊어내기 가능 여부를 확인
		DeleteResult childResult = delete(child, word, idx + 1);
		
		// 자식에서 삭제 실패했다면 그대로 반환
		if(!childResult.deleted) return childResult;
		
		// 끊어내기가 가능할 경우 자식 null 처리
		if(childResult.removable) curr.setChild(c, null);
		
		// 현재 노드의 자식 존재 여부 확인 및 단어 끝 여부 전달
		result.deleted = true;
		result.removable = curr.childCount == 0 && !curr.isEndOfWord();
		
		return result;
	}
	
	public static class DeleteResult {
		boolean deleted;
		boolean removable;
	}

	// find node
	public TrieNode findNode(String str) {
		// 시작 노드는 root
		TrieNode curr = root;
		
		// str 순회하면서 존재 여부 체크
		for(char c : str.toCharArray()) {
			if(curr.getChild(c) == null) return null;
			curr = curr.getChild(c);
		}
		
		// 마지막 노드 반환
		return curr;
	}
}

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	// main
	public static void main(String[] args) throws IOException {
		// init
		Trie trie = new Trie();
		int answer = 0;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// input
		for(int i = 0; i < N; i++) {
			trie.insert(br.readLine());
		}
		
		// solve
		for(int i = 0; i < M; i++) {
			if(trie.search(br.readLine())) answer++;
		}
		
		// output
		System.out.println(answer);
		br.close();
	}

}