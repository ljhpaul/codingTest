package swea.trialSW.p2382;

import java.io.*;
import java.util.*;

class Solution {
    // static field
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int N, M;
    
    static int[] dr = {0, -1, 1, 0, 0}; // 상하좌우(1234)
    static int[] dc = {0, 0, 0, -1, 1};
    static int[] changeDir = {0, 2, 1, 4, 3}; // 하상우좌(2143)
    
    // Micro
    static class Micro {
    	int r, c, cnt, dir;
		public Micro(int r, int c, int cnt, int dir) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.dir = dir;
		}
    }

    // main
    public static void main(String[] args) throws IOException {
        // test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            // init
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	M = Integer.parseInt(st.nextToken());
        	int K = Integer.parseInt(st.nextToken());
        	List<Micro> micros = new ArrayList<>();
        	
        	// input
        	for(int i=0; i<K; i++) {
        		st = new StringTokenizer(br.readLine());
        		int r = Integer.parseInt(st.nextToken());
        		int c = Integer.parseInt(st.nextToken());
        		int cnt = Integer.parseInt(st.nextToken());
        		int dir = Integer.parseInt(st.nextToken());
        		micros.add(new Micro(r, c, cnt, dir));
        	}
            
            // solve
        	int answer = simulate(micros);

            // answer
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        // output
        System.out.println(sb);
        br.close();
    }

    // simulate
	private static int simulate(List<Micro> micros) {
		for(int hour=0; hour<M; hour++) {
			Map<Long, List<Micro>> moved = new HashMap<>();
			// 미생물 이동
			for(Micro m : micros) {
				int cnt = m.cnt;
				int dir = m.dir;
				int r = m.r + dr[dir];
				int c = m.c + dc[dir];
				
				// 약품 처리 : 미생물 타노스 + 방향 전환
				if(isMedicine(r, c)) {
					cnt /= 2;
					dir = changeDir[dir];
				}
				if(cnt == 0) continue;
				
				// 다음 좌표영역에 삽입
				long key = getKey(r, c);
				if(!moved.containsKey(key)) {
					moved.put(key, new ArrayList<>());
				}
				moved.get(key).add(new Micro(r, c, cnt, dir));
			}
			// 이동 좌표 순회 후 합체 처리
			List<Micro> next = new ArrayList<>();
			for(List<Micro> list : moved.values()) {
				int r = list.get(0).r;
				int c = list.get(0).c;
				int cnt = 0;
				int dir = 0;
				int maxCnt = 0;
				for(Micro m : list) {
					cnt += m.cnt;
					if(m.cnt > maxCnt) {
						maxCnt = m.cnt;
						dir = m.dir;
					}
				}
				next.add(new Micro(r, c, cnt, dir));
			}
			micros = next;
		}
		
		// 미생물 합 계산
		int sum = 0;
		for(Micro m : micros) {
			sum += m.cnt;
		}
		return sum;
	}
	
	// check medicine
	private static boolean isMedicine(int r, int c) {
		return r == 0 || r == N-1 || c == 0 || c == N-1;
	}
	
	// pos key
	private static long getKey(int r, int c) {
		return ((long) r)<<32 | c;
	}
}