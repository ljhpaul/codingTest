package swea.d2.p1989;

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
            StringBuilder answer = new StringBuilder().append("#").append(tc).append(" ");
            String str = br.readLine();
            int find = 1;

            // get answer
            for(int i = 0; i < str.length() / 2; i++) {
                if(str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                    find = 0;
                    break;
                }
            }
            answer.append(find);

            // output
            System.out.println(answer.toString());
        }
    }

}