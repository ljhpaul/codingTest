package programmers.java.ch08_Hash;

import java.util.HashMap;

// 할인 행사
// https://school.programmers.co.kr/learn/courses/30/lessons/131127
public class P20 {

    public static void main(String[] args) {
        String[] want = {"banana", "apple", "rice", "pork", "pot"};
        int[] number = {3, 2, 2, 2, 1};
        String[] discount = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};
        // String[] want = {"apple"};
        // int[] number = {10};
        // String[] discount = {"banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"};

        P20 p20 = new P20();
        System.out.println(p20.solution(want, number, discount));
    }

    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        HashMap<String, Integer> tenDaysDiscounts = new HashMap<>();
        int start = 0;
        int end = 9;

        // 처음 10일간의 할인일정 초기화
        for (int i = 0; i < 10; i++) {
            if(tenDaysDiscounts.containsKey(discount[i])) {
                tenDaysDiscounts.put(discount[i], tenDaysDiscounts.get(discount[i]) + 1);
            } else {
                tenDaysDiscounts.put(discount[i], 1);
            }
        }

        // 회원등록 총 일수 계산
        for (int i = 0; i <= discount.length - 10; i++) {
            boolean register = true;
            for (int j = 0; j < want.length; j++) {
                String item = want[j];
                int itemCnt = number[j];
                if(!tenDaysDiscounts.containsKey(item) ||
                    tenDaysDiscounts.get(item) < itemCnt) {
                    register = false;
                    break;
                }
            }

            if(register) {
                answer++;
            }

            if(i == discount.length - 10) break;

            tenDaysDiscounts.put(discount[i], tenDaysDiscounts.get(discount[i]) - 1);
            if(tenDaysDiscounts.containsKey(discount[i + 10])) {
                tenDaysDiscounts.put(discount[i + 10], tenDaysDiscounts.get(discount[i + 10]) + 1);
            } else {
                tenDaysDiscounts.put(discount[i + 10], 1);
            }
        }

        return answer;
    }

}