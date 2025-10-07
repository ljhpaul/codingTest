package programmers.java.ch05_Array;

import java.util.*;

// 행렬의 곱셈
// https://school.programmers.co.kr/learn/courses/30/lessons/12949
public class P05 {

    public static void main(String[] args) {
        int[][] arr1 = {{1, 4}, {3, 2}, {4, 1}};
        int[][] arr2 = {{3, 3}, {3, 3}};

        // int[][] arr1 = {{2, 3, 2}, {4, 2, 4}, {3, 1, 4}};
        // int[][] arr2 = {{5, 4, 3}, {2, 4, 1}, {3, 1, 1}};

        P05 p05 = new P05();
        System.out.println(Arrays.deepToString(p05.solution(arr1, arr2)));
    }

    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2[0].length; j++) {
                for (int k = 0; k < arr1[0].length; k++) {
                    // System.out.print("" + arr1[i][k] + " X " + arr2[k][j] + ", ");
                    answer[i][j] += arr1[i][k] * arr2[k][j];
                }
                System.out.println();
            }
        }
        
        return answer;
    }

}
