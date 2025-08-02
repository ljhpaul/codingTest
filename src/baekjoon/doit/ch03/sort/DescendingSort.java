package baekjoon.doit.ch03.sort;

import java.util.*;

public class DescendingSort {
    public static void main(String[] args) {
//        Integer[] A = {5,3,2,4,1};
//        Arrays.sort(A, Collections.reverseOrder());
        int[] A = {5,3,2,4,1};
        negate(A);
        Arrays.sort(A);
        negate(A);
        System.out.println(Arrays.toString(A));
    }
    public static void negate(int[] arr) {
        for(int i=0; i<arr.length; i++) {
            arr[i] = -arr[i];
        }
    }
}
