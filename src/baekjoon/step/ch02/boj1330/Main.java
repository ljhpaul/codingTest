package baekjoon.step.ch02.boj1330;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B5] 두 수 비교하기
//https://www.acmicpc.net/problem/1330
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		int a;
		int b;
		String answer = "";
		
		//input
		st = new StringTokenizer(br.readLine(), " ");
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		//solution
		if(a > b) {
			answer += ">";
		} else if(a < b) {
			answer += "<";
		} else {
			answer += "==";
		}
		
		//output
		System.out.println(answer);
	}
}
