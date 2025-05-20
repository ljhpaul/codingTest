package baekjoon.step.ch04.boj10811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//[B2] 바구니 뒤집기
//https://www.acmicpc.net/problem/10811
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
		
		//input & solution
		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		basket = new int[n+1];
		
		for(int idx=1; idx<=n; idx++) {
			basket[idx] = idx;
		}
		
		for(int t=0; t<m; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			i = Integer.parseInt(st.nextToken());
			j = Integer.parseInt(st.nextToken());
			
			while(i < j) {
				int temp = basket[i];
				basket[i] = basket[j];
				basket[j] = temp;
				i++;
				j--;
			}
		}
		
		for(int idx=1; idx<=n; idx++) {
			answer += basket[idx] + " ";
		}
		
		
		//output
		System.out.println(answer);
		br.close();
	}
}