package swea.d1.p2068;

import java.io.*;
import java.util.*;

class Solution {
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
            int max = 0;

            // get answer
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<10; i++) {
                max = Math.max(max, Integer.parseInt(st.nextToken()));
            }
            answer += max;

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