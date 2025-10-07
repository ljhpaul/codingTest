package programmers.java.ch06_Stack;

import java.util.ArrayDeque;

// 짝지어 제거하기
// https://school.programmers.co.kr/learn/courses/30/lessons/12973
public class P11 {

    public static void main(String[] args) {
        String s = "baabaa";
        s = "cdcd"	;

        P11 p11 = new P11();
        System.out.println(p11.solution(s));
    }

    public int solution(String s) {
        int answer = 0;
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for(char c : s.toCharArray()) {
            // 스택에 마지막 값과 같을 경우 해당 쌍을 제거
            if(!stack.isEmpty() && stack.getLast() == c) {
                stack.pollLast();
                continue;
            }

            // 비어있거나 다를 경우 새 값을 스택에 저장
            stack.addLast(c);
        }

        // 스택이 비어있으면 제거 성공
        if(stack.isEmpty()) {
            answer++;
        }

        return answer;
    }

}