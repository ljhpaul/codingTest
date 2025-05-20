package baekjoon.step.ch05.boj9086;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//[B5] 문자열
//https://www.acmicpc.net/problem/9086
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		int t;
		
		//input & solution
		t = Integer.parseInt(br.readLine());
		for(int i=0; i<t; i++) {
			String str = br.readLine();
			answer += String.valueOf(str.charAt(0)) + String.valueOf(str.charAt(str.length()-1)) + "\n";
		}
		
		//output
		System.out.println(answer);
		br.close();
	}
}