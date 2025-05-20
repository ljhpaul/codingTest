package baekjoon.step.ch05.boj11718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//[B3] 그대로 출력하기
//https://www.acmicpc.net/problem/11718
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		String input;
		
		//input & solution
		while((input = br.readLine()) != null) {
			if(input == "") break;
			answer += input + "\n";
		}
		
		//output
		System.out.println(answer);
		br.close();
	}
}