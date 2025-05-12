package baekjoon.step.ch03.boj11022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B5] A+B - 8
//https://www.acmicpc.net/problem/11022
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder answer = new StringBuilder();
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		int t, a, b;
		
		//input		
		t = Integer.parseInt(br.readLine());
		
		//solution
		for(int i=1; i<=t; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			answer.append("Case #").append(i).append(": ")
				  .append(a).append(" + ").append(b).append(" = ")
				  .append(a + b).append("\n");
		}
		
		//output
		System.out.println(answer);
		br.close();
	}
}