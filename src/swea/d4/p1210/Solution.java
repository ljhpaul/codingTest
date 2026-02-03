package swea.d4.p1210;

import java.io.*;
import java.util.*;

class Solution  {
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
            List<Integer> starts = new ArrayList<>();
            int[][] grid = new int[100][100];

            // input
            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                    if(i == 0 && grid[i][j] == 1) {
                        starts.add(j);
                    }
                }
            }

            // get answer
            answer += getLadderStart(grid, starts);

            // output
            System.out.println(answer);
        }
    }

    static int getLadderStart(int[][] grid, List<Integer> starts) {
        // 시작점 순회
        for(int start : starts) {
            int r = 0;
            int c = start;

            while(r < 100) {
                // 도착점 확인
                if(grid[r][c] == 2) {
                    return start;
                }

                // 좌-우 확인
                if(c - 1 >= 0 && grid[r][c - 1] == 1) {
                    // 좌측 이동
                    while(c - 1 >= 0 && grid[r][c - 1] == 1) {
                        c--;
                    }
                } else if(c + 1 < 100 && grid[r][c + 1] == 1) {
                    // 우측 이동
                    while(c + 1 < 100 && grid[r][c + 1] == 1) {
                        c++;
                    }
                }

                // 하향 이동
                r++;
            }
        }
        return -1;
    }

}
