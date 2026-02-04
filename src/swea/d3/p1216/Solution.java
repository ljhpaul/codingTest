package swea.d3.p1216;

import java.io.*;
import java.util.*;

public class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // main
    public static void main(String[] args) throws IOException {
        // test case
        for(int tc = 1; tc <= 10; tc++) {
            // init
            int T = Integer.parseInt(br.readLine());
            StringBuilder answer = new StringBuilder().append("#").append(T).append(" ");
            String str = br.readLine();
            String line = br.readLine();
            int palindromeCnt = 0;

            // get answer
            // for(int i = 0; i < line.length() - str.length() + 1; i++) {
            //     // 0 1 2 3 4(5), 0 1 2(3)
            //     int matchCnt = 0;
            //     for(int j = 0; j < str.length(); j++) {
            //         if(line.charAt(i + j) != str.charAt(j)) break;
            //         matchCnt++;
            //     }
            //     if(matchCnt == str.length()) palindromeCnt++;
            // }
            // answer.append(palindromeCnt);

            while(line.contains(str)) {
                line = line.substring(line.indexOf(str) + 1);
                palindromeCnt++;
            }
            answer.append(palindromeCnt);

            // output
            System.out.println(answer);
        }
    }

}