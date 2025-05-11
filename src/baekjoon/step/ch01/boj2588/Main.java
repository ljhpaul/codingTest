package baekjoon.step.ch01.boj2588;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//[B3] 곱셈
//https://www.acmicpc.net/problem/2588
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		int n1, n3, n4, n5, n6;
		String n2;
		
		//input
		n1 = Integer.parseInt(br.readLine());
		n2 = br.readLine();
		
		//solution
		n3 = n1 * (n2.charAt(2) - '0');
		n4 = n1 * (n2.charAt(1) - '0');
		n5 = n1 * (n2.charAt(0) - '0');
		n6 = n3 + n4*10 + n5*100;
		answer += n3 + "\n" + n4 + "\n" + n5 + "\n" + n6; 
		
		//output
		System.out.println(answer);
		br.close();
	}
}
