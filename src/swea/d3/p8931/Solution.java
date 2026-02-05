package swea.d3.p8931;

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
            Stack<Integer> stk = new Stack<>();
            int k = Integer.parseInt(br.readLine());
            int sum = 0;

            // solve
            for(int i = 0; i < k; i++) {
                int num = Integer.parseInt(br.readLine());
                if(num == 0) {
                    stk.pop();
                } else {
                    stk.push(num);
                }
            }
            for(int num : stk) {
                sum += num;
            }

            // answer
            sb.append("#").append(tc).append(" ").append(sum).append("\n");
        }
        // output
        System.out.println(sb);
        br.close();
    }
}