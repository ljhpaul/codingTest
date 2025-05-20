package baekjoon.step.ch05.boj2675;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B2] 문자열 반복
//https://www.acmicpc.net/problem/2675
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		int t;
		
		//input & solution
		t = Integer.parseInt(br.readLine());
		for(int i=0; i<t; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			String s = st.nextToken();
			
			for(char ch : s.toCharArray()) {
				for(int j=0; j<r; j++) {
					answer += String.valueOf(ch);
				}
			}
			answer += "\n";
		}
		
		//output
		System.out.println(answer);
		br.close();
	}
}