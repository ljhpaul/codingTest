package swea.trialSW.p4012;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int answer, N, R;
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
            R = N/2;
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
            solve(1, 1);

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        // output
        System.out.println(sb);
        br.close();
    }

    // solve : N C R
    private static void solve(int cnt, int start) {
        // base
        if(cnt == R) {
            int sumA = 0;
            int sumB = 0;
            // R C 2
            for(int i=0; i<N-1; i++) {
                for(int j=i+1; j<N; j++) {
                    if(isFoodA[i] && isFoodA[j]) {
                        sumA += table[i][j] + table[j][i];
                    } else if(!isFoodA[i] && !isFoodA[j]) {
                        sumB += table[i][j] + table[j][i];
                    }
                }
            }
            answer = Math.min(answer, Math.abs(sumA - sumB));
            return;
        }

        // recursion
        for(int i=start; i<N; i++) {
            isFoodA[i] = true;
            solve(cnt+1, i+1);
            isFoodA[i] = false;
        }
    }

}