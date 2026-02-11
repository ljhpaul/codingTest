package swea.d4.p1486;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, B;
    static List<Integer> S;
    static int answer;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            answer = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            S = new ArrayList<>();

            // input
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                S.add(Integer.parseInt(st.nextToken()));
            }

            // solve
            S.sort(Comparator.reverseOrder());
            dfs(0, 0);
            if(answer == Integer.MAX_VALUE) {
                answer = 0;
            } else {
                answer -= B;
            }

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        // output
        System.out.println(sb);
        br.close();
    }

    // subSet
    private static void dfs(int idx, int sum) {
        // base
        if(sum >= B) {
            answer = Math.min(sum, answer);
            return;
        } else if(idx == N || sum >= answer) {
            return;
        }

        // recursion
        dfs(idx + 1, sum + S.get(idx));
        dfs(idx + 1, sum);
    }

}