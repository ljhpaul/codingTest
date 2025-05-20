package baekjoon.step.ch05.boj5622;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B2] 다이얼
//https://www.acmicpc.net/problem/5622
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		int answer = 0;
		String str;
		
		//input 
		str = br.readLine();
		
		//solution
		for(char ch : str.toCharArray()) {
			if(ch == 'Z') ch--;
			if(ch >= 'S') ch--;
			
			answer += (ch - 'A')/3 + 3;
		}
		
		//output
		System.out.println(answer);
		br.close();
	}
}