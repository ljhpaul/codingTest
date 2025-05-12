package baekjoon.step.ch03.boj2739;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B5] 구구단
//https://www.acmicpc.net/problem/2739
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		int n;
		
		//input
		n = Integer.parseInt(br.readLine());
		
		//solution
		for(int i=1; i<=9; i++) {
			answer += n + " * " + i + " = " + n * i + "\n";
		}
		 
		
		//output
		System.out.println(answer);
		br.close();
	}
}