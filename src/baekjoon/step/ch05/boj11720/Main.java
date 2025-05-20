package baekjoon.step.ch05.boj11720;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//[B4] 숫자의 합
//https://www.acmicpc.net/problem/11720
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		int answer = 0;
		int n;
		String str;
		
		//input
		n = Integer.parseInt(br.readLine());
		str = br.readLine();
		
		//solution
		char[] arr = str.toCharArray();
		for(int i=0; i<n; i++) {
			 int num = arr[i] - '0';
			 answer += num;
		}
		
		//output
		System.out.println(answer);
		br.close();
	}
}