package swea.trialSW.p1952;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int minCost, daily, monthly_1, monthly_3, annually;
    static int[] plan, cost;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	minCost = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            daily = Integer.parseInt(st.nextToken());
            monthly_1 = Integer.parseInt(st.nextToken());
            monthly_3 = Integer.parseInt(st.nextToken());
            annually = Integer.parseInt(st.nextToken());
            plan = new int[13];
            cost = new int[13];

            // input
            st = new StringTokenizer(br.readLine());
            for(int month = 1; month <= 12; month++) {
            	plan[month] = Integer.parseInt(st.nextToken());
            }
            
            // solve
            solve();

            // answer
            sb.append("#").append(tc).append(" ").append(minCost).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }
    
    // solve
    private static void solve() {
    	// loop
    	for(int month = 1; month <= 12; month++) {
    		int dailyCost = daily * plan[month];
    		cost[month] = cost[month - 1] + Math.min(dailyCost, monthly_1);
    		
    		if(month >= 3) {
    			cost[month] = Math.min(cost[month], cost[month - 3] + monthly_3);
    		}
    	}
    	
    	// annually
    	minCost = Math.min(annually, cost[12]);
    }
}