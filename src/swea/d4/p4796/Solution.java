package swea.d4.p4796;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            // init
            int answer = 0;
            int N = sc.nextInt();
            int[] h = new int[N];
            int[] asc = new int[N];
            int[] desc = new int[N];
            boolean[] isDesc = new boolean[N];

            // solve
            h[0] = sc.nextInt();
            for(int i=1; i<N; i++) {
                h[i] = sc.nextInt();
                // 증가
                if(h[i] > h[i-1]) {
                    asc[i] = asc[i-1] + 1;
                    desc[i] = desc[i-1];
                    if(i>=2 && isDesc[i-1]) {   // 감소 -> 증가 꺾임
                        answer += asc[i-1] * desc[i-1];
                        asc[i] = 1;
                    }
                }
                // 감소
                else {
                    asc[i] = asc[i-1];
                    desc[i] = desc[i-1] + 1;
                    isDesc[i] = true;
                    if(i>=2 && !isDesc[i-1]) {   // 증가 -> 감소 꺾임
                        desc[i] = 1;
                    }
                }
            }
            if(isDesc[N-1]) {
                answer += asc[N-1] * desc[N-1];
            }

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        // output
        System.out.println(sb);
        br.close();
    }

}
