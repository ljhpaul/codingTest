package swea.trialSW.p2117;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int answer, maxProfit, N, M;
    static List<int[]> home;

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
            answer = 0;
            maxProfit = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            home = new ArrayList<>();
            
            // input
            for(int i=0; i<N; i++) {
            	st = new StringTokenizer(br.readLine());            	
            	for(int j=0; j<N; j++) {
            		if(Integer.parseInt(st.nextToken()) == 1) {
            			home.add(new int[] {i, j});
            		}
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
    	for(int k=1; k<2*N; k++) {
    		// 얻을 수 있는 이익이 현재 최대 이익보다 작을 경우 중단
    		int cost = k*k + (k-1)*(k-1);
    		int limitProfit = home.size() * M;
    		if(limitProfit - cost < 0) break;
    		
    		// 좌표 순회 -> 마름모 영역 설정 후 이익 계산
            for(int i=0; i<N; i++) {        	
            	for(int j=0; j<N; j++) {
            		// 집 순회하면서 이익 계산
            		int cnt = 0;
            		for(int[] pos : home) {
            			int r = pos[0];
            			int c = pos[1];
            			// 방범 서비스 영역인지 확인(멘하탄 거리 : 다이아몬드)
            			if((Math.abs(i-r) + Math.abs(j-c)) <= k-1) {
            				cnt++;
            			}
            		}
            		int profit = cnt * M - cost;
            		if(cnt > answer && profit >= 0) {
            			maxProfit = profit;
            			answer = cnt;
            		}
            	}
            }
    	}
    }
}