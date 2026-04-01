package baekjoon.newbie.week09.boj1655;

import java.io.*;
import java.util.*;

public class Main2 {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	// main
	public static void main(String[] args) throws IOException {
		// init
		StringBuilder answer = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		// left : max heap  
		PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder());
		left.add(-100_001);
		
		// right : min heap
		PriorityQueue<Integer> right = new PriorityQueue<>();
		right.add(100_001);
		
		// solve
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			// 왼쪽 삽입 과정
			if(left.size() == right.size()) {
				// 오른쪽 값보다 작거나 같으면 왼쪽 즉시 삽입
				if(num <= right.peek()) left.add(num);
				// 큰 경우 오른쪽 값을 왼쪽으로 이동 후 오른쪽에 현재 값 삽입
				else {
					left.add(right.poll());
					right.add(num);
				}
			}
			
			// 오른쪽 삽입 과정
			else {
				// 왼쪽 값보다 크거나 같으면 오른쪽 즉시 삽입
				if(num >= left.peek()) right.add(num);
				// 작은 경우 오른쪽 값을 왼쪽으로 이동 후 오른쪽에 현재 값 삽입
				else {
					right.add(left.poll());
					left.add(num);
				}
			}
			
			// 중앙값 출력
			answer.append(left.peek()).append("\n");
		}

		// output
		System.out.println(answer);
		br.close();
	}
}