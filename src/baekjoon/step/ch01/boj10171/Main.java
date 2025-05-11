package baekjoon.step.ch01.boj10171;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B5] 고양이
//https://www.acmicpc.net/problem/10171
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
		answer += "\\    /\\" + "\n";
		answer += " )  ( \')" + "\n";
		answer += "(  /  )" + "\n";
		answer += " \\(__)|";
		
		//output
		System.out.println(answer);
		br.close();
	}
}
