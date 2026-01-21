package baekjoon.roadmap.week01.boj13458;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st; 

	// main
	public static void main(String[] args) throws IOException {
		// init
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		long answer = n;
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		// get answer
		for(int i=0; i<n; i++) {
			arr[i] = (arr[i] - b < 0) ? 0 : (arr[i] - b);
			answer += Math.ceil((double) arr[i] / c);
		}
		
		// output
		System.out.println(answer);
	}
	
}
