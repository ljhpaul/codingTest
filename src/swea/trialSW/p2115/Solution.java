package swea.trialSW.p2115;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int[][] map;
    static int[][] maxMap;
    static int N;   // dfs 최대 깊이
    static int M;
    static int C;
    static int answer;   // 남은 블록 최소치 갱신(최대 폭파)

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            int answer = 0;
        	st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            maxMap = new int[N][N-M+1];

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            // solve
            answer = getMaxBenefit();

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    private static int getMaxBenefit() {
        // 연속된 M개 선택 가능한 모든 행의 열 위치마다 최대이익 계산
        makeMaxMap();

        // 두 일꾼의 선택 조합
        return processCombination();
    }

    private static void makeMaxMap() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N-M; j++) {
                // i, j 위치에서 부분집합 따져보고 최대 이익 계산

            }
        }
    }

    private static void subset(int i, int j, int cnt, int sum, int powSum) {
        if(cnt == M) {
            if(maxMap[i][j-M] < powSum) maxMap[i][j-M] = powSum;
            return;
        }

        // i, j 벌통 포함
        subset(i, j + 1, cnt + 1, sum + map[i][j], powSum + map[i][j] * map[i][j]);
        // i, j 벌통 미포함
        subset(i, j + 1, cnt + 1, sum, powSum);
    }

    private static int processCombination() {

        return 0;
    }
}
/*



 */