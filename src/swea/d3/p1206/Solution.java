package swea.d3.p1206;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = 10;
        for(int tc = 1; tc <= T; tc++) {
            // init
            String answer = "#" + tc + " ";
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            int cnt = 0;

            // input
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            // get answer
            for(int i=2; i<n-2; i++) {
                int[] tmp = new int[]{arr[i-2], arr[i-1], arr[i+1], arr[i+2]};
                Arrays.sort(tmp);
                int gap = arr[i] - tmp[3];

                // 조망권 확보
                if(gap >= 0) {
                    cnt += gap;
                }
            }
            answer += cnt;

            // output
            System.out.println(answer);
        }
    }
}
/*

### 문제 분석
-
-

### 슈도 코드


 */