package swea.d4.p4038;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            String text = br.readLine();
            String pattern = br.readLine();
            
            // solve
            System.out.println(Arrays.toString(buildPi(pattern)));
            List<Integer> matches = kmpSearch(text, pattern);
            System.out.println(matches);
            int answer = matches.size();

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    // build pi array
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
		
		// return pi
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
		int j = 0;	// 패턴 포인터
		for(int i = 0; i < n; i++) {
			// 불일치 -> j 후퇴(while)
			while(j > 0 && text.charAt(i) != pattern.charAt(j)) {
				j = pi[j - 1];
			}
			
			// 일치 -> 포인터 전진
			if(text.charAt(i) == pattern.charAt(j)) {
				j++;
			}
			
			// 패턴 전체 일치 -> 인덱스 저장, 다음 매칭위해 j 후퇴
			if(j == m) {
				result.add(i - m + 1);
				j = pi[j - 1];
			}
		}
		
		// return match idx
		return result;
	}
}
/**
2
ababa
aba
abracadabra
ab
*/