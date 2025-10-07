package programmers.java.ch05_Array;

import java.util.*;

// 실패율
// https://school.programmers.co.kr/learn/courses/30/lessons/42889
public class P06 {

    public static void main(String[] args) {
        // int N = 5;
        // int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
        int N = 4;
        int[] stages = {4, 4, 4, 4, 4};

        P06 p06 = new P06();
        System.out.println(Arrays.toString(p06.solution(N, stages)));
    }

    public int[] solution(int N, int[] stages) {
        int[] answer = {};

        // 클리어 배열 계산
        int[] clear = new int[N+2];
        for(int stage : stages) {
            clear[stage]++;
        }

        // 실패율 계산
        double participate = stages.length;
        HashMap<Integer, Double> failed = new HashMap<>();
        for (int i = 1; i < N+1; i++) {
            if(clear[i] == 0) {
                failed.put(i, 0.0);
            } else {
                failed.put(i, clear[i] / participate);
                participate -= clear[i];
            }
        }

        // 실패율 내림차순 정렬
        answer = failed.entrySet().stream()
            .sorted((o1, o2) ->
                o1.getValue().equals(o2.getValue()) ?
                    Integer.compare(o1.getKey(), o2.getKey()) :
                    Double.compare(o2.getValue(), o1.getValue())
                )
            .mapToInt(HashMap.Entry::getKey)
            .toArray();

        return answer;
    }

}
