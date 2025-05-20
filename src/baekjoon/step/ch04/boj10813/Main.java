package baekjoon.step.ch04.boj10813;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//[B2] 공 바꾸기
//https://www.acmicpc.net/problem/10813
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		int[] basket;
		int n, m, i, j;
		
		//input
		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		//solution
		basket = new int[n];
		for(int idx=0; idx<n; idx++) {
			basket[idx] = idx + 1;
		}
		for(int t=0; t<m; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			i = Integer.parseInt(st.nextToken()) - 1;
			j = Integer.parseInt(st.nextToken()) - 1;
			
			int temp = basket[i];
			basket[i] = basket[j];
			basket[j] = temp;
		}
		for(int ball : basket) {
			answer += ball + " ";
		}
		
		//output
		System.out.println(answer);
		br.close();
	}
}