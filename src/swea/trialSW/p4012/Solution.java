package swea.trialSW.p4012;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int answer, N;
    static int[][] table;
    static boolean[] isFoodA;
    
    
    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	answer = Integer.MAX_VALUE;
        	N = Integer.parseInt(br.readLine());
        	table = new int[N][N];
        	isFoodA = new boolean[N];
        	
        	// input
        	for(int i=0; i<N; i++) {
        		st = new StringTokenizer(br.readLine());
        		for(int j=0; j<N; j++) {
        			table[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
            // solve
        	isFoodA[0] = true;
        	combination(1, 1);
        	
            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        // output
        System.out.println(sb);
        br.close();
    }

    // N C N/2
    private static void combination(int cnt, int start) {
    	// pruning
    	if(answer == 0) return;
    	
    	// base
    	if(cnt == N/2) {
    		answer = Math.min(answer, getTaste());
    		return;
    	}
    	
    	// recursion
    	for(int i=start; i<N; i++) {
    		isFoodA[i] = true;
    		combination(cnt + 1, i + 1);
    		isFoodA[i] = false;
    	}
    }
    
    // N/2 C 2
    private static int getTaste() {
    	int tasteA = 0;
    	int tasteB = 0;
    	
    	// get taste A and B
    	for(int i=0; i<N-1; i++) {
    		for(int j=i+1; j<N; j++) {
    			if(isFoodA[i] && isFoodA[j]) {
    				// A
    				tasteA += table[i][j];
    				tasteA += table[j][i];
    			} else if(!isFoodA[i] && !isFoodA[j]) {
    				// B
    				tasteB += table[i][j];
    				tasteB += table[j][i];
    			}
    		}
    	}
    	
    	return Math.abs(tasteA - tasteB);
    }
}
/* 08:58 ~ 09:22 */