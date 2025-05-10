package baekjoon.step.ch01.boj1001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B5] A-B
//https://www.acmicpc.net/problem/1001
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		int a;
		int b;
		int answer;
		
		//input
		st = new StringTokenizer(br.readLine(), " ");
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		//solution
		answer = a - b;
		
		//output
		System.out.println(answer);
	}
}
