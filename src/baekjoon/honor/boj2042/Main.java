package baekjoon.honor.boj2042;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static int N, M, K, sz;
	static long[] tree;

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int treeHeight = (int) Math.ceil(Math.log(N) / Math.log(2));
		sz = (int) Math.pow(2, treeHeight + 1);
		tree = new long[sz];
		
		// input
		for(int i=0; i<N; i++) {
			tree[sz/2 + i] = Long.parseLong(br.readLine());
		}

		// solve
		for(int i=sz/2-1; i>0; i--) {
			tree[i] = tree[2*i] + tree[2*i+1];
		}
		
		for(int i=0; i<M+K; i++) {
			st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			if(a == 1) {
				// 값 변경
				update(b, c);
			} else if(a == 2) {
				// 구간 합 구하기
				getSum(b, (int) c);
			}
		}
		
		// close
		System.out.println(answer);
		br.close();
	}

	// update : 값 변경
	private static void update(int idx, long replace) {
		// 트리 인덱스로 변환
		idx += sz/2 - 1;
		
		// 이전값과의 차이
		long gap = replace - tree[idx];
		
		while(idx > 0) {
			// 값 변경
			tree[idx] += gap;
			// 부모 노드로 이동
			idx /= 2;
		}
	}

	// get sum : 구간 합 구하기
	private static void getSum(int s, int e) {
		long sum = 0;
		
		// 트리 인덱스로 변환
		s += sz/2 - 1;
		e += sz/2 - 1;
		
		// 시작 인덱스가 마지막 인덱스보다 커지면 종료
		while(s <= e) {
			// 시작이 오른쪽 자식이면 값 반영
			if(s % 2 == 1) {
				sum += tree[s];
			}
			// 마지막이 왼쪽 자식이면 값 반영
			if(e % 2 == 0) {
				sum += tree[e];
			}
			// 각각 부모 노드로 옮기기
			s = (s + 1) / 2;
			e = (e - 1) / 2;
		}
		
		// 출력
		answer.append(sum).append("\n");
	}

	
}
/*
1. 트리 배열 초기화
- 2^k >= N 만족하는 k가 트리의 높이(height)
- 트리 배열 long[2^(height + 1)] 
2. 구간 합 구하기
- getSum
3. 데이터 업데이트
- update
*/
