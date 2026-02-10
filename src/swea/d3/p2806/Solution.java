package swea.d3.p2806;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int answer;
    static int N;
    static boolean[] colCheck;
    static boolean[] slashCheck;
    static boolean[] backSlashCheck;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            answer = 0;
            N = Integer.parseInt(br.readLine());
            colCheck = new boolean[N];
            slashCheck = new boolean[2*N - 1];
            backSlashCheck = new boolean[2*N - 1];

            // solve
            dfs(0);

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    // dfs
    private static void dfs(int r) {
        // base case
        if(r == N) {
            answer++;
            return;
        }

        // recursion case
        for (int i = 0; i < N; i++) {
            // 해당 위치가 불가 영역이면 가지치기
            if(!setQueen(r, i)) continue;
            // 가능할 경우 퀸 세팅 후 다음 행 진행
            dfs(r + 1);
            // 불가 영역 원복
            rollbackQueen(r, i);
        }
    }

    // set queen
    private static boolean setQueen(int r, int c) {
        // 퀸을 놓을 수 없는 경우
        if(colCheck[c] || slashCheck[r + c] || backSlashCheck[r - c + N - 1]) return false;

        // 열 불가 처리
        colCheck[c] = true;

        // 대각선(/) 불가 처리
        slashCheck[r + c] = true;

        // 대각선(\) 불가 처리
        backSlashCheck[r - c + N - 1] = true;

        // 퀸 배치 성공
        return true;
    }

    // rollback queen
    private static void rollbackQueen(int r, int c) {
        // 열 불가 처리 복구
        colCheck[c] = false;

        // 대각선(/) 불가 처리 복구
        slashCheck[r + c] = false;

        // 대각선(\) 불가 처리 복구
        backSlashCheck[r - c + N - 1] = false;
    }
}
/*
# 상태
- 결정 : 보드의 크기
- 미결정 : 퀸의 위치(어느 열에 위치시킬 것인지?)
    - 행마다 퀸은 하나씩밖에 위치할 수 없음!
# 선택지
- 행마다 한칸을 선택하여 퀸을 위치
# 제약
- 이미 불가 처리된 열, 행, 대각선에 위치하면 안됨
# 종료 조건
- N개의 퀸을 놓았을 경우
 */