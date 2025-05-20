package baekjoon.step.ch05.boj2908;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B2] 단어의 개수
//https://www.acmicpc.net/problem/1152
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		String str;
		
		//input & solution
		str = br.readLine().trim();
		if(str == "") {
			answer += 0;
		} else {
			answer += str.split(" ").length;
		}
		
		//output
		System.out.println(answer);
		br.close();
	}
}