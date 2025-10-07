package programmers.java.ch07_Queue;

import java.util.*;
import java.io.*;

// 카드 뭉치
// https://school.programmers.co.kr/learn/courses/30/lessons/159994
public class P17 {

    public static void main(String[] args) {
        // String[] cards1 = {"i", "drink", "water"};
        String[] cards1 = {"i", "water", "drink"};
        String[] cards2 = {"want", "to"};
        String[] goal = {"i", "want", "to", "drink", "water"};

        P17 p17 = new P17();
        System.out.println(p17.solution(cards1, cards2, goal));
    }

    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "No";

        int now = 0;
        int idx1 = 0;
        int idx2 = 0;

        while(now < goal.length) {
            // System.out.println(goal[now]);
            if(idx1 < cards1.length && cards1[idx1].equals(goal[now])) {
                idx1++;
                now++;
            } else if(idx2 < cards2.length && cards2[idx2].equals(goal[now])) {
                idx2++;
                now++;
            } else {
                break;
            }
        }

        if(now == goal.length) {
            answer = "Yes";
        }

        return answer;
    }

}