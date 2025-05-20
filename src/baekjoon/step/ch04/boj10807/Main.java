package baekjoon.step.ch04.boj10807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B5] 개수 세기
//https://www.acmicpc.net/problem/10807
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		int[] nums;
		int n, v;
		int answer = 0;
		
		//input
		n = Integer.parseInt(br.readLine());
		nums = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		v = Integer.parseInt(br.readLine());
		
		//solution
		for(int num : nums) {
			if(num == v) {
				answer++;
			}
		}
		
		//output
		System.out.println(answer);
		br.close();
	}
}