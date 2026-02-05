package swea.d4.p1218;

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
        for(int tc = 1; tc <= 10; tc++) {
            // init
            int T = Integer.parseInt(br.readLine());
            Stack<Character> stk = new Stack<>();
            String str = br.readLine();
            int answer = 1;

            // solve
            outer:
            for(char c : str.toCharArray()) {
                switch (c) {
                    case '(':
                    case '{':
                    case '[':
                    case '<':
                        stk.push(c);
                        break;
                    case ')':
                        if(stk.isEmpty() || stk.peek() != '(') {
                            answer = 0;
                            break outer;
                        }
                        stk.pop();
                        break;
                    case '}':
                        if(stk.isEmpty() || stk.peek() != '{') {
                            answer = 0;
                            break outer;
                        }
                        stk.pop();
                        break;
                    case ']':
                        if(stk.isEmpty() || stk.peek() != '[') {
                            answer = 0;
                            break outer;
                        }
                        stk.pop();
                        break;
                    case '>':
                        if(stk.isEmpty() || stk.peek() != '<') {
                            answer = 0;
                            break outer;
                        }
                        stk.pop();
                        break;
                }
            }

            // answer
            sb.append("#").append(T).append(" ").append(answer).append("\n");
        }
        // output
        System.out.println(sb);
        br.close();
    }

}
