package baekjoon.newbie.week05.boj2138;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	// main
	public static void main(String[] args) throws IOException {
		// init
		int answer = 0;
		int N = Integer.parseInt(br.readLine());
		char[] state = br.readLine().toCharArray();
		char[] goal = br.readLine().toCharArray();

		char[] s1 = Arrays.copyOf(state, N);
		char[] s2 = Arrays.copyOf(state, N);
		
		// solve
		int cnt1 = solve(s1, goal, false);
		int cnt2 = solve(s2, goal, true);
		
		if(cnt1 == -1 && cnt2 == -1) answer = -1;
		else if(cnt1 == -1) answer = cnt2;
		else if(cnt2 == -1) answer = cnt1;
		else answer = Math.min(cnt1, cnt2);
		
		// output
		System.out.println(answer);
		br.close();
	}
	
	// toggle
	private static void toggle(char[] state, int idx) {
		for(int i=idx-1; i<=idx+1; i++) {
			if(i < 0 || i >= state.length) continue;
			if(state[i] == '0') state[i] = '1';
			else state[i] = '0';
		}
	}
	
	// solve
	private static int solve(char[] state, char[] goal, boolean isFirstToggled) {
		int cnt = 0;
		
		if(isFirstToggled) {
			toggle(state, 0);
			cnt++;
		}
		
		for(int i=1; i<state.length; i++) {
			if(state[i-1] != goal[i-1]) {
				toggle(state, i);
				cnt++;
			}
			if(i == state.length - 1 && state[i] != goal[i]) {
				cnt = -1;
			}
		}
		
		return cnt;
	}
}