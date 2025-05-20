package baekjoon.step.ch04.boj3052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//[B2] 나머지
//https://www.acmicpc.net/problem/3052
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		int answer = 0;
		int[] remainders = new int[42];
		
		//input & solution
		for(int i=0; i<10; i++) {
			int num = Integer.parseInt(br.readLine());
			remainders[num % 42]++;
		}
		
		for(int i=0; i<42; i++) {
			if(remainders[i] > 0) {
				answer++;
			}
		}
		
		//output
		System.out.println(answer);
		br.close();
	}
}