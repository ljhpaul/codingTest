package swea.trialSW.p1952;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int answer;
    static int[] plan, cost;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	plan = new int[12];		// 연간 이용 계획(1~12월)
        	cost = new int[4];		// 일일, 1달, 3달, 연간
        	
        	//input
        	st = new StringTokenizer(br.readLine());
        	for(int i=0; i<4; i++) {
        		cost[i] = Integer.parseInt(st.nextToken());
        	}
        	st = new StringTokenizer(br.readLine());
        	for(int month=0; month<12; month++) {
        		plan[month] = Integer.parseInt(st.nextToken());
        	}
        	
        	// solve
        	answer = cost[3];	// 합계를 연간권과 비교
        	dfs(0, 0);
        	
            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }
    
    // dfs
    private static void dfs(int month, int sum) {
    	if(sum >= answer) return;	// 합계가 최솟값보다 클 경우 가지치기
    	if(month >= 12) {
    		// 합계와 연간권 비교
    		answer = Math.min(answer, sum);
    		return;
    	}
    	
    	// 해당 달에 이용 계획 없으면 다음 달로 넘어가기
    	if(plan[month] == 0) {
    		dfs(month + 1, sum);
    		return;
    	}
    	
    	// 일일권 vs 1달권
    	int monthly = Math.min(cost[0]*plan[month], cost[1]);
    	dfs(month + 1, sum + monthly);
    	// 3달권
    	dfs(month + 3, sum + cost[2]);
    }
}