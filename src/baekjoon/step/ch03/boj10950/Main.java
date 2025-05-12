package baekjoon.step.ch03.boj10950;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B5] A+B - 3
//https://www.acmicpc.net/problem/10950
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		int t, a, b;
		
		//input
		t = Integer.parseInt(br.readLine());
		
		//solution
		for(int i=0; i<t; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			answer += (a + b) + "\n";
		}
		
		//output
		System.out.println(answer);
		br.close();
	}
}