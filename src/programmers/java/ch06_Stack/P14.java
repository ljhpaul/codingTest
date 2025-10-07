package programmers.java.ch06_Stack;

import java.util.ArrayDeque;

// 표 편집
// https://school.programmers.co.kr/learn/courses/30/lessons/81303
public class P14 {

    public static void main(String[] args) {
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
        // String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
        P14 p14 = new P14();
        System.out.println(p14.solution(n, k, cmd));
    }

    public String solution(int n, int k, String[] cmd) {
        String answer = "";

        // 변수 선언
        int[] prev = new int[n];
        int[] next = new int[n];
        boolean[] deleted = new boolean[n];
        ArrayDeque<Integer> removed = new ArrayDeque<>();
        int idx = k;

        // prev, next 초기화
        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        next[n - 1] = -1;

        // 명령어 실행
        for(String strCmd : cmd) {
            String[] strCmdArr = strCmd.split(" ");

            // System.out.print(Arrays.toString(strCmdArr) + " -> ");

            // 명령어 첫 글자로 분기
            switch (strCmdArr[0]) {
                case "U" : {
                    int x = Integer.parseInt(strCmdArr[1]);
                    for (int i = 0; i < x; i++) {
                        idx = prev[idx];
                    }
                    break;
                } case "D" : {
                    int x = Integer.parseInt(strCmdArr[1]);
                    for (int i = 0; i < x; i++) {
                        idx = next[idx];
                    }
                    break;
                } case "C" : {
                    removed.addLast(idx);
                    deleted[idx] = true;

                    if(prev[idx] != -1) next[prev[idx]] = next[idx];
                    if(next[idx] != -1) prev[next[idx]] = prev[idx];

                    idx = next[idx] == -1 ? prev[idx] : next[idx];

                    break;
                } case "Z" : {
                    int undoIdx = removed.pollLast();
                    deleted[undoIdx] = false;

                    if(prev[undoIdx] != -1) next[prev[undoIdx]] = undoIdx;
                    if(next[undoIdx] != -1) prev[next[undoIdx]] = undoIdx;

                    break;
                }
            }

            // System.out.println(Arrays.toString(chart) + " | (" + idx + ")");
        }

        // StringBuilder로 문자열 생성
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(deleted[i] ? "X" : "O");
        }
        answer = sb.toString();

        return answer;
    }

}