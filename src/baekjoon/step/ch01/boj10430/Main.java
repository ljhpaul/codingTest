package baekjoon.step.ch01.boj10430;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B5] 나머지
//https://www.acmicpc.net/problem/10430
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
		c = Integer.parseInt(st.nextToken());
		
		//solution
		answer += (a+b)%c + "\n";
		answer += ((a%c)+(b%c))%c + "\n";
		answer += (a*b)%c + "\n";
		answer += ((a%c)*(b%c))%c + "\n";
		
		//output
		System.out.println(answer);
		br.close();
	}
}
