package baekjoon.step.ch02.boj14681;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//[B5] 사분면 고르기
//https://www.acmicpc.net/problem/14681
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		int answer = 0;
		int x, y;
		
		//input
		x = Integer.parseInt(br.readLine());
		y = Integer.parseInt(br.readLine());
		
		//solution
		if(x>0 && y>0) {
			answer = 1;
		} else if(x<0 && y>0) {
			answer = 2;
		} else if(x<0 && y<0) {
			answer = 3;
		} else if(x>0 && y<0) {
			answer = 4;
		} 
		
		//output
		System.out.println(answer);
	}
}
