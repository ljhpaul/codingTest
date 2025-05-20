package baekjoon.step.ch04.boj10810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//[B3] 공 널기
//https://www.acmicpc.net/problem/10810
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		int[] basket;
		int n, m, i, j, k;
		
		//input
		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		//solution
		basket = new int[n];
		for(int t=0; t<m; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			i = Integer.parseInt(st.nextToken());
			j = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			for(int idx=i-1; idx<j; idx++) {
				basket[idx] = k;
			}
		}
		for(int ball : basket) {
			answer += ball + " ";
		}
		
		//output
		System.out.println(answer);
		br.close();
	}
}