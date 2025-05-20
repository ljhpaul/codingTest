package baekjoon.step.ch04.boj1546;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//[B1] 평균
//https://www.acmicpc.net/problem/1546
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		int n;
		int sum = 0;
		int m = Integer.MIN_VALUE;
		
		//input & solution
		n = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			int num = Integer.parseInt(st.nextToken());
			sum += num;
			m = Math.max(m, num);
		}
		
		answer += (double) sum / n / m * 100;
		
		//output
		System.out.println(answer);
		br.close();
	}
}