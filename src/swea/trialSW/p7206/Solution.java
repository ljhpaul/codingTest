package swea.trialSW.p7206;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static Set<Integer> muls;
    static int[] memo = new int[100_000];

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	Arrays.fill(memo, -1);
            int num = Integer.parseInt(br.readLine());
            
            // solve
            int answer = solve(num);

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }
    
    // 쪼개기
    private static int solve(int num) {
    	if(memo[num] != -1) {
    		return memo[num];
    	}
    	if(String.valueOf(num).length() == 1) {
    		memo[num] = 0;
    		return 0;
    	}
    	
    	// 부분집합으로 쪼갠 결과값 저장
    	muls = new HashSet<>();
    	getMul(String.valueOf(num), 0, 1, 1, false);
    	
    	int max = 0;
    	for(int mul : muls) {
    		max = Math.max(max, 1 + solve(mul));
    	}
    	
    	memo[num] = max;
    	return max;
    }
    
    // 쪼갤 위치 선택 : 부분집합
    private static void getMul(String num, int prevIdx, int currIdx, int mul, boolean cut) {
    	if(currIdx == num.length()) {
    		if(cut) {
        		muls.add(mul * Integer.parseInt(num.substring(prevIdx, currIdx)));
    		}
    		// 아예 쪼개지 않은 경우는 배제
    		return;
    	}
    	
    	// 현재 인덱스에서 쪼개기
    	getMul(num, currIdx, currIdx + 1, mul * Integer.parseInt(num.substring(prevIdx, currIdx)), true);
    	// 쪼개지 않기
    	getMul(num, prevIdx, currIdx + 1, mul, cut);
    }
}