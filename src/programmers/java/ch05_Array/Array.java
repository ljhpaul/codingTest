package programmers.java.ch05_Array;

import java.util.Arrays;

public class Array {

    public static void main(String[] args) {
        int[] arr1 = {0, 0, 0, 0, 0, 0};
        int[] arr2 = new int[6];
        int[][] arr3 = {{1, 2, 3}, {4, 5, 6}};

        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(arr3[1][1]);
        System.out.println(Arrays.deepToString(arr3));
    }

}
