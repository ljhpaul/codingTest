package baekjoon.step.ch03.boj10951;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B5] A+B - 4
//https://www.acmicpc.net/problem/10951
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder answer = new StringBuilder();
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		int a, b;
		
		//solution
		while(true) {
			String input = br.readLine();
			if(input == null) break;
			st = new StringTokenizer(input, " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			answer.append(a + b).append("\n");
		}
		
		//output
		System.out.println(answer);
		br.close();
	}
}