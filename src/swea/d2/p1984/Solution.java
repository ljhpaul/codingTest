package swea.d2.p1984;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
            int min = 10000;
            int max = 0;
            int sum = 0;

            // get answer
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<10; i++) {
                int num = Integer.parseInt(st.nextToken());
                min = Math.min(min, num);
                max = Math.max(max, num);
                sum += num;
            }
            answer += (int) Math.round((sum - max - min) * 10 / 80.0);

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