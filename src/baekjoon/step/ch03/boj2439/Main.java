package baekjoon.step.ch03.boj2439;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//[B5] 별 찍기 - 2
//https://www.acmicpc.net/problem/2438
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder answer = new StringBuilder();
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		int n;
		
		//input
		n = Integer.parseInt(br.readLine());
		
		//solution
		for(int i=1; i<=n; i++) {
			for(int j=n; j>i; j--) {
				answer.append(" ");
			}
			for(int j=0; j<i; j++) {
				answer.append("*");
			}
			answer.append("\n");
		}
		
		//output
		System.out.println(answer);
		br.close();
	}
}