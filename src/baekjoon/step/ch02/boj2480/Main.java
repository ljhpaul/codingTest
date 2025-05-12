package baekjoon.step.ch02.boj2480;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B4] 주사위 세개
//https://www.acmicpc.net/problem/2480
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		int answer;
		int a, b, c;
		
		//input
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		//solution
		if(a == b && b == c) {
			answer = 10000 + a * 1000;
		} else if(a == b) {
			answer = 1000 + a * 100;
		} else if(b == c) {
			answer = 1000 + b * 100;
		} else if(c == a) {
			answer = 1000 + c * 100;
		} else {
			answer = 100 * Math.max(a, Math.max(b, c));
		}
		
		//output
		System.out.println(answer);
		br.close();
	}
}
