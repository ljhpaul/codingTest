package baekjoon.step.ch04.boj10818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B3] 최소, 최대
//https://www.acmicpc.net/problem/10818
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder answer = new StringBuilder();
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		int n;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int[] nums;
		
		//input
		n = Integer.parseInt(br.readLine());
		nums = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		//solution
		for(int num : nums) {
			min = Math.min(min, num);
			max = Math.max(max, num);
		}
		answer.append(min).append(" ").append(max);
		
		//output
		System.out.println(answer);
		br.close();
	}
}