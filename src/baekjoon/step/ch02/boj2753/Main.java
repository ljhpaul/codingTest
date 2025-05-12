package baekjoon.step.ch02.boj2753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//[B5] 윤년
//https://www.acmicpc.net/problem/2753
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		int answer = 0;
		int year;
		
		//input
		year = Integer.parseInt(br.readLine());
		
		//solution
		if((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
			answer = 1;
		}
		
		//output
		System.out.println(answer);
	}
}
