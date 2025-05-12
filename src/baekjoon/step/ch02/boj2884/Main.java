package baekjoon.step.ch02.boj2884;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B3] 알람 시계
//https://www.acmicpc.net/problem/2884
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		int h, m;
		
		//input
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		//solution
		 if(m < 45) {
			 if(h == 0) {
				 h = 23;
			 } else {
				 h--;
			 }
			 m += 15;
		 } else {
			 m -= 45;
		 }
		 
		 answer += h + " " + m;
		
		//output
		System.out.println(answer);
		br.close();
	}
}
