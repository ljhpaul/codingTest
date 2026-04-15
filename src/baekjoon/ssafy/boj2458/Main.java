package baekjoon.ssafy.boj2458;

import java.io.*;
import java.util.*;

class Main {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int answer, N, M;
    static boolean[][] relation;

    // main
    public static void main(String[] args) throws IOException {
    	// init
        answer = 0;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        relation = new boolean[N + 1][N + 1];

        // input
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	relation[a][b] = true;
        }
        
        // solve
        floydWarshall();

        // output
        System.out.println(answer);
        br.close();
    }

    // Floyd-Warshall
	private static void floydWarshall() {
		// loop (k-i-j)
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				if(!relation[i][k]) continue;
				for(int j = 1; j <= N; j++) {
					if(!relation[k][j]) continue;
					relation[i][j] = true;
				}
			}
		}
		
		// count
		for(int i = 1; i <= N; i++) {
			boolean flag = true;
			for(int j = 1; j <= N; j++) {
				if(i == j) continue;
				
				if(!relation[i][j] && !relation[j][i]) {
					flag = false;
					break;
				}
			}
			if(flag) answer++;
		}
	}
}