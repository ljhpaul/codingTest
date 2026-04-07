package baekjoon.algorithm.dp.boj12865;

import java.io.*;
import java.util.*;

public class Main {
	// static field
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
    
    static int answer, N, K;
    static int[] weight, value;
    static int[][] dp;

	// main
	public static void main(String[] args) throws IOException {
		// init
        answer = 0;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        weight = new int[N + 1];
        value = new int[N + 1];
        dp = new int[N + 1][K + 1];
		
        // input
        for(int i = 1; i <= N; i++) {
        	st = new StringTokenizer(br.readLine());
        	weight[i] = Integer.parseInt(st.nextToken());
        	value[i] = Integer.parseInt(st.nextToken());
        }
        
        // solve
        solve();


		// output
		System.out.println(answer);
		br.close();
	}
	
    // solve
	private static void solve() {
		for(int i = 1; i <= N; i++) {
			for(int w = 0; w <= K; w++) {
				if(w < weight[i]) {
					dp[i][w] = dp[i - 1][w];
				} else {
					dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weight[i]] + value[i]);
				}
			}
		}
		
		answer = dp[N][K];
	}
}