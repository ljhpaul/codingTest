package baekjoon.algorithm.lis.binarysearch.boj12015;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int answer, N;
	static int[] arr;
	static List<Integer> lis;

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = 0;
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		lis = new ArrayList<>();
		
		// input
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// solve
		solve();

		// output
		System.out.println(answer);
		br.close();
	}

	// solve
	private static void solve() {
		// 모든 요소 순회
		for(int num : arr) {
			// 좌우 포인터
			int left = 0;
			int right = lis.size();
			
			// 이분 탐색으로 삽입 위치 찾기
			while(left < right) {
				int mid = (left + right) / 2;
				if(lis.get(mid) <= num) left = mid + 1;
				else right = mid;
			}
			
			// 해당 위치에 삽입 (list 크기보다 클 경우 add)
			if(left >= lis.size()) lis.add(num);
			else lis.set(left, num);
		}
		
		// LIS 길이 : lis의 길이
		answer = lis.size();
	}
}