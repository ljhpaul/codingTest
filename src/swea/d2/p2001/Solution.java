package swea.d2.p2001;

import java.io.*;
import java.util.*;

public class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            String answer = "#" + tc + " ";
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[][] grid = new int[n][n];
            int max = 0;
            int range = n - m;

            // input
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // get answer
            for(int i = 0; i <= range; i++) {
                for(int j = 0; j <= range; j++) {
                    int sum = 0;
                    for(int x = 0; x < m; x++) {
                        for(int y = 0; y < m; y++) {
                            sum += grid[i + x][j + y];
                        }
                    }
                    max = Math.max(sum, max);
                }
            }
            answer += max;

            // output
            System.out.println(answer);
        }
    }

}