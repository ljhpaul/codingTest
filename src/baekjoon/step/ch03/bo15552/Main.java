package baekjoon.step.ch03.bo15552;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B4] 빠른 A+B
//https://www.acmicpc.net/problem/15552
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		int t, a, b;
		
		//input
		t = Integer.parseInt(br.readLine());
		
		//solution
		for(int i=0; i<t; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			sb.append(a + b).append("\n");
		}
		
		//output
		System.out.println(sb);
		br.close();
	}
}