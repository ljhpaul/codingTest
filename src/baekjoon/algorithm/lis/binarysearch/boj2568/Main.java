package baekjoon.algorithm.lis.binarysearch.boj2568;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static StringBuilder answer;
	static int N;
	static Pair[] pairs;
	static int[] arr, pos;
	static List<Integer> lis;
	static boolean[] isLis;
	
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
		answer = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		pairs = new Pair[N];
		arr = new int[N];
		pos = new int[N];
		lis = new ArrayList<>();
		isLis = new boolean[N];
		
		// input
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			pairs[i] = new Pair(a, b);
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
		for(int i = 0; i < N; i++) {
			// 좌우 포인터 선언
			int left = 0;
			int right = lis.size();
			
			// 이분탐색으로 삽입 위치 선정
			while(left < right) {
				int mid = (left + right) / 2;
				
				// lower bound(엄격)
				if(lis.get(mid) < arr[i]) left = mid + 1;
				else right = mid;
			}
			
			// 해당 위치에 삽입 (lis 크기보다 클 경우 add)
			if(left >= lis.size()) lis.add(arr[i]);
			else lis.set(left, arr[i]);
			
			// 위치만 기록
			pos[i] = left;
		}
		
		// lis 경로 추적
		int target = lis.size() - 1;
		for(int i = N - 1; i >= 0; i--) {
			if(pos[i] == target) {
				isLis[i] = true;
				target--;
			}
		}
		
		// 전깃줄 출력
		answer.append(N - lis.size()).append("\n");
		for(int i = 0; i < N; i++) {
			if(!isLis[i]) answer.append(pairs[i].a).append("\n");
		}
	}
}