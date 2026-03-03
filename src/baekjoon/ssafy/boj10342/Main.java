package baekjoon.ssafy.boj10342;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static int N;
	static int[] arr;

	// main
	public static void main(String[] args) throws Exception {
		// init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		
		// input
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// solve
		int S = Integer.parseInt(br.readLine());
		for(int i=0; i<S; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			if(gender == 1) {
				boy(num); // 남자
			} else {
				girl(num); // 여자
			}
		}
		
		// output
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<N; i++) {
			sb.append(arr[i]).append(" ");
			if(i%20==0) sb.append("\n");
		}
		sb.append(arr[N]).append("\n");
		System.out.println(sb);
	}
	
	// boy
	private static void boy(int num) {
		for(int i = num; i <= N; i += num) {
			toggle(i);
		}
	}
	
	// girl
	private static void girl(int num) {
		toggle(num);
		
		int p1 = num - 1;
		int p2 = num + 1;
		
		while(p1 >= 1 && p2 <= N) {
			if(arr[p1] == arr[p2]) {
				toggle(p1);
				toggle(p2);
				p1--; p2++;
			} else break;
		}
	}
	
	// toggle
	private static void toggle(int num) {
		if(arr[num] == 1) {
			arr[num] = 0;
		} else {
			arr[num] = 1;
		}
	}
}