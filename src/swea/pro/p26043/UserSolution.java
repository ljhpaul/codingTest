package swea.pro.p26043;

import java.io.*;
import java.util.*;

class UserSolution {
	/* ------ 트라이 구조 구현 ------ */
	// TrieNode 클래스
	static class Node {
		Node[] children = new Node[26];
		boolean isEndOfWord;
		int importance, regSeq, subTreeCount;
	}

	// 전역 관리 변수
	static int globalSeq = 1;
	static int pageNo;
	
	// 루트 노드
	Node root = new Node();
	
	// 삽입
	private void insert(String word, int importance) {
		// 루트 노드부터 시작
		Node node = this.root;
		
		// 내려가면서 삽입
		for(char c : word.toCharArray()) {
			if(node.children[c - '0'] == null) node.children[c - '0'] = new Node();
			
			node.subTreeCount++;	// 서브 트리 개수 추가
			node = node.children[c - '0'];	// 다음 노드로 이동
		}
		
		// 마지막 노드 조건 추가
		node.isEndOfWord = true;
		node.importance = importance;
		node.regSeq = globalSeq++;
	}
	
	// 페이지 번호 찾기
	private int findPageNo(String word) {
		
		return 1;
	}
	
	// k번째 단어 찾기
	private String findKth(int k) {
		
		return "";
	}
	
	// prefix 탐색
	private Node findPrefixNode(String prefix) {
		
		return null;
	}
	
	/* ------ 주요 로직 메서드 ------ */
	// init
	public void init() {
		// a 단어 등록(중요도 1, 등록 순서 1)
		insert("a", 1);
		pageNo = 1;
		return;
	}

	// add
	public Solution.PAGE add(String mWord, int mImportance) {
		Solution.PAGE res = new Solution.PAGE();
		res.no =  -1;
		
		insert(mWord, mImportance);

		return res;
	}

	// move
	public Solution.PAGE move(int mDir) {
		Solution.PAGE res = new Solution.PAGE();
		res.no =  -1;

		return res;
	}

	// search
	public Solution.PAGE search(String mStr) {
		Solution.PAGE res = new Solution.PAGE();
		res.no =  -1;

		return res;
	}

	// go
	public Solution.PAGE go(int mNo) {
		Solution.PAGE res = new Solution.PAGE();
		res.no =  -1;

		return res;
	}
}