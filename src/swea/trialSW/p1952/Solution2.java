package swea.trialSW.p1952;

import java.io.*;
import java.util.*;

class Solution2 {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[] costs = new int[4];
    static int[] plan = new int[12];
    static int[] dp = new int[12];
    static int answer;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            answer = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 4; i++) {
                costs[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 12; i++) {
                plan[i] = Integer.parseInt(st.nextToken());
            }
            
            // solve
            dfs(0, 0);

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    // dfs
    private static void dfs(int month, int sum) {
        // base case
        if(month >= 12) {
            answer = Math.min(sum, costs[3]);
            return;
        }

        // pruning
        if(sum > answer) return;
        if(sum >= costs[3]) {
            answer = costs[3];
            return;
        }
        if(plan[month] == 0) {
            dfs(month + 1, sum);
            return;
        }

        // recursion case
        int curr = Math.min(plan[month] * costs[0], costs[1]);
        dfs(month + 1, sum + curr); // 3달 이용 X
        dfs(month + 3, sum + costs[2]); // 3달 이용 O
    }
}
/*
# 상태
- 결정: 연간 이용 계획
- 미결정: 각 달에 어떤 이용권을 사용하는지
# 선택지
- 1일 / 1달 / 3달 / 1년
# 제약
- 해당 월에 월간 이용권 금액을 넘을 경우 가지치기
- 3달까지 누적 중 3달 이용권 금액을 넘을 경우 가지치기
- 전체 누적 중 연간 이용권 금액을 넘을 경우 가지치기
# 종료 조건
- 12월을 넘어섰을 경우
 */