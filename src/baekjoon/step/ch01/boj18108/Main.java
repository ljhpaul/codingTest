package baekjoon.step.ch01.boj18108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//[B5] 1998년생인 내가 태국에서는 2541년생?!
//https://www.acmicpc.net/problem/18108
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		int y;
		
		//input
		y = Integer.parseInt(br.readLine());
		
		//solution
		answer += y - 543;
		
		//output
		System.out.println(answer);
		br.close();
	}
}
