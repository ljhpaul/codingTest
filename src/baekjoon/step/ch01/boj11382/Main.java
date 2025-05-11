package baekjoon.step.ch01.boj11382;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B5] 꼬마 정민
//https://www.acmicpc.net/problem/11382
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		long a, b, c;
		
		//input
		st = new StringTokenizer(br.readLine());
		a = Long.parseLong(st.nextToken());
		b = Long.parseLong(st.nextToken());
		c = Long.parseLong(st.nextToken());
		
		//solution
		answer += (a + b + c);
		
		//output
		System.out.println(answer);
		br.close();
	}
}
