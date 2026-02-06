package swea.d5.p3421;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int answer;
    static int N;
    static int L;
    static int[] scores;
    static int[] calories;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            answer = 0;
        	st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            scores = new int[N];
            calories = new int[N];

            // input
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                scores[i] = Integer.parseInt(st.nextToken());
                calories[i] = Integer.parseInt(st.nextToken());
            }
            
            // solve
            subSet(0, 0, 0);

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    // generateSubSet : 부분집합 생성
    private static void subSet(int idx, int scoreSum, int calorySum) {
        // base case
        if(calorySum > L) return;
        answer = Math.max(scoreSum, answer);    // scoreSum 갱신
        if(idx == N) return;

        // recursion case
        subSet(idx + 1, scoreSum + scores[idx], calorySum + calories[idx]);
        subSet(idx + 1, scoreSum, calorySum);
    }
}