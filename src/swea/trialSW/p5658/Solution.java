package swea.trialSW.p5658;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int N, M, K;
    static char[] arr;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	M = N / 4;	// 슬라이딩 윈도우 최대 인덱스
        	K = Integer.parseInt(st.nextToken());
        	arr = new char[N + M];
        	char[] tmp = br.readLine().toCharArray();
        	for(int i=0; i<arr.length-1; i++) {
        		arr[i] = tmp[i % N];
        	}
        	
            // solve
        	long answer = solve();

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }
    
    // solve
    private static long solve() {
    	// init
    	Set<Long> nums = new HashSet<>();
    	List<Long> list = new ArrayList<>();
    	long tmp = 0;
    	int p1 = 0;
    	int p2 = 0;
    	
    	// 처음 숫자 설정
    	for(; p2<M; p2++) {
    		tmp += convert(arr[p2]) * Math.pow(16, M-p2-1);
    	}
    	nums.add(tmp);
    	
    	// 슬라이딩 윈도우 진행
    	for(int i=1; i<N; i++) {
    		// 앞 숫자 제거
    		tmp -= convert(arr[p1++]) * Math.pow(16, M-1);
    		// 나머지 숫자 16배
    		tmp *= 16;
    		// 뒤 숫자 추가
    		tmp += convert(arr[p2++]);
    		// 집합에 추가
    		nums.add(tmp);
    	}
    	
    	// 집합 리스트로 변경
    	for(long num : nums) list.add(num);
    	Collections.sort(list, Comparator.reverseOrder());
    	
    	// return
    	return list.get(K-1);
    }
    
    // convert
    private static long convert(char c) {
    	if(c >= '0' && c <= '9') {
    		return c - '0';
    	} else {
    		return c - 'A' + 10;
    	}
    }
}