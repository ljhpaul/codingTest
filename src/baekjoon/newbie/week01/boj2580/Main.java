package baekjoon.newbie.week01.boj2580;

import java.io.*;
import java.util.*;

public class Main {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[][] grid;
    static List<int[]> blanks;
    static boolean[][] rowSelected;
    static boolean[][] columnSelected;
    static boolean[][] square3x3Selected;

    // main
    public static void main(String[] args) throws IOException {
        // init
        grid = new int[9][9];
        blanks = new ArrayList<>();
        rowSelected = new boolean[9][9 + 1];        // 행 기준 : 1~9 까지 사용 여부
        columnSelected = new boolean[9][9 + 1];     // 열 기준 : 1~9 까지 사용 여부
        square3x3Selected = new boolean[9][9 + 1];  // 3x3 기준 : 1~9 까지 사용 여부

        // input
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                int num = Integer.parseInt(st.nextToken());
                grid[i][j] = num;

                if(num == 0) {
                    blanks.add(new int[]{i, j});
                } else {
                    rowSelected[i][num] = true;
                    columnSelected[j][num] = true;
                    square3x3Selected[getSquareIndex(i, j)][num] = true;
                }
            }
        }

        // solve
        dfs(0);

        // output
        output();
    }

    private static int getSquareIndex(int r, int c) {
        int index = 0;
        if(r < 3) {
            if(c < 3) index = 0;
            else if(c < 6) index = 1;
            else if(c < 9) index = 2;
        } else if(r < 6) {
            if(c < 3) index = 3;
            else if(c < 6) index = 4;
            else if(c < 9) index = 5;
        } else {
            if(c < 3) index = 6;
            else if(c < 6) index = 7;
            else if(c < 9) index = 8;
        }
        return index;
    }

    private static void dfs(int idx) {
        // base case
        if(idx == blanks.size()) {
            return;
        }

        // recursion case
        for(int i = 1; i <= 9; i++) {
            int r = blanks.get(idx)[0];
            int c = blanks.get(idx)[1];
            if(checkDuplication(r, c, i)) continue;  // 중복 검사
            grid[r][c] = i;
            dfs(idx + 1);
            if(checkComplete(r, c, i)) break;
        }
    }

    private static boolean checkDuplication(int r, int c, int num) {
        // 행 중복 확인
        if(rowSelected[r][num]) return true;

        // 열 중복 확인
        if(columnSelected[c][num]) return true;

        // 3x3 중복 확인
        if(square3x3Selected[getSquareIndex(r, c)][num]) return true;

        // 사용되지 않은 수 확인됨
        return false;
    }

    private static boolean checkComplete(int r, int c, int num) {
        // 행 유일한 빈칸인지 확인
        int rCnt = 0;
        for(int i = 1; i <= 9; i++) {
            if(i == num) continue;
            if(rowSelected[r][i]) rCnt++;
        }
        if(rCnt == 8) return true;

        // 열 유일한 빈칸인지 확인
        int cCnt = 0;
        for(int i = 1; i <= 9; i++) {
            if(i == num) continue;
            if(rowSelected[c][i]) cCnt++;
        }
        if(cCnt == 8) return true;

        // 3x3 유일한 빈칸인지 확인
        int sqCnt = 0;
        for(int i = 1; i <= 9; i++) {
            if(i == num) continue;
            if(rowSelected[getSquareIndex(r, c)][i]) sqCnt++;
        }
        if(sqCnt == 8) return true;

        // 사용되지 않은 수 확인됨
        return false;
    }

    private static void output() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }


}
/*
# 상태
- 결정 :
- 미정 :
# 선택지
-
# 제약
-
# 종료 조건
-
 */