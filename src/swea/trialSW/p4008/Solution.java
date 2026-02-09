package swea.trialSW.p4008;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int max;
    static int min;
    static int N;
    static int[] cards;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            int answer = 0;
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            int[] op = new int[4];
            cards = new int[N];

            // input
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 4; i++) {
                op[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                cards[i] = Integer.parseInt(st.nextToken());
            }

            // solve
            dfs(0, cards[0], op[0], op[1], op[2], op[3]);
            answer = max - min;

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    // permutation
    private static void dfs(int idx, int value, int plus, int minus, int mul, int div) {
        // base case
        if(idx == N - 1) {
            max = Math.max(value, max);
            min = Math.min(value, min);
            return;
        }
        // recursion case
        if(plus > 0) dfs(idx + 1, value + cards[idx + 1], plus - 1, minus, mul, div);
        if(minus > 0) dfs(idx + 1, value - cards[idx + 1], plus, minus - 1, mul, div);
        if(mul > 0) dfs(idx + 1, value * cards[idx + 1], plus, minus, mul - 1, div);
        if(div > 0) dfs(idx + 1, value / cards[idx + 1], plus, minus, mul, div - 1);
    }
}
/*
cards = {3, 5, 3, 7, 9}
op = {'+', '+', '-', '/'}

op 순서 고려 배치 -> (N-1)! -> (3<=N<=12)
isSelected로 선택 관리 후 순열 풀이 -> nPn
연산 결과 누적 후 idx == N 이면 max, min 갱신

### 문제 풀이 결과
- 일반 순열을 사용할 경우 최대 12!으로 시간복잡도 터짐
- 연산자의 중복을 고려하여 판단, 각 연산자의 개수를 고려하기
 */