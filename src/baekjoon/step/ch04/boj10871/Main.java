package baekjoon.step.ch04.boj10871;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B5] X보다 작은 수
//https://www.acmicpc.net/problem/10871
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder answer = new StringBuilder();
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		int n, x;
		int[] nums;
		
		//input
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		nums = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		//solution
		for(int num : nums) {
			if(num < x) {
				answer.append(num).append(" ");
			}
		}
		
		//output
		System.out.println(answer);
		br.close();
	}
}