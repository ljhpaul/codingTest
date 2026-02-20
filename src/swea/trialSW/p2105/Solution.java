package swea.trialSW.p2105;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	
        	
        	// input
        	
            
            // solve
        	solve();

            // answer
            sb.append("#").append(tc).append(" ").append(maxCnt).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }
    
    
}