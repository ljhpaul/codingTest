package baekjoon.step.ch02.boj2525;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B3] 오븐 시계
//https://www.acmicpc.net/problem/2525
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		int a, b, c;
		
		//input
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(br.readLine());
		
		//solution
		int h = a;
		int m = b + c;
		
		h = (h + m / 60) % 24;
		m = m % 60;
		
		answer += h + " " + m;
		
		//output
		System.out.println(answer);
		br.close();
	}
}
