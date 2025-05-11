package baekjoon.step.ch01.boj10869;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B5] 사칙연산
//https://www.acmicpc.net/problem/10869
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	//main
	public static void main(String[] args) throws IOException {
		//init
//		String answer = "";
		StringBuilder answer = new StringBuilder();
		int a;
		int b;
		
		//input
		st = new StringTokenizer(br.readLine(), " ");
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		//solution
//		answer += ( a + b + "\n" );
//		answer += ( a - b + "\n" );
//		answer += ( a * b + "\n" );
//		answer += ( a / b + "\n" );
//		answer += ( a % b );
		answer.append(a+b).append("\n")
			  .append(a-b).append("\n")
			  .append(a*b).append("\n")
			  .append(a/b).append("\n")
			  .append(a%b);
		
		//output
		System.out.println(answer);
		br.close();
	}
}
