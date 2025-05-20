package baekjoon.step.ch05.boj1152;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B2] 단어의 개수
//https://www.acmicpc.net/problem/1152
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		String a, b;
		int numA, numB;
		
		//input & solution
		st = new StringTokenizer(br.readLine());
		a = st.nextToken();
		b = st.nextToken();
		
		numA = Integer.parseInt( String.valueOf(a.charAt(2))  + String.valueOf(a.charAt(1)) + String.valueOf(a.charAt(0)) ); 
		numB = Integer.parseInt( String.valueOf(b.charAt(2))  + String.valueOf(b.charAt(1)) + String.valueOf(b.charAt(0)) ); 
		
		answer += Math.max(numA, numB);
		
		//output
		System.out.println(answer);
		br.close();
	}
}