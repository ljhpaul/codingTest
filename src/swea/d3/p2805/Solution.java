package swea.d3.p2805;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            int answer = 0;
            int N = Integer.parseInt(br.readLine());

            // solve
            for(int i=0; i<=N/2; i++) {
                char[] nums = br.readLine().toCharArray();
                answer += nums[N/2] - '0';
                for(int j=1; j<=i; j++) {
                    answer += nums[N/2 + j] - '0';
                    answer += nums[N/2 - j] - '0';
                }
            }

            for(int i=N/2+1; i<N; i++) {
                char[] nums = br.readLine().toCharArray();
                answer += nums[N/2] - '0';
                for(int j=1; j<N-i; j++) {
                    answer += nums[N/2 + j] - '0';
                    answer += nums[N/2 - j] - '0';
                }
            }

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

}