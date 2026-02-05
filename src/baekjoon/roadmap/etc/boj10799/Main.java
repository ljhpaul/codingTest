package baekjoon.roadmap.etc.boj10799;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	// main
	public static void main(String[] args) throws IOException {
		// init
		Stack<Character> stk = new Stack<>();
		String str = br.readLine();
		int sum = 0;
		boolean prevOpen = false;

		// get answer
		for(char c : str.toCharArray()) {
			switch (c) {
				case '(':
					stk.push(c);
					prevOpen = true;
					break;
				case ')':
					stk.pop();
					if(prevOpen) sum += stk.size();
					else sum++;
					prevOpen = false;
					break;
			}
		}

		// output
		System.out.println(sum);
	}

}