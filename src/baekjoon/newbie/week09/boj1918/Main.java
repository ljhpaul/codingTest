package baekjoon.newbie.week09.boj1918;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	// main
	public static void main(String[] args) throws IOException {
		// init
		String infix = br.readLine();
		
		// solve
		String answer = infixToPostfix(infix);
		
		// output
		System.out.println(answer);
		br.close();
	}
	
	// infix -> postfix
	private static String infixToPostfix(String infix) {
		// init
		StringBuilder postfix = new StringBuilder();
		Stack<Character> stack = new Stack<>();
		
		// set priority
		Map<Character, Integer> priority = new HashMap<>();
		priority.put('(', 0);
		priority.put('+', 1);
		priority.put('-', 1);
		priority.put('*', 2);
		priority.put('/', 2);
		
		// loop
		for(char c : infix.toCharArray()) {
			if('A' <= c && c <= 'Z') {
				postfix.append(c);
			} else if(c == '(') {
				stack.add(c);
			} else if(c == ')') {
				while(!stack.isEmpty() && stack.peek() != '(') {
					postfix.append(stack.pop());
				}
				stack.pop();
			} else {
				while(!stack.isEmpty() && priority.get(stack.peek()) >= priority.get(c)) {
					postfix.append(stack.pop());
				}
				stack.push(c);
			}
		}
		while(!stack.isEmpty()) {
			postfix.append(stack.pop());
		}
		
		// return
		return postfix.toString();
	}

}