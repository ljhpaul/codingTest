package swea.trialSW.p4014;

import java.io.*;
import java.util.*;

class Solution2 {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int answer, N, X;
    static int[][] map;
    
    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            answer = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            map = new int[N][N];

            // input
            for(int r=0; r<N; r++) {
            	st = new StringTokenizer(br.readLine());
            	for(int c=0; c<N; c++) {
            		map[r][c] = Integer.parseInt(st.nextToken());
            	}
            }
            
            // solve
            solve();

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }
    
    // solve
    private static void solve() {
    	// 수평방향 체크
    	horizonal:
    	for(int r=0; r<N; r++) {
    		int[] flat = new int[N]; // 연속된 평평 길이
    		int stuff = -1;	// 이전 절벽의 높이
    		
    		// 처음 위치 관리
    		flat[0] = 1;
    		
    		for(int c=1; c<N; c++) {
    			int now = map[r][c];
    			int prev = map[r][c-1];
    			
    			// 1. 평평할 경우
    			if(now == prev) {
        			flat[c] = flat[c-1] + 1;
        			
    				// 이전 절벽이 남아있으며 연속 길이가 경사로 길이에 도달했을 경우
        			if(stuff != -1 && flat[c] == X) {
        				flat[c] = 0;	// 경사로 설치
        				stuff = -1;
        			}
    			}
    			// 2. 평평하지 않을 경우
    			else {
    				flat[c] = 1;
    				if(Math.abs(now - prev) > 1) continue horizonal; // 높이 차가 1이 아니면 실패
    				
    				// 현재 높이가 직전 높이보다 높아졌을 경우
        			if(now > prev) {
        				// 직전까지의 연속 길이가 경사로 길이보다 작으면 실패
        				if(flat[c-1] < X) continue horizonal;
        				stuff = -1;
        			}
        			// 현재 높이가 직전 높이보다 낮아졌을 경우
        			else if(now < prev) {
        				if(stuff != -1 && flat[c-1] < X) continue horizonal;
        				stuff = prev;
        			}
    			}
    		}
    		
    		// 절벽이 남아있을 때, 마지막 경사로 설치 여부 확인
    		if(stuff != -1) continue horizonal;
    		
    		// 경사로 건설 성공
    		answer++;
    	}
    	
    	// 수직방향 체크
    	vertical:
    	for(int c=0; c<N; c++) {
    		int[] flat = new int[N]; // 연속된 평평 길이
    		int stuff = -1;	// 이전 절벽의 높이
    		
    		// 처음 위치 관리
    		flat[0] = 1;
    		
    		for(int r=1; r<N; r++) {
    			int now = map[r][c];
    			int prev = map[r-1][c];
    			
    			// 1. 평평할 경우
    			if(now == prev) {
        			flat[r] = flat[r-1] + 1;
        			
    				// 이전 절벽이 남아있으며 연속 길이가 경사로 길이에 도달했을 경우
        			if(stuff != -1 && flat[r] == X) {
        				flat[r] = 0;	// 경사로 설치
        				stuff = -1;
        			}
    			}
    			// 2. 평평하지 않을 경우
    			else {
    				flat[r] = 1;
    				if(Math.abs(now - prev) > 1) continue vertical; // 높이 차가 1이 아니면 실패
    				
    				// 현재 높이가 직전 높이보다 높아졌을 경우
        			if(now > prev) {
        				// 직전까지의 연속 길이가 경사로 길이보다 작으면 실패
        				if(flat[r-1] < X) continue vertical;
        				stuff = -1;
        			}
        			// 현재 높이가 직전 높이보다 낮아졌을 경우
        			else if(now < prev) {
        				if(stuff != -1 && flat[r-1] < X) continue vertical;
        				stuff = prev;
        			}
    			}
    		}
    		
    		// 절벽이 남아있을 때, 마지막 경사로 설치 여부 확인
    		if(stuff != -1) continue vertical;
    		
    		// 경사로 건설 성공
    		answer++;
    	}
    }
}