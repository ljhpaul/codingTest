package swea.d4.p5432;

import java.io.*;
import java.util.*;

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
            Stack<Character> stk = new Stack<>();
            String str = br.readLine();
            int sum = 0;
            boolean prevOpen = false;

            // solve
            for(char c : str.toCharArray()) {
                switch (c) {
                    case '(':
                        stk.push(c);
                        prevOpen = true;
                        break;
                    case ')':
                        stk.pop();
                        if(prevOpen) sum += stk.size();
                        else sum++;
                        prevOpen = false;
                        break;
                }
            }

            // answer
            sb.append("#").append(tc).append(" ").append(sum).append("\n");
        }
        // output
        System.out.println(sb);
        br.close();
    }

}
