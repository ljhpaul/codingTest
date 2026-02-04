package swea.d2.p2001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 누적합 풀이
public class Solution2 {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[][] grid = new int[n + 1][n + 1];
            int max = Integer.MIN_VALUE;
            int range = n + 1;

            // input
            for(int i = 1; i < range; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 1; j < range; j++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    grid[i][j] = tmp + grid[i - 1][j] + grid[i][j - 1] - grid[i - 1][j - 1];
                }
            }

            // get answer
            for(int i = m; i < range; i++) {
                for(int j = m; j < range; j++) {
                    int sum = grid[i][j] - grid[i - m][j] - grid[i][j - m] + grid[i - m][j - m];
                    max = Math.max(sum, max);
                }
            }
            sb.append(max).append('\n');
        }

        // output
        System.out.print(sb);
        br.close();
    }

}