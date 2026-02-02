package baekjoon.roadmap.etc.boj14500;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	// main
	public static void main(String[] args) throws IOException {
		// init
		int answer = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
//		int[][]

		// get answer
		// '-'
//		for(int i=0; i<)

		// output
//		System.out.println(answer);
	}

}

/*
====================================
# 문제 정리
브루트 포스 (N^2 = 250000 < 2^7)
실제 도형
- '-', '|' : (1 * 4), (4 * 1)
- 'ㅁ' : (2 * 2)
- 'L' * 4, 'ㄱ' * 4 : (3 * 2), (2 * 3)
- 's' * 2, 'ㄹ' * 2 : (3 * 2), (2 * 3)
- 'ㅗ', 'ㅓ', 'ㅜ', 'ㅏ' : (3 * 2), (2 * 3)
====================================
# 슈도 코드
각 도형 별로 저장
====================================
 */