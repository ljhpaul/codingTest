package baekjoon.step.ch04.boj5597;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//[B3] 과제 안 내신 분..?
//https://www.acmicpc.net/problem/5597
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		int[] stdArr = new int[31];
		
		//input
		for(int i=0; i<28; i++) {
			int stdNo = Integer.parseInt(br.readLine());
			stdArr[stdNo]++;
		}
		
		//solution;
		for(int i=1; i<=30; i++) {
			if(stdArr[i] == 0) answer += i + "\n";
		}
		
		//output
		System.out.println(answer);
		br.close();
	}
}