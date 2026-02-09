package baekjoon.newbie.week01.boj2580;

import java.io.*;
import java.util.*;

public class Main {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[][] grid;
    static Map<int[], Boolean> isFilled;
    static int[] rowSum;
    static int[] columnSum;
    static int[] square3x3Sum;

    // main
    public static void main(String[] args) throws IOException {
        // init
        int answer = 0;
        grid = new int[9][9];
        isFilled = new HashMap<>();
        rowSum = new int[9];
        columnSum = new int[9];
        square3x3Sum = new int[9];

        // input
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(grid[i][j] == 0) {
                    isFilled.put(new int[]{i, j}, false);
                }
                rowSum[j] += grid[i][j];
                columnSum[i] += grid[i][j];
            }
        }

        // get answer
        for(int[] tmp : isFilled.keySet()) {
            int x = tmp[0];
            int y = tmp[1];

            checkRow(x, y);
            checkColumn(x, y);
            checkSquare3x3(x, y);
        }

        // output
        System.out.println(answer);
    }

    private static void checkRow(int x, int y) {
        int sum = 0;
        int cnt = 0;

        for(int i=0; i<9; i++) {

        }


    }

    private static void checkColumn(int x, int y) {

    }

    private static void checkSquare3x3(int x, int y) {

    }

}
/*
# 합 45
- 수평 확인(9)
- 수직 확인(9)
- 3*3 확인(9)

 */