package swea.d3.p1209;

import java.io.*;
import java.util.*;

public class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        for(int tc = 1; tc <= 10; tc++) {
            // init
            String testCaseNum = br.readLine();
            String answer = "#" + testCaseNum + " ";
            int[][] grid = new int[100][100];
            int max = 0;
            int ascCross = 0;
            int descCross = 0;

            // input & get answer
            for(int i = 0; i < 100; i++) {
                int horizonSum = 0;
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 100; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                    horizonSum += grid[i][j];
                    if(i == j) ascCross += grid[i][j];
                    if(i + j == 99) descCross += grid[i][j];
                }
                max = Math.max(max, horizonSum);
            }

            for(int j = 0; j < 100; j++) {
                int verticalSum = 0;
                for(int i = 0; i < 100; i++) {
                    verticalSum += grid[i][j];
                }
                max = Math.max(max, verticalSum);
            }

            max = Math.max(max, ascCross);
            max = Math.max(max, descCross);

            answer += max;

            // output
            System.out.println(answer);
        }
    }

}