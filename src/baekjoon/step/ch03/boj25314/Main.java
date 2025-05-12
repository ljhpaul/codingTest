package baekjoon.step.ch03.boj25314;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//[B5] 코딩은 체육과목 입니다
//https://www.acmicpc.net/problem/25314
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		int n;
		
		//input
		n = Integer.parseInt(br.readLine());
		
		//solution & output
		for(int i=0; i<n/4; i++) {
			System.out.print("long ");
		}
		System.out.println("int");
		
		br.close();
	}
}