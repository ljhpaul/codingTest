package swea.trialSW.p2112;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int answer, D, W, K;
    static int[][] film;
    static boolean[] isMedicineA, isMedicineB;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            answer = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            film = new int[D][W];
            isMedicineA = new boolean[D];
            isMedicineB = new boolean[D];

            // input
            for(int r = 0; r < D; r++) {
            	st = new StringTokenizer(br.readLine());
            	for(int c = 0; c < W; c++) {
            		film[r][c] = Integer.parseInt(st.nextToken());
            	}
            }
            
            // solve
            if(K == 1) answer = 0;
            else setMedicine(0, 0);

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    // setMedicine(idx, mediCnt) : 부분집합 DFS (3^D)
	private static void setMedicine(int idx, int mediCnt) {
		// pruning
		if(mediCnt >= answer) return;
		
		// query
		if(performanceTest()) {
			answer = Math.min(answer, mediCnt);
			return;
		}
		
		// base
		if(idx == D) return;
		
		// recursion
		// 1. 현재 막 약품 처리 안 함
		setMedicine(idx + 1, mediCnt);
		
		// 2. 약품 A 처리
		isMedicineA[idx] = true;
		setMedicine(idx + 1, mediCnt + 1);
		isMedicineA[idx] = false;
		
		// 3. 약품 B 처리
		isMedicineB[idx] = true;
		setMedicine(idx + 1, mediCnt + 1);
		isMedicineB[idx] = false;
	}

	// performanceTest : 성능 검사
	private static boolean performanceTest() {
		// 모든 열 순회
		for(int c = 0; c < W; c++) {
			int seq = 1;
			boolean passed = false;
			
			// 연속 여부 확인
			for(int r = 1 ; r < D; r++) {
				int prev = isMedicineA[r - 1] ? 0 : isMedicineB[r - 1] ? 1 : film[r - 1][c];
				int curr = isMedicineA[r] ? 0 : isMedicineB[r] ? 1 : film[r][c];
				
				if(curr == prev) seq++;
				else seq = 1;
				
				// 해당 열 성능 통과
				if(seq >= K) {
					passed = true;
					break;
				}
			}
			
			// 실패시 조기 종료
			if(!passed) return false;
		}
		
		// 모든 열이 성능 검사에 통과했는지 반환
		return true;
	}
}