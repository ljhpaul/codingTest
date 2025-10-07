package programmers.java.ch06_Stack;

import java.util.ArrayDeque;
import java.util.Arrays;

// 짝지어 제거하기
// https://school.programmers.co.kr/learn/courses/30/lessons/12973
public class P12 {

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 3};

        P12 p12 = new P12();
        System.out.println(Arrays.toString(p12.solution(prices)));
    }

    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        ArrayDeque<int[]> dq = new ArrayDeque<>();

        for (int i = 0; i < prices.length; i++) {
            answer[i] = prices.length - (i + 1);
        }

        for (int i = 0; i < prices.length; i++) {
            while (!dq.isEmpty() && dq.getLast()[1] > prices[i]) {
                answer[dq.getLast()[0]] = i - dq.pollLast()[0];
            }
            dq.addLast(new int[] {i, prices[i]});
        }

        return answer;
    }

}