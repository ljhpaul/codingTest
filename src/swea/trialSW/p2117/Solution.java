package swea.trialSW.p2117;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int maxCnt, N, M;
    static List<Pos> homes;
    
    // Pos
    static class Pos {
    	int r, c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "(" + r + ", " + c + ")";
		}
    }

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	maxCnt = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            homes = new ArrayList<>();

            // input
            for(int r = 0; r < N; r++) {
            	st = new StringTokenizer(br.readLine());
            	for(int c = 0; c < N; c++) {
            		int input = Integer.parseInt(st.nextToken());
            		if(input == 1) homes.add(new Pos(r, c));
            	}
            }
            
            // solve
            solve();

            // answer
            sb.append("#").append(tc).append(" ").append(maxCnt).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    // solve
	private static void solve() {
		// 서비스 영역 설정 : K(1 ~ N + 1)
		for(int K = 1; K <= N + 1; K++) {
			int cost = K * K + (K - 1) * (K - 1);	// 운영 비용
			
			// 모든 좌표 순회
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					int cnt = 0;	// 영역에 포함된 집의 수
					
					// 모든 집 순회
					for(Pos home : homes) {
						// 멘하탄 거리 이내 확인 후 서비스 여부 판단
						if(Math.abs(home.r - r) + Math.abs(home.c - c) < K) cnt++;
					}
					
					int earning = cnt * M;	// 서비스 수익
					int profit = earning - cost;	// 이익
					
					// 손해를 보지 않음 -> 최대 서비스 제공 수 갱신
					if(profit >= 0) maxCnt = Math.max(maxCnt, cnt);
				}
			}
		}
	}
}