package baekjoon.step.ch05.boj11654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//[B5] 아스키 코드
//https://www.acmicpc.net/problem/11654
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		String str;
		
		//input
		str = br.readLine();
		
		//solution
		answer += (int) str.charAt(0);
		
		//output
		System.out.println(answer);
		br.close();
	}
}