package baekjoon.training.s25284.boj2075;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	// main
	public static void main(String[] args) throws IOException {
		// init
		int answer = 0;
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		// input
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				
				if(pq.size() < N) {
					// pq의 크기를 N 이하로 유지
					pq.offer(num);
				} else if(pq.peek() < num) {
					// 새로 들어오는 값이 pq 내 가장 작은 값보다 큰 경우 원래 값을 빼고 새 값 삽입
					pq.poll();
					pq.offer(num);
				}
			}
		}

		// solve
		answer = pq.poll();
		
		// output
		System.out.println(answer);
		br.close();
	}
}