package baekjoon.step.ch03.boj25304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//[B4] 영수증
//https://www.acmicpc.net/problem/25304
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		int x, n, a, b;
		
		//input		
		x = Integer.parseInt(br.readLine());
		n = Integer.parseInt(br.readLine());
		
		//solution
		int sum = 0;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			sum += a * b;
		}
		
		if(x == sum) {
			answer += "Yes";
		} else {
			answer += "No";
		}
		
		//output
		System.out.println(answer);
		br.close();
	}
}