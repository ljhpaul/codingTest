package baekjoon.step.ch03.boj10952;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B5] A+B - 5
//https://www.acmicpc.net/problem/10952
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
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			if(a == 0 && b == 0) break;
			
			answer.append(a + b).append("\n");
		}
		
		//output
		System.out.println(answer);
		br.close();
	}
}