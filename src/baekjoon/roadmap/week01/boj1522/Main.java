package baekjoon.roadmap.week01.boj1522;

import java.io.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	// main
	public static void main(String[] args) throws IOException {
		// init
		int answer = 0;
		String words = br.readLine();
		int p1 = 0;
		int p2 = -1;
		int bCnt = 0;
		int bMax = 0;
		
		// get answer
		for(char word : words.toCharArray()) {
			if(word == 'b') p2++;
		}
		
		words += words;
		answer = p2 + 1;
		
		for(int i=0; i<=p2; i++) {
			if(words.charAt(i) == 'b') bCnt++;
		}
		
		bMax = bCnt;
		
		while(p2 < words.length() - 1) {
			if(words.charAt(p1++) == 'b') bCnt--;
			if(words.charAt(++p2) == 'b') bCnt++;
			bMax = Math.max(bCnt, bMax);
		}
		
		answer -= bMax;
		
		// output
		System.out.println(answer);
	}
	
}