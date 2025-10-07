package programmers.java.ch05_Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 두 개 뽑아서 더하기
// https://school.programmers.co.kr/learn/courses/30/lessons/68644
public class P03 {

    public static void main(String[] args) {
        int[] numbers = {2, 1, 3, 4, 1};
        int[] numbers2 = {5, 0, 2, 7};

        P03 p03 = new P03();
        System.out.println(Arrays.toString(p03.solution(numbers)));
        System.out.println(Arrays.toString(p03.solution(numbers2)));
    }

    public int[] solution(int[] numbers) {
        int[] answer = {};
        Set<Integer> numSet = new HashSet<>();

        for(int i = 0; i < numbers.length-1; i++) {
            for (int j = i+1; j < numbers.length; j++) {
                numSet.add(numbers[i] + numbers[j]);
            }
        }

        Integer[] numArr = numSet.toArray(new Integer[0]);
        Arrays.sort(numArr);

        answer = new int[numSet.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = numArr[i];
        }

        return answer;
    }
    
    // stream 사용
    public int[] solution2(int[] numbers) {
        int[] answer;
        Set<Integer> numSet = new HashSet<>();

        for(int i=0; i<numbers.length-1; i++) {
            for (int j = i+1; j < numbers.length; j++) {
                numSet.add(numbers[i] + numbers[j]);
            }
        }

        // System.out.println(numSet);
        answer = numSet.stream().sorted().mapToInt(value -> value).toArray();
        return answer;
    }

}
