package baekjoon.step.ch02.boj9498;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//[B5] 시험 성적
//https://www.acmicpc.net/problem/9498
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		int score;
		String answer = "";
		
		//input
		score = Integer.parseInt(br.readLine());
		
		//solution
		if(score >= 90) {
			answer += "A";
		} else if(score >= 80) {
			answer += "B";
		} else if(score >= 70) {
			answer += "C";
		} else if(score >= 60) {
			answer += "D";
		} else {
			answer += "F";
		}
		
		//output
		System.out.println(answer);
	}
}
