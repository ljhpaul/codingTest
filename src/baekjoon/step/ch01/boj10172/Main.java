package baekjoon.step.ch01.boj10172;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B5] 개
//https://www.acmicpc.net/problem/10172
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		
		//input
		
		//solution
		answer += "|\\_/|" + "\n";
		answer += "|q p|   /}" + "\n";
		answer += "( 0 )\"\"\"\\" + "\n";
		answer += "|\"^\"`    |" + "\n";
		answer += "||_/=\\\\__|";
		
		//output
		System.out.println(answer);
		br.close();
	}
}
