package baekjoon.algorithm.kmp.boj1786;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	// main
	public static void main(String[] args) throws IOException {
		// input
		StringBuilder answer = new StringBuilder();
		String text = br.readLine();
		String pattern = br.readLine();

		// solve
		List<Integer> matches = kmpSearch(text, pattern);
		answer.append(matches.size()).append("\n");
		for(int idx : matches) answer.append(idx + 1).append(" ");

		// output
		System.out.println(answer);
		br.close();
	}
	
	// build pi arr
	private static int[] buildPi(String pattern) {
		// init
		int m = pattern.length();
		int[] pi = new int[m];
		
		// two pointer (k 검사 및 후퇴, i 무조건 전진)
		int k = 0;
		for(int i = 1; i < m; i++) {
			// 불일치 -> k 후퇴(while)
			while(k > 0 && pattern.charAt(i) != pattern.charAt(k)) {
				k = pi[k - 1];
			}
			
			// 일치 -> 패턴 일치 길이 증가
			if(pattern.charAt(i) == pattern.charAt(k)) {
				pi[i] = ++k;
			}
		}
		
		// return pi array
		return pi;
	}
	
	// KMP search
	private static List<Integer> kmpSearch(String text, String pattern) {
		// init
		List<Integer> result = new ArrayList<>();
		int n = text.length();
		int m = pattern.length();
		int[] pi = buildPi(pattern);
		
		// 각 배열 당 포인터 관리 (j 검사 및 후퇴, i 무조건 전진)
		int j = 0;
		for(int i = 0; i < n; i++) {
			// 불일치 -> j 후퇴(while)
			while(j > 0 && text.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}
			
			// 일치 -> 포인터 전진
			if(text.charAt(i) == pattern.charAt(j)) {
				j++;
			}
			
			// 전체 일치 -> 인덱스 저장, 다음 매칭 위해 j 후퇴
			if(j == m) {
				result.add(i - m + 1);
				j = pi[j - 1];
			}
		}
		
		// return match idx
		return result;
	}

}