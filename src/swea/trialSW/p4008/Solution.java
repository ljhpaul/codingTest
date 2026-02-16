package swea.trialSW.p4008;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int[] nums;
    static int max, min;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	int answer = 0;
        	int N = Integer.parseInt(br.readLine());
        	nums = new int[N];
        	max = Integer.MIN_VALUE;
        	min = Integer.MAX_VALUE;
        	int[] op = new int[4];

        	// input
        	st = new StringTokenizer(br.readLine());
        	for(int i=0; i<4; i++) {
        		op[i] = Integer.parseInt(st.nextToken());
        	}
        	st = new StringTokenizer(br.readLine());
        	for(int i=0; i<N; i++) {
        		nums[i] = Integer.parseInt(st.nextToken());
        	}

            // solve
        	permutation(1, nums[0], op[0], op[1], op[2], op[3]);
        	answer = max - min;

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

	private static void permutation(int idx, int result, int plus, int minus, int mul, int div) {
		// base
		if(idx == nums.length) {
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}
		
		// recursion
		if(plus > 0) permutation(idx + 1, result + nums[idx], plus - 1, minus, mul, div);
		if(minus > 0) permutation(idx + 1, result - nums[idx], plus, minus - 1, mul, div);
		if(mul > 0) permutation(idx + 1, result * nums[idx], plus, minus, mul - 1, div);
		if(div > 0) permutation(idx + 1, result / nums[idx], plus, minus, mul, div - 1);
	}
    
}
/* 08:42 ~ 08:57 */