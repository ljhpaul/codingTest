package baekjoon.step.ch04.boj2562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B3] 최댓값
//https://www.acmicpc.net/problem/2562
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder answer = new StringBuilder();
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		int num;
		int max = Integer.MIN_VALUE;
		int max_index = 0;
		
		//input & solution
		for(int i=1; i<=9; i++) {
			num = Integer.parseInt(br.readLine());
			if(num > max) {
				max = num;
				max_index = i;
			}
		}
		answer.append(max).append("\n").append(max_index);
		
		//output
		System.out.println(answer);
		br.close();
	}
}