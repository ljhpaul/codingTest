package baekjoon.step.ch01.boj10926;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//[B5] ??!
//https://www.acmicpc.net/problem/10926
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		String id;
		
		//input
		id = br.readLine();
		
		//solution
		answer += id + "??!";
		
		//output
		System.out.println(answer);
		br.close();
	}
}
