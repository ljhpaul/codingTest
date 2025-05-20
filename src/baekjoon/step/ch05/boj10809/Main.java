package baekjoon.step.ch05.boj10809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//[B2] 알파벳 찾기
//https://www.acmicpc.net/problem/10809
public class Main {
	//static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	//main
	public static void main(String[] args) throws IOException {
		//init
		String answer = "";
		String s;
		int[] alphabet = new int[26];
		
		//input
		s = br.readLine();
		
		//solution
		for(int i=0; i<26; i++) {
			alphabet[i] = -1;
		}
		
		for(int i=0; i<s.length(); i++) {
			int idx = s.charAt(i) - 'a';
			if(alphabet[idx] < 0) {
				alphabet[idx] = i;
			}
		}
		
		for(int pos : alphabet) {
			answer += pos + " ";
		}
		
		//output
		System.out.println(answer);
		br.close();
	}
}