package baekjoon.step.ch05.boj2743;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//[B5] 단어 길이 재기
//https://www.acmicpc.net/problem/2743
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		String s;
		
		//input
		s = br.readLine();
		
		//solution
		answer += s.length();
		
		//output
		System.out.println(answer);
		br.close();
	}
}