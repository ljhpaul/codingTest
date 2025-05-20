package baekjoon.step.ch05.boj27866;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//[B5] 문자와 문자열
//https://www.acmicpc.net/problem/27866
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		String s;
		int i;
		
		//input
		s = br.readLine();
		i = Integer.parseInt(br.readLine());
		
		//solution
		answer += s.charAt(i-1);
		
		//output
		System.out.println(answer);
		br.close();
	}
}