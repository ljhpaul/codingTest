package baekjoon.step.ch03.boj8393;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B5] 합
//https://www.acmicpc.net/problem/8393
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		int answer = 0;
		int n;
		
		//input
		n = Integer.parseInt(br.readLine());
		
		//solution
		for(int i=1; i<=n; i++) {
			answer += i;
		}
//		answer = n * (n + 1) / 2;
		
		//output
		System.out.println(answer);
		br.close();
	}
}