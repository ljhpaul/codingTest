package programmers.java.ch06_Stack;

import java.util.ArrayDeque;

// 괄호 회전하기
// https://school.programmers.co.kr/learn/courses/30/lessons/76502
public class P10 {

    public static void main(String[] args) {
        String s = "[](){}";
        s = "}]()[{"	;
        // s = "[)(]"	;
        // s = "}}}"	;


        P10 p10 = new P10();
        System.out.println(p10.solution(s));
    }

    public int solution(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            char[] arr = new char[s.length()];

            // 괄호 문자열 회전
            for (int j = 0; j < s.length(); j++) {
                arr[j] = s.toCharArray()[(i + j) % s.length()];
            }

            // 올바은 괄호 문자열인지 검증
            if(isValid(arr)) {
                answer++;
            }
        }

        return answer;
    }

    public boolean isValid(char[] arr) {
        // 스택 선언
        ArrayDeque<Character> stack = new ArrayDeque<>();

        // 괄호 처리
        for(char c : arr) {
            switch(c) {
                case '[' : {
                    stack.add('[');
                    break;
                }
                case ']' : {
                    if(!stack.isEmpty() && stack.getLast() == '[') {
                        stack.removeLast();
                        break;
                    }
                    return false;
                }
                case '{' : {
                    stack.add('{');
                    break;
                }
                case '}' : {
                    if(!stack.isEmpty() && stack.getLast() == '{') {
                        stack.removeLast();
                        break;
                    }
                    return false;
                }
                case '(' : {
                    stack.add('(');
                    break;
                }
                case ')' : {
                    if(!stack.isEmpty() && stack.getLast() == '(') {
                        stack.removeLast();
                        break;
                    }
                    return false;
                }

            }
        }

        // 스택이 비어있지 않은 경우 올바르지 않은 괄호 문자열
        if(!stack.isEmpty()) {
            return false;
        }

        // 올바른 괄호 문자열
        return true;
    }

}
