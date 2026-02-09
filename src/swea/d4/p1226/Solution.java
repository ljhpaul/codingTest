package swea.d4.p1226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
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
            StringBuilder answer = new StringBuilder();
            int N = Integer.parseInt(br.readLine());
            Queue<String> first = new ArrayDeque<>();
            Queue<String> second = new ArrayDeque<>();

            // input
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < (N + 1) / 2; i++) {
                first.add(st.nextToken());
            }
            for(int i = 0; i < N / 2; i++) {
                second.add(st.nextToken());
            }

            // solve
            for (int i = 0; i < N / 2; i++) {
                answer.append(first.poll()).append(" ");
                answer.append(second.poll()).append(" ");
            }
            if(!first.isEmpty()) {
                answer.append(first.poll());
            }

            // answer
            sb.append("#").append(tc).append(" ").append(answer.toString()).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }
}