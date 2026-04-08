package baekjoon.algorithm.lis.binarysearch.boj1365;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int answer, N;
	static Pair[] pairs;
	static int[] arr;
	static List<Integer> lis;
	
	// Pair
	static class Pair {
		int a, b;

		public Pair(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}

	// main
	public static void main(String[] args) throws IOException {
		// init
		answer = 0;
		N = Integer.parseInt(br.readLine());
		pairs = new Pair[N];
		arr = new int[N];
		lis = new ArrayList<>();
		
		// input
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int b = Integer.parseInt(st.nextToken()) - 1;
			pairs[i] = new Pair(i, b);
		}

		// solve
		Arrays.sort(pairs, (o1, o2) -> Integer.compare(o1.a, o2.a));
		for(int i = 0; i < N; i++) arr[i] = pairs[i].b;
		solve();

		// output
		System.out.println(answer);
		br.close();
	}

	// solve
	private static void solve() {
		// 모든 요소 순회
		for(int num : arr) {
			// 좌우 포인터 선언
			int left = 0;
			int right = lis.size();
			
			// 이분탐색 -> 삽입 위치 찾기
			while(left < right) {
				int mid = (left + right) / 2;
				
				// lower bound(업격)
				if(lis.get(mid) < num) left = mid + 1;
				else right = mid;
			}
			
			// 해당 위치에 삽입(더 크면 add)
			if(left == lis.size()) lis.add(num);
			else lis.set(left, num);
		}
		
		// LIS 길이 : lis 크기
		answer = lis.size();
	}
}