package baekjoon.ssafy.boj2887;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	
	
	// main
	public static void main(String[] args) throws IOException {
		// init
		long answer = 0;
		
		// input
		

		// solve
		

		// output
		System.out.println(answer);
		br.close();
	}
	
	
}
/*
# Kruskal MST
1. 간선 배열(리스트), 유니온 파인드 배열(pr, sz) 초기화
2. 그래프(간선) 가중치 오름차순 정렬
3. 가중치 낮은 연산부터 연결 시도(find로 사이클 확인)
*/