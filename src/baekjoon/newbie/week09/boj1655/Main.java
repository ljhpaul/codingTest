package baekjoon.newbie.week09.boj1655;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	// main
	public static void main(String[] args) throws IOException {
		// init
		StringBuilder answer = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		
		// solve
		for(int i = 0; i < N; i++) {
			int size = (int) Math.ceil(i / 2);
			int num = Integer.parseInt(br.readLine());
			while(pq.peek() < num) {
				if(pq.size() == size || ) break;
//				System.out.println(pq);
				pq.poll();
			}
			pq.offer(num);
			System.out.println(pq);
		}

		// output
		System.out.println(answer);
		br.close();
	}

}