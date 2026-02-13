package swea.d5.p6782;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static long answer;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            answer = 0;
            long N = Long.parseLong(br.readLine());
            
            // solve
            dfs(N, 0);

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    // dfs
    private static void dfs(long N, long cnt) {
        // base
        if(N == 1) {
            answer = cnt + 1;
            return;
        } else if(N == 2) {
            answer = cnt;
            return;
        }

        // recursion
        double sqrtN = Math.sqrt(N);
        if(sqrtN == Math.floor(sqrtN)) {
            dfs((long) sqrtN, cnt + 1);  // 현재 값이 제곱수일 때 제곱근
        } else {
            long nextN = (long) (Math.ceil(sqrtN) * Math.ceil(sqrtN));
            dfs(nextN, cnt + nextN - N);    // 다음 제곱수까지 점프
        }
    }
}